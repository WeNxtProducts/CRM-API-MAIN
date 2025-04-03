package com.vi.extended.modules.quotes;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.vi.base.modules.quotes.QuoteService;
import com.vi.base.modules.users.UserService;
import com.vi.model.dao.QuoteDAO;
import com.vi.model.dto.QuoteDTO;
import com.vi.model.dto.QuoteDTOCustom;
import com.vi.model.dto.SummaryDTO;
import com.vi.model.dto.UserDTO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/Quote")
@Slf4j
public class QuoteControllerCustom {

    @Autowired
    private Environment env;

    @Autowired
    private QuoteServiceCustom quoteServiceCustom;  

    @Autowired
    private UserService userService;
    
    @Autowired
    private QuoteService quoteService;
    
    @Autowired
    private QuoteRepositoryCustom quoteRepositoryCustom;

    @PersistenceContext
    private EntityManager em; 
   
    @PostMapping("/Filter")
    public ResponseEntity<?> filter(
            @RequestBody JsonNode json, 
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "1000") int size) {

        try {
            ObjectNode mutableJson = json.deepCopy();
            mutableJson.remove("page");
            mutableJson.remove("size");
            mutableJson.put("deleted","false");

            log.info("Filter request received with JSON: {}", mutableJson.toString());
            
            return ResponseEntity.ok().body(quoteService.filterData(mutableJson, page, size));
        } catch (Exception ex) {
            log.error("Error filtering quotes", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", ex.getMessage()));
        }
    }
    
    @Transactional
    @PutMapping("/FilterSource")
    public ResponseEntity<QuoteDTOCustom> update(@RequestBody QuoteDTOCustom quoteDTOCustom) {
        if (quoteDTOCustom == null || quoteDTOCustom.getEnqSeqNo() == null || 
            quoteDTOCustom.getUserSeqNo() == null || quoteDTOCustom.getIsAccepted() == null) {
            log.error("Bad request: Missing required fields.");
            return ResponseEntity.badRequest().build();
        }

        try {
            List<QuoteDAO> enquiryQuotes = quoteRepositoryCustom.findByEnqSeqNo(quoteDTOCustom.getEnqSeqNo());

            if (enquiryQuotes == null || enquiryQuotes.isEmpty()) {
                log.error("No quotes found for EnqSeqNo: {}", quoteDTOCustom.getEnqSeqNo());
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

            // First, check if there are any already accepted/rejected quotes and mark them as "done"
            boolean hasExistingStatus = enquiryQuotes.stream()
                .anyMatch(quote -> quote.getIsAccepted() != null && 
                         !"done".equals(quote.getIsAccepted()));

            if (hasExistingStatus) {
                // Mark all existing quotes with status as "done"
                enquiryQuotes.forEach(quote -> {
                    if (quote.getIsAccepted() != null && !"done".equals(quote.getIsAccepted())) {
                        quote.setIsAccepted("done");
                    }
                });
                quoteRepositoryCustom.saveAll(enquiryQuotes);
            }

            // Now process the new status update
            for (QuoteDAO quote : enquiryQuotes) {
                if (quote.getUserSeqNo() == null) {
                    log.error("Found quote with null UserSeqNo for EnqSeqNo: {}", quoteDTOCustom.getEnqSeqNo());
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                }

                if (quoteDTOCustom.getUserSeqNo().equals(quote.getUserSeqNo())) {
                    quote.setIsAccepted(quoteDTOCustom.getIsAccepted());
                } else {
                    if ("accept".equals(quoteDTOCustom.getIsAccepted())) {
                        quote.setIsAccepted("reject");
                    }
                }
            }

            quoteRepositoryCustom.saveAll(enquiryQuotes);

            // Find the updated quote(s) - now handling multiple results
            List<QuoteDAO> updatedQuotes = quoteRepositoryCustom.findByEnqSeqNoAndUserSeqNo(
                    quoteDTOCustom.getEnqSeqNo(),
                    quoteDTOCustom.getUserSeqNo());

            if (updatedQuotes.isEmpty()) {
                log.error("Quote not found for EnqSeqNo: {}, UserSeqNo: {}", 
                         quoteDTOCustom.getEnqSeqNo(), quoteDTOCustom.getUserSeqNo());
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

            // Get the first matching quote (or implement your business logic to handle multiple)
            QuoteDAO acceptedQuote = updatedQuotes.get(0);
            return ResponseEntity.ok().body(QuoteMapperCustom.INSTANCE.quoteDAOToQuoteDTOCustom(acceptedQuote));

        } catch (Exception e) {
            log.error("Error updating quote status for EnqSeqNo: {}, UserSeqNo: {}. Error: {}", 
                      quoteDTOCustom.getEnqSeqNo(), quoteDTOCustom.getUserSeqNo(), e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @GetMapping("/Stats")
    public ResponseEntity<?> getBrokerCode( @RequestParam int userId) {
        String sql = "SELECT * FROM wn_quote_summary WHERE user_seq_no="+ userId;
        List<Object[]> queryResult = em.createNativeQuery(sql).getResultList();
        
        List<SummaryDTO> result = queryResult.stream()
                .map(row -> new SummaryDTO(
                        ((Number) row[0]).longValue(),  
                        ((Number) row[1]).longValue(),  
                        (BigDecimal) row[2],  
                        (BigDecimal) row[3],  
                        (BigDecimal) row[4],  
                        (String) row[5]       
                ))
                .toList();

        return ResponseEntity.ok().body(result);
    }

    
    
    
   
   
}