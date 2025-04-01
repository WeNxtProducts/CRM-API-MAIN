package com.vi.extended.modules.conversations;

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
import com.vi.base.modules.conversations.ConversationRepository;
import com.vi.base.modules.conversations.ConversationService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/Conversation")
@Slf4j
public class ConversationControllerCustom {

    @Autowired
    private Environment env;

    @Autowired
    private ConversationServiceCustom conversationServiceCustom;  

    @Autowired
    private ConversationService conversationService;
    
    @Autowired
    private ConversationRepository conversationRepository;

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
            mutableJson.remove("page");
            mutableJson.remove("size");
            mutableJson.put("deleted","false");

            log.info("Filter request received with JSON: {}", mutableJson.toString());

            return ResponseEntity.ok().body(conversationService.filterData(mutableJson, page, size));
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
//            if (!json.has("conversationSeqNo") || json.get("conversationSeqNo").isNull()) {
//                throw new EntityNotFoundException("Missing required field: conversationSeqNo");
//            }
//            if (!json.has("conversationSource") || json.get("conversationSource").isNull()) {
//                throw new EntityNotFoundException("Missing required field: conversationSource");
//            }
//            if (!json.has("conversationDescription") || json.get("conversationDescription").isNull()) {
//                throw new EntityNotFoundException("Missing required field: conversationDescription");
//            }
//
//            Integer conversationSeqNo = json.get("conversationSeqNo").asInt();
//            String conversationSource = json.get("conversationSource").asText();
//            String conversationDescription = json.get("conversationDescription").asText();
//
//            // Set conversationSource and validate
//            Set<String> validConversationSources = Set.of("mail", "whatsapp", "sms");
//            if (!validConversationSources.contains(conversationSource.toLowerCase())) {
//                throw new IllegalArgumentException("Invalid conversationSource: " + conversationSource);
//            }
//            
//            // Update conversation source, description, and remainderSent
//            ConversationDAO updatedConversation = conversationServiceCustom.updateConversationSourceAndDescription(conversationSeqNo, conversationSource, conversationDescription);
//            return ResponseEntity.ok().body(buildResponse("success", "Conversation updated successfully", updatedConversation));
//
//            return conversationRepository.findById(conversationSeqNo).map(conversation -> {
//                // Set conversation source from input
//                conversation.setConversationSource(conversationSource);
//
//                // Increment remainderSent count
//                conversation.setRemainderSent(conversation.getRemainderSent() == null ? 1 : conversation.getRemainderSent() + 1);
//
//                // Save updated conversation
//                conversationRepository.save(conversation);
//
//                return ResponseEntity.ok().body(buildResponse("success", "Conversation updated successfully", conversation));
//            }).orElseThrow(() -> new EntityNotFoundException("Conversation not found for conversationSeqNo: " + conversationSeqNo));
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
//    public ResponseEntity<ConversationDTOCustom> update(@RequestBody ConversationDTOCustom conversationDTOCustom) {
//        Set<String> allowedSources = Set.of("mail", "whatsapp", "sms");
//
//        if (allowedSources.contains(conversationDTOCustom.getConversationSource())) {
//        	
//        	conversationDTOCustom.setConversationDescription(conversationDTOCustom.getConversationDescription());
//        
//        	System.out.println("Before Increment: " + conversationDTOCustom.getRemainderCount());
//     	    conversationDTOCustom.setRemainderCount(conversationDTOCustom.getRemainderCount() + 1); // Increment count
//     	    System.out.println("After Increment: " + conversationDTOCustom.getRemainderCount());
//
//            var conversationConversationDTO = conversationServiceCustom.update(conversationDTOCustom);
//            return ResponseEntity.ok().body(conversationConversationDTO);
//        }
//
//        return ResponseEntity.badRequest().build(); 
//    }
   
   
}