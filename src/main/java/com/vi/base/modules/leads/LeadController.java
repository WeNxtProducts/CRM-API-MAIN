/*
 - Version Number s0.0.1
*/

package com.vi.base.modules.leads;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vi.base.modules.activitylogs.ActivityLogService;
import com.vi.model.dto.ActivityLogDTO;
import com.vi.model.dto.LeadDTO;
import com.vi.model.dto.PaginatedResponse;

//import com.vi.corelib.LeadInfo;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/Lead")
@Slf4j
public class LeadController {

	@Autowired
	LeadService leadService;
	
	@Autowired
	ActivityLogService activityLogService;

	@GetMapping("/all")
	public ResponseEntity<List<LeadDTO>> getAll(
			@Nullable @RequestParam HashMap<String, String> json, 
			@RequestParam(value = "page", defaultValue = "0") int page,
	        @RequestParam(value = "size", defaultValue = "1000") int size) {
		    json.remove("page");
		    json.remove("size");
		    json.put("deleted","false");
		JsonNode jsonRequest = new ObjectMapper().convertValue(json, JsonNode.class);

		  System.out.println("Request JSON: " + jsonRequest);
		  System.out.println("Page: " + page + ", Size: " + size);	
		return ResponseEntity.ok().body(leadService.filterData(jsonRequest, page, size));
	}

	@GetMapping("/{id}")
	public ResponseEntity<LeadDTO> getOne(@PathVariable Long id) {
		var leadDTO = leadService.get(id);
		return ResponseEntity.ok().body(leadDTO);
	}

	@PostMapping("/create")
	public ResponseEntity<LeadDTO> create( @RequestBody LeadDTO leadDTO ) {
		
		var leadLeadDTO = leadService.create(leadDTO); 
			
		ActivityLogUtil.createActivityLog(leadLeadDTO.getUserSeqNo(),"LEAD_CREATED","Lead created: " + leadLeadDTO.getLeadName(),activityLogService);
		
		return ResponseEntity.ok().body(leadLeadDTO);
	}


	@PutMapping("/update")
	public ResponseEntity<LeadDTO> update( @RequestBody LeadDTO leadDTO) {
		
		var leadLeadDTO = leadService.update(leadDTO);
		
		return ResponseEntity.ok().body(leadLeadDTO);
	}

	@GetMapping("/filter")
	public ResponseEntity<List<LeadDTO>> filterData(@RequestParam(value = "search") String search) {
		
		return ResponseEntity.ok().body(leadService.filterData(search));
	}


	@GetMapping("/filter2")
	public ResponseEntity<PaginatedResponse<LeadDTO>> filterData2(
			@RequestParam HashMap<String, Object> json,
			@RequestParam(value = "page", defaultValue = "0") int page,
	        @RequestParam(value = "size", defaultValue = "1000") int size) {
		    json.remove("page");
		    json.remove("size");
		JsonNode jsonRequest = new ObjectMapper().convertValue(json, JsonNode.class);
		
		  System.out.println("Request JSON: " + jsonRequest);
		  System.out.println("Page: " + page + ", Size: " + size);
		  List<LeadDTO> paginatedData = leadService.filterData(jsonRequest, page, size);
		  int totalCount = leadService.filterData(jsonRequest).size();
		  
		  System.out.println("-------------" + totalCount);

//		  List<LeadDTO> response = new PaginatedResponse<>(paginatedData, totalCount);
		  PaginatedResponse<LeadDTO> response = new PaginatedResponse<>(paginatedData, totalCount);
		  return ResponseEntity.ok(response);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deleteOne(@PathVariable Long id) {
		return ResponseEntity.ok().body(leadService.delete(id));
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
