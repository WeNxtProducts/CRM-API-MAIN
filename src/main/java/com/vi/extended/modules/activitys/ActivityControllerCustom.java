package com.vi.extended.modules.activitys;

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
import com.vi.base.modules.activitys.ActivityRepository;
import com.vi.base.modules.activitys.ActivityService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/Activity")
@Slf4j
public class ActivityControllerCustom {

    @Autowired
    private Environment env;

    @Autowired
    private ActivityServiceCustom activityServiceCustom;  

    @Autowired
    private ActivityService activityService;
    
    @Autowired
    private ActivityRepository activityRepository;

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

            return ResponseEntity.ok().body(activityService.filterData(mutableJson, page, size));
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
//            if (!json.has("activitySeqNo") || json.get("activitySeqNo").isNull()) {
//                throw new EntityNotFoundException("Missing required field: activitySeqNo");
//            }
//            if (!json.has("activitySource") || json.get("activitySource").isNull()) {
//                throw new EntityNotFoundException("Missing required field: activitySource");
//            }
//            if (!json.has("activityDescription") || json.get("activityDescription").isNull()) {
//                throw new EntityNotFoundException("Missing required field: activityDescription");
//            }
//
//            Integer activitySeqNo = json.get("activitySeqNo").asInt();
//            String activitySource = json.get("activitySource").asText();
//            String activityDescription = json.get("activityDescription").asText();
//
//            // Set activitySource and validate
//            Set<String> validActivitySources = Set.of("mail", "whatsapp", "sms");
//            if (!validActivitySources.contains(activitySource.toLowerCase())) {
//                throw new IllegalArgumentException("Invalid activitySource: " + activitySource);
//            }
//            
//            // Update activity source, description, and remainderSent
//            ActivityDAO updatedActivity = activityServiceCustom.updateActivitySourceAndDescription(activitySeqNo, activitySource, activityDescription);
//            return ResponseEntity.ok().body(buildResponse("success", "Activity updated successfully", updatedActivity));
//
//            return activityRepository.findById(activitySeqNo).map(activity -> {
//                // Set activity source from input
//                activity.setActivitySource(activitySource);
//
//                // Increment remainderSent count
//                activity.setRemainderSent(activity.getRemainderSent() == null ? 1 : activity.getRemainderSent() + 1);
//
//                // Save updated activity
//                activityRepository.save(activity);
//
//                return ResponseEntity.ok().body(buildResponse("success", "Activity updated successfully", activity));
//            }).orElseThrow(() -> new EntityNotFoundException("Activity not found for activitySeqNo: " + activitySeqNo));
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
//    public ResponseEntity<ActivityDTOCustom> update(@RequestBody ActivityDTOCustom activityDTOCustom) {
//        Set<String> allowedSources = Set.of("mail", "whatsapp", "sms");
//
//        if (allowedSources.contains(activityDTOCustom.getActivitySource())) {
//        	
//        	activityDTOCustom.setActivityDescription(activityDTOCustom.getActivityDescription());
//        
//        	System.out.println("Before Increment: " + activityDTOCustom.getRemainderCount());
//     	    activityDTOCustom.setRemainderCount(activityDTOCustom.getRemainderCount() + 1); // Increment count
//     	    System.out.println("After Increment: " + activityDTOCustom.getRemainderCount());
//
//            var activityActivityDTO = activityServiceCustom.update(activityDTOCustom);
//            return ResponseEntity.ok().body(activityActivityDTO);
//        }
//
//        return ResponseEntity.badRequest().build(); 
//    }
   
   
}