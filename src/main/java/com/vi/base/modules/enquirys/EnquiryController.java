/*
 - Version Number s0.0.1
*/

package com.vi.base.modules.enquirys;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vi.model.dto.ActivityLogDTO;
import com.vi.model.dto.EnquiryDTO;
import com.vi.base.modules.activitylogs.ActivityLogService;
import com.vi.corelib.filter.Filter;
//import com.vi.corelib.EnquiryInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/Enquiry")
@Slf4j
public class EnquiryController {

	@Autowired
	EnquiryService enquiryService;
	
	@Autowired
	ActivityLogService activityLogService;
	
//	@Autowired
//	ActivityLogDTO activityLogDTO;

	@GetMapping("/all")
	public ResponseEntity<List<EnquiryDTO>> getAll(
			@Nullable @RequestParam HashMap<String, String> json, 
			@RequestParam(value = "page", defaultValue = "0") int page,
	        @RequestParam(value = "size", defaultValue = "1000") int size) {
		    json.remove("page");
		    json.remove("size");
		    json.put("deleted","false");
		JsonNode jsonRequest = new ObjectMapper().convertValue(json, JsonNode.class);

		  System.out.println("Request JSON: " + jsonRequest);
		  System.out.println("Page: " + page + ", Size: " + size);	
		return ResponseEntity.ok().body(enquiryService.filterData(jsonRequest, page, size));
	}

	@GetMapping("/{id}")
	public ResponseEntity<EnquiryDTO> getOne(@PathVariable Long id) {
		var enquiryDTO = enquiryService.get(id);
		return ResponseEntity.ok().body(enquiryDTO);
	}

	@PostMapping("/create")
	public ResponseEntity<EnquiryDTO> create( @RequestBody EnquiryDTO enquiryDTO) {
		var enquiryEnquiryDTO = enquiryService.create(enquiryDTO);
		ActivityLogUtil.createActivityLog(enquiryEnquiryDTO.getEnqProdName(),"Enquiry created: " + enquiryEnquiryDTO.getEnqName(),activityLogService);

		return ResponseEntity.ok().body(enquiryEnquiryDTO);
	}


	@PutMapping("/update")
	public ResponseEntity<EnquiryDTO> update( @RequestBody EnquiryDTO enquiryDTO) {
		
		var enquiryEnquiryDTO = enquiryService.update(enquiryDTO);
		return ResponseEntity.ok().body(enquiryEnquiryDTO);
	}

	@GetMapping("/filter")
	public ResponseEntity<List<EnquiryDTO>> filterData(@RequestParam(value = "search") String search) {
		
		return ResponseEntity.ok().body(enquiryService.filterData(search));
	}

	@GetMapping("/filter2")
	public ResponseEntity<List<EnquiryDTO>> filterData2(
			@RequestParam HashMap<String, Object> json, 
			@RequestParam(value = "page", defaultValue = "0") int page,
	        @RequestParam(value = "size", defaultValue = "1000") int size) {
		    json.remove("page");
		    json.remove("size");
	
		JsonNode jsonRequest = new ObjectMapper().convertValue(json, JsonNode.class);

		  System.out.println("Request JSON: " + jsonRequest);
		  System.out.println("Page: " + page + ", Size: " + size);	
		return ResponseEntity.ok().body(enquiryService.filterData(jsonRequest, page, size));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deleteOne(@PathVariable Long id) {
		return ResponseEntity.ok().body(enquiryService.delete(id));
	}
	
	public class ActivityLogUtil {
	    public static void createActivityLog(String enqProdName,String enqName, ActivityLogService activityLogService) {
	        ActivityLogDTO activityLogDTO = new ActivityLogDTO();
	        activityLogDTO.setActivityLogDate(new Date(System.currentTimeMillis()));
	        activityLogDTO.setActivityLogType(enqProdName);
	        activityLogDTO.setActivityLogDescription(enqName);
	        activityLogService.create(activityLogDTO);
	    }
	}
}
