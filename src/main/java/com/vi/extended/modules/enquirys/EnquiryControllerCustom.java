package com.vi.extended.modules.enquirys;

import java.util.Collections;
import java.util.List;

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
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.vi.base.modules.enquirys.EnquiryRepository;
import com.vi.base.modules.enquirys.EnquiryService;
import com.vi.extended.modules.quotes.QuoteMapperCustom;
import com.vi.model.dao.QuoteDAO;
import com.vi.model.dto.EnquiryDTO;
import com.vi.model.dto.QuoteDTOCustom;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/Enquiry")
@Slf4j
public class EnquiryControllerCustom {

    @Autowired
    private Environment env;

    @Autowired
    private EnquiryServiceCustom enquiryServiceCustom; 
    
    @Autowired
    private EnquiryRepositoryCustom quoteRepositoryCustom;

    @Autowired
    private EnquiryService enquiryService;
    
    @Autowired
    private EnquiryRepository enquiryRepository;

    @PersistenceContext
    private EntityManager em;

    @PostMapping("/Filter")
    public ResponseEntity<?> filter(
            @RequestBody JsonNode json,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "1000") int size,
            @RequestParam(value = "dateFilter", required = false) String dateFilter,
            @RequestParam(value = "from", required = false) String from,
            @RequestParam(value = "to", required = false) String to) {
        try {
        	
            ObjectNode mutableJson = json.deepCopy();
            System.out.print(json);
            mutableJson.remove("page");
            mutableJson.remove("size");
            mutableJson.put("deleted","false");
            
            // Handle custom date filter
            if ("custom".equalsIgnoreCase(dateFilter) || from != null && to != null) {
                String dateRange = from + "__" + to;
                mutableJson.put("enqDate", dateRange);
                log.info("Filter request received with JSON: {}", mutableJson.toString());
            }

            log.info("Filter request received with JSON: {}", mutableJson.toString());

            return ResponseEntity.ok().body(enquiryService.filterData(mutableJson, page, size));
            
        } catch (Exception ex) {
            log.error("Error filtering leads", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", ex.getMessage()));
        }
    } 
}