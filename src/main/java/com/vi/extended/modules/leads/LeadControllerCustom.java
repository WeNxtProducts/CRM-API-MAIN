package com.vi.extended.modules.leads;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.vi.base.modules.activitylogs.ActivityLogService;
import com.vi.base.modules.leads.LeadService;
import com.vi.base.modules.leads.LeadController.ActivityLogUtil;
import com.vi.base.modules.users.UserService;
import com.vi.model.dao.LeadDAO;
import com.vi.model.dto.ActivityLogDTO;
import com.vi.model.dto.LeadDTOCustom;
import com.vi.model.dto.SummaryDTO;
import com.vi.model.dto.UserDTO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/Lead")
@Slf4j
public class LeadControllerCustom {

    @Autowired
    private Environment env;

    @Autowired
    private LeadServiceCustom leadServiceCustom;  

    @Autowired
    private UserService userService;
    
    @Autowired
    private LeadService leadService;
    
    @Autowired
    private LeadRepositoryCustom leadRepositoryCustom;
    
    @Autowired
    private ActivityLogService activityLogService;

    @PersistenceContext
    private EntityManager em; 
    
    @PostMapping("/Create")
    public ResponseEntity<LeadDAO> create(@RequestBody LeadDAO leadDAO) {
        try {
            LeadDAO createdLead = leadRepositoryCustom.save(leadDAO);
            UserDTO userDTO = userService.get(leadDAO.getUserSeqNo());

            if (createdLead != null && "Sales".equalsIgnoreCase(userDTO.getUserRole())) {
                createdLead.setLeadAssignedTo(leadDAO.getUserSeqNo());
                leadRepositoryCustom.save(createdLead); 
            }
            
    		ActivityLogUtil.createActivityLog(leadDAO.getUserSeqNo(),"LEAD_CREATED","Lead created: " + leadDAO.getLeadName(), activityLogService);

            return ResponseEntity.ok().body(createdLead);
        } catch (Exception e) {
            log.error("Error while creating lead: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


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
            mutableJson.remove("page");
            mutableJson.remove("size");
            mutableJson.put("deleted", "false");

            // Handle custom date filter
            if ("custom".equalsIgnoreCase(dateFilter) || from != null && to != null) {
                String dateRange = from + "__" + to;
                mutableJson.put("leadCreatedDate", dateRange);
                log.info("Filter request received with JSON: {}", mutableJson.toString());
            }

            log.info("Filter request received with JSON: {}", mutableJson.toString());
            
            return ResponseEntity.ok().body(leadService.filterData(mutableJson, page, size));
        } catch (Exception ex) {
            log.error("Error filtering leads", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", ex.getMessage()));
        }
    }
    
    private UserDTO extractUserDTO(JsonNode json) {
		return null;
	}

	@PutMapping("/FilterSource")
    public ResponseEntity<LeadDTOCustom> update(@RequestBody LeadDTOCustom leadDTOCustom) {
        Set<String> allowedSources = Set.of("mail", "whatsapp", "sms");

        if (allowedSources.contains(leadDTOCustom.getLeadSource())) {
        	
        	leadDTOCustom.setLeadDescription(leadDTOCustom.getLeadDescription());
        
        	System.out.println("Before Increment: " + leadDTOCustom.getRemainderCount());
     	    leadDTOCustom.setRemainderCount(leadDTOCustom.getRemainderCount() + 1); // Increment count
     	    System.out.println("After Increment: " + leadDTOCustom.getRemainderCount());

            var leadLeadDTO = leadServiceCustom.update(leadDTOCustom);
            return ResponseEntity.ok().body(leadLeadDTO);
        }

        return ResponseEntity.badRequest().build(); 
    }
    
	@GetMapping("/Stats")
	public ResponseEntity<?> getLeadSummary(@RequestParam(value = "userSeqNo") int userId) {
	    String sql = "SELECT * FROM wn_lead_summary WHERE user_seq_no = " + userId;
	    List<Object[]> queryResult = em.createNativeQuery(sql).getResultList();

	    if (queryResult.isEmpty()) {
	        return ResponseEntity.notFound().build();
	    }

	    List<SummaryDTO> result = queryResult.stream()
	            .map(row -> new SummaryDTO(
	                    ((Number) row[0]).longValue(),       // user_seq_no
	                    (String) row[1],                     // user_role
	                    ((Number) row[2]).longValue(),       // total_count
	                    (BigDecimal) row[3],   				 // newly_added
	                    (BigDecimal) row[4],				 // on_priority
	                    (BigDecimal) row[5],				 // completed
	                    ((Number) row[6]).longValue(),        // total_enquiry
	                    ((Number) row[7]).longValue(),        // pending_enquiry
	                    ((Number) row[8]).longValue(),        // meeting_req_enquiry
	                    ((Number) row[9]).longValue(),        // info_req_enquiry
	                    ((Number) row[10]).longValue(),       // info_pro_enquiry
	                    ((Number) row[11]).longValue(),       // quote_req_enquiry
	                    ((Number) row[12]).longValue(),       // quote_gen_enquiry
	                    ((Number) row[13]).longValue(),       // acc_enquiry
	                    ((Number) row[14]).longValue(),       // rej_enquiry
	                    ((Number) row[15]).longValue(),       // total_quote
	                    ((Number) row[16]).longValue(),       // total_prospect
	                    (String) row[17],					  // month_leads (JSON string)
	                    (String) row[18]					  // month_enquiries (JSON string)
	            ))
	            .toList();

	    return ResponseEntity.ok().body(result);
	}
	
	@GetMapping("/SummaryWithDate")
	public ResponseEntity<?> getLeadSummaryWithDate(
	    @RequestParam("dateFilter") String dateFilter,
	    @RequestParam(value = "from", required = false) Date startDate,
	    @RequestParam(value = "to", required = false) Date endDate, 	    
	    @RequestParam("userSeqNo") int userSeqNo) {

	    try {
	    	String query = "CALL GetLeadSummary(:dateFilter, :startDate, :endDate, :userSeqNo)";
	    	List<Object[]> resultList = em.createNativeQuery(query)
	    	    
	    	    .setParameter("dateFilter", dateFilter)
	    	    .setParameter("startDate", startDate)
	    	    .setParameter("endDate", endDate)
	    	    .setParameter("userSeqNo", userSeqNo)
	    	    .getResultList();


	        if (resultList.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No summary data found.");
	        }

	        List<SummaryDTO> result = resultList.stream()
	            .map(row -> new SummaryDTO(
	                ((Number) row[0]).longValue(),
	                (String) row[1],
	                ((Number) row[2]).longValue(),
	                (BigDecimal) row[3],
	                (BigDecimal) row[4],
	                (BigDecimal) row[5],
	                ((Number) row[6]).longValue(),
	                ((Number) row[7]).longValue(),
	                ((Number) row[8]).longValue(),
	                ((Number) row[9]).longValue(),
	                ((Number) row[10]).longValue(),
	                ((Number) row[11]).longValue(),
	                ((Number) row[12]).longValue(),
	                ((Number) row[13]).longValue(),
	                ((Number) row[14]).longValue(),
	                ((Number) row[15]).longValue(),
	                ((Number) row[16]).longValue(),
	                (String) row[17],
	                (String) row[18]
	            ))
	            .toList();

	        return ResponseEntity.ok(result);
	    } catch (Exception e) {
	        log.error("Error calling GetLeadSummary procedure", e);
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Procedure call failed.");
	    }
	}

	
	public class ActivityLogUtil {
	    public static void createActivityLog(Long userSeqNo, String leadType,String leadName, ActivityLogService activityLogService) {
	        ActivityLogDTO activityLogDTO = new ActivityLogDTO();
	        activityLogDTO.setUserSeqNo(userSeqNo);
	        activityLogDTO.setActivityLogDate(new Date(System.currentTimeMillis()));
	        activityLogDTO.setActivityLogType(leadType);
	        activityLogDTO.setActivityLogDescription(leadName);
	        activityLogService.create(activityLogDTO);
	    }
	}


    
    
    
   
   
}