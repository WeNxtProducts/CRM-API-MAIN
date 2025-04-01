package com.vi.extended.modules.leads;

import java.math.BigDecimal;
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
import com.vi.base.modules.leads.LeadService;
import com.vi.base.modules.users.UserService;
import com.vi.model.dao.LeadDAO;
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
            @RequestParam(value = "size", defaultValue = "1000") int size) {

        try {
            ObjectNode mutableJson = json.deepCopy();
            mutableJson.remove("page");
            mutableJson.remove("size");
            mutableJson.put("deleted","false");

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
    public ResponseEntity<?> getBrokerCode( @RequestParam int userId) {
        String sql = "SELECT * FROM wn_lead_summary WHERE user_seq_no="+ userId;
        List<Object[]> queryResult = em.createNativeQuery(sql).getResultList();
        
        List<SummaryDTO> result = queryResult.stream()
                .map(row -> new SummaryDTO(
                        ((Number) row[0]).longValue(),  // userSeqNo
                        ((Number) row[1]).longValue(),  // totalCount
                        (BigDecimal) row[2],  // newlyAdded
                        (BigDecimal) row[3],  // onPriority
                        (BigDecimal) row[4],  // completed
                        (String) row[5]       // monthLeads (JSON String)
                ))
                .toList();

        return ResponseEntity.ok().body(result);
    }

    
    
    
   
   
}