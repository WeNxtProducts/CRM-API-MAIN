package com.vi.extended.modules.enquirys;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.vi.base.modules.enquirys.EnquiryRepository;
import com.vi.base.modules.enquirys.EnquiryService;

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
    private EnquiryService enquiryService;
    
    @Autowired
    private EnquiryRepository enquiryRepository;

    @PersistenceContext
    private EntityManager em;

    @PostMapping("/Filter")
    public ResponseEntity<?> filter(
            @RequestBody JsonNode json, 
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "1000") int size) {

        try {
            // Convert JsonNode to mutable ObjectNode
            ObjectNode mutableJson = json.deepCopy();
            System.out.print(json);
            mutableJson.remove("page");
            mutableJson.remove("size");
            mutableJson.put("deleted","false");


            log.info("Filter request received with JSON: {}", mutableJson.toString());

            return ResponseEntity.ok().body(enquiryService.filterData(mutableJson, page, size));
        } catch (Exception ex) {
            log.error("Error filtering leads", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", ex.getMessage()));
        }
    }

    
//    @PostMapping("/filterSource")
//    public ResponseEntity<?> filterSource(@RequestBody JsonNode json) {
//        try {
//            log.info("INPUT JSON: {}", json);
//
//            // Validate input JSON
//            if (!json.has("enquirySeqNo") || json.get("enquirySeqNo").isNull()) {
//                throw new EntityNotFoundException("Missing required field: enquirySeqNo");
//            }
//            if (!json.has("enquirySource") || json.get("enquirySource").isNull()) {
//                throw new EntityNotFoundException("Missing required field: enquirySource");
//            }
//            if (!json.has("enquiryDescription") || json.get("enquiryDescription").isNull()) {
//                throw new EntityNotFoundException("Missing required field: enquiryDescription");
//            }
//
//            Integer enquirySeqNo = json.get("enquirySeqNo").asInt();
//            String enquirySource = json.get("enquirySource").asText();
//            String enquiryDescription = json.get("enquiryDescription").asText();
//
//            // Set enquirySource and validate
//            Set<String> validEnquirySources = Set.of("mail", "whatsapp", "sms");
//            if (!validEnquirySources.contains(enquirySource.toLowerCase())) {
//                throw new IllegalArgumentException("Invalid enquirySource: " + enquirySource);
//            }
//            
//            // Update enquiry source, description, and remainderSent
//            EnquiryDAO updatedEnquiry = enquiryServiceCustom.updateEnquirySourceAndDescription(enquirySeqNo, enquirySource, enquiryDescription);
//            return ResponseEntity.ok().body(buildResponse("success", "Enquiry updated successfully", updatedEnquiry));
//
//            return enquiryRepository.findById(enquirySeqNo).map(enquiry -> {
//                // Set enquiry source from input
//                enquiry.setEnquirySource(enquirySource);
//
//                // Increment remainderSent count
//                enquiry.setRemainderSent(enquiry.getRemainderSent() == null ? 1 : enquiry.getRemainderSent() + 1);
//
//                // Save updated enquiry
//                enquiryRepository.save(enquiry);
//
//                return ResponseEntity.ok().body(buildResponse("success", "Enquiry updated successfully", enquiry));
//            }).orElseThrow(() -> new EntityNotFoundException("Enquiry not found for enquirySeqNo: " + enquirySeqNo));
//
//        } catch (EntityNotFoundException | IllegalArgumentException ex) {
//            log.warn("Validation error: {}", ex.getMessage());
//            return ResponseEntity.badRequest().body(ex.getMessage());
//        } catch (Exception ex) {
//            log.error("Error processing request", ex);
//            return ResponseEntity.internalServerError().body("Error processing request: " + ex.getMessage());
//        }
//    }
    
//    @PutMapping("/FilterSource")
//    public ResponseEntity<EnquiryDTOCustom> update(@RequestBody EnquiryDTOCustom enquiryDTOCustom) {
//        Set<String> allowedSources = Set.of("mail", "whatsapp", "sms");
//
//        if (allowedSources.contains(enquiryDTOCustom.getEnquirySource())) {
//        	
//        	enquiryDTOCustom.setEnquiryDescription(enquiryDTOCustom.getEnquiryDescription());
//        
//        	System.out.println("Before Increment: " + enquiryDTOCustom.getRemainderCount());
//     	    enquiryDTOCustom.setRemainderCount(enquiryDTOCustom.getRemainderCount() + 1); // Increment count
//     	    System.out.println("After Increment: " + enquiryDTOCustom.getRemainderCount());
//
//            var enquiryEnquiryDTO = enquiryServiceCustom.update(enquiryDTOCustom);
//            return ResponseEntity.ok().body(enquiryEnquiryDTO);
//        }
//
//        return ResponseEntity.badRequest().build(); 
//    }
   
   
}