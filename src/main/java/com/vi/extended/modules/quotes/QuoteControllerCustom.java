package com.vi.extended.modules.quotes;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.vi.base.modules.quotes.QuoteService;
import com.vi.base.modules.users.UserService;
import com.vi.model.dao.QuoteDAO;
import com.vi.model.dto.QuoteDTO;
import com.vi.model.dto.QuoteDTOCustom;
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
        @RequestParam(value = "size", defaultValue = "1000") int size,
        @RequestParam(value = "dateFilter", required = false) String dateFilter,
        @RequestParam(value = "from", required = false) String from,
        @RequestParam(value = "to", required = false) String to,
        @RequestParam(value = "listingType", required = true) String listingType) {

        try {
            if (json == null) {
                return ResponseEntity.badRequest().body("Request body cannot be null");
            }

            ObjectNode mutableJson = (ObjectNode) json.deepCopy();
            mutableJson.remove("page");
            mutableJson.remove("size");
            mutableJson.put("deleted", "false");

            // Apply custom date filter if present
            if ("custom".equalsIgnoreCase(dateFilter) && from != null && to != null) {
                mutableJson.put("quoteCreatedDate", from + "__" + to);
                log.info("Applying custom date filter: {} to {}", from, to);
            }

            Set<QuoteDTO> finalResults = new LinkedHashSet<>(); // <--- FIX: Set<QuoteDTO>

            if ("underwriter".equalsIgnoreCase(listingType)) {
                // Underwriter listing: isAccepted = "Todo" or "accept"
                ObjectNode filterJson = mutableJson.deepCopy();
                
                filterJson.put("isAccepted", "Todo");
                log.info("Filtering for underwriter with isAccepted: Todo");
                List<QuoteDTO> partialResultsTodo = (List<QuoteDTO>) quoteService.filterData(filterJson, page, size);
                finalResults.addAll(partialResultsTodo);

                filterJson.put("isAccepted", "accept");
                log.info("Filtering for underwriter with isAccepted: accept");
                List<QuoteDTO> partialResultsAccept = (List<QuoteDTO>) quoteService.filterData(filterJson, page, size);
                finalResults.addAll(partialResultsAccept);

            } else if ("quoteHistory".equalsIgnoreCase(listingType)) {
                // Only filter by isAccepted = "accept"
                ObjectNode filterJson = mutableJson.deepCopy();
                filterJson.put("isAccepted", "accept");

                log.info("Filtering for quote history with isAccepted: accept");

                List<QuoteDTO> partialResults = (List<QuoteDTO>) quoteService.filterData(filterJson, page, size);
                finalResults.addAll(partialResults);

            } else {
                return ResponseEntity.badRequest().body("Invalid listing type");
            }


            // Convert Set to List and sort by 'quoteCreatedDate' to show latest first
            List<QuoteDTO> resultList = new ArrayList<>(finalResults);

            resultList.sort(Comparator.comparing(QuoteDTO::getQuoteCreatedDate).reversed());

            log.info("Filtered {} results", resultList.size());

            return ResponseEntity.ok(resultList);

        } catch (Exception ex) {
            log.error("Error filtering quotes", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", ex.getMessage()));
        }
    }


    @Transactional
    @PutMapping("/isAccepted")
    public ResponseEntity<?> updateQuoteStatus(@RequestBody QuoteDTOCustom quoteDTOCustom) {
        try {
            // 1. Validate input
            if (quoteDTOCustom == null || quoteDTOCustom.getEnqSeqNo() == null ||
                quoteDTOCustom.getUserSeqNo() == null || quoteDTOCustom.getIsAccepted() == null) {
                return ResponseEntity.badRequest().body(
                    Collections.singletonMap("error", "Missing required fields in request"));
            }

            // 2. Fetch all quotes for this enquiry
            List<QuoteDAO> allQuotes = quoteRepositoryCustom.findByEnqSeqNo(quoteDTOCustom.getEnqSeqNo());
            if (allQuotes.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    Collections.singletonMap("error", "No quotes found for EnqSeqNo: " + quoteDTOCustom.getEnqSeqNo()));
            }

            // 3. Find the "Todo" quote for this user
            QuoteDAO quoteToUpdate = allQuotes.stream()
                .filter(q -> "Todo".equalsIgnoreCase(q.getIsAccepted()) && 
                             quoteDTOCustom.getUserSeqNo().equals(q.getUserSeqNo()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(
                    String.format("Todo quote not found for UserSeqNo %d and EnqSeqNo %d. Available: %s",
                        quoteDTOCustom.getUserSeqNo(),
                        quoteDTOCustom.getEnqSeqNo(),
                        allQuotes.stream()
                            .map(q -> q.getUserSeqNo() + ":" + q.getIsAccepted())
                            .collect(Collectors.joining(", "))
                    )
                ));

            // 4. Update quotes
            if ("accept".equalsIgnoreCase(quoteDTOCustom.getIsAccepted())) {
                // Accept this quote, reject others (only those in Todo state)
                allQuotes.forEach(quote -> {
                    if ("Todo".equalsIgnoreCase(quote.getIsAccepted())) {
                        if (quoteDTOCustom.getUserSeqNo().equals(quote.getUserSeqNo())) {
                            quote.setIsAccepted("accept");
                        } else {
                            quote.setIsAccepted("reject");
                        }
                    }
                });
            } else {
                // Just update this Todo quote to reject/other status
                quoteToUpdate.setIsAccepted(quoteDTOCustom.getIsAccepted());
            }

            // 5. Find the latest accepted quote
            Optional<QuoteDAO> latestAcceptedQuote = allQuotes.stream()
                .filter(q -> "accept".equalsIgnoreCase(q.getIsAccepted()) && q.getQuoteCreatedDate() != null)
                .max(Comparator.comparing(QuoteDAO::getQuoteCreatedDate));

            // 6. Update currUnderwriter based on latest accepted quote
            latestAcceptedQuote.ifPresent(latestQuote -> {
                allQuotes.forEach(q -> q.setCurrUnderwriter(latestQuote.getQuoteSeqNo()));
            });

            // 7. Save all updates
            quoteRepositoryCustom.saveAll(allQuotes);

            // 8. Return response
            return ResponseEntity.ok(QuoteMapperCustom.INSTANCE.quoteDAOToQuoteDTOCustom(quoteToUpdate));

        } catch (IllegalArgumentException | IllegalStateException e) {
            log.error("Validation error: {}", e.getMessage());
            return ResponseEntity.badRequest().body(Collections.singletonMap("error", e.getMessage()));
        } catch (EntityNotFoundException e) {
            log.error("Not found error: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("error", e.getMessage()));
        } catch (Exception e) {
            log.error("Unexpected error: {}", e.getMessage(), e);
            return ResponseEntity.internalServerError().body(Collections.singletonMap("error", "Internal server error"));
        }
    }

}