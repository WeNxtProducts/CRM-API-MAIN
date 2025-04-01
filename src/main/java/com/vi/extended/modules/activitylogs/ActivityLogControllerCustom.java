package com.vi.extended.modules.activitylogs;

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
import com.vi.base.modules.activitylogs.ActivityLogRepository;
import com.vi.base.modules.activitylogs.ActivityLogService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/ActivityLog")
@Slf4j
public class ActivityLogControllerCustom {

    @Autowired
    private Environment env;

    @Autowired
    private ActivityLogServiceCustom activityLogServiceCustom;  

    @Autowired
    private ActivityLogService activityLogService;
    
    @Autowired
    private ActivityLogRepository activityLogRepository;

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

            return ResponseEntity.ok().body(activityLogService.filterData(mutableJson, page, size));
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
//            if (!json.has("activityLogSeqNo") || json.get("activityLogSeqNo").isNull()) {
//                throw new EntityNotFoundException("Missing required field: activityLogSeqNo");
//            }
//            if (!json.has("activityLogSource") || json.get("activityLogSource").isNull()) {
//                throw new EntityNotFoundException("Missing required field: activityLogSource");
//            }
//            if (!json.has("activityLogDescription") || json.get("activityLogDescription").isNull()) {
//                throw new EntityNotFoundException("Missing required field: activityLogDescription");
//            }
//
//            Integer activityLogSeqNo = json.get("activityLogSeqNo").asInt();
//            String activityLogSource = json.get("activityLogSource").asText();
//            String activityLogDescription = json.get("activityLogDescription").asText();
//
//            // Set activityLogSource and validate
//            Set<String> validActivityLogSources = Set.of("mail", "whatsapp", "sms");
//            if (!validActivityLogSources.contains(activityLogSource.toLowerCase())) {
//                throw new IllegalArgumentException("Invalid activityLogSource: " + activityLogSource);
//            }
//            
//            // Update activityLog source, description, and remainderSent
//            ActivityLogDAO updatedActivityLog = activityLogServiceCustom.updateActivityLogSourceAndDescription(activityLogSeqNo, activityLogSource, activityLogDescription);
//            return ResponseEntity.ok().body(buildResponse("success", "ActivityLog updated successfully", updatedActivityLog));
//
//            return activityLogRepository.findById(activityLogSeqNo).map(activityLog -> {
//                // Set activityLog source from input
//                activityLog.setActivityLogSource(activityLogSource);
//
//                // Increment remainderSent count
//                activityLog.setRemainderSent(activityLog.getRemainderSent() == null ? 1 : activityLog.getRemainderSent() + 1);
//
//                // Save updated activityLog
//                activityLogRepository.save(activityLog);
//
//                return ResponseEntity.ok().body(buildResponse("success", "ActivityLog updated successfully", activityLog));
//            }).orElseThrow(() -> new EntityNotFoundException("ActivityLog not found for activityLogSeqNo: " + activityLogSeqNo));
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
//    public ResponseEntity<ActivityLogDTOCustom> update(@RequestBody ActivityLogDTOCustom activityLogDTOCustom) {
//        Set<String> allowedSources = Set.of("mail", "whatsapp", "sms");
//
//        if (allowedSources.contains(activityLogDTOCustom.getActivityLogSource())) {
//        	
//        	activityLogDTOCustom.setActivityLogDescription(activityLogDTOCustom.getActivityLogDescription());
//        
//        	System.out.println("Before Increment: " + activityLogDTOCustom.getRemainderCount());
//     	    activityLogDTOCustom.setRemainderCount(activityLogDTOCustom.getRemainderCount() + 1); // Increment count
//     	    System.out.println("After Increment: " + activityLogDTOCustom.getRemainderCount());
//
//            var activityLogActivityLogDTO = activityLogServiceCustom.update(activityLogDTOCustom);
//            return ResponseEntity.ok().body(activityLogActivityLogDTO);
//        }
//
//        return ResponseEntity.badRequest().build(); 
//    }
   
   
}