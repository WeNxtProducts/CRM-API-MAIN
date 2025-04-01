/*
 - Version Number s0.0.1
*/

package com.vi.base.modules.activitylogs;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.vi.model.dto.ActivityLogDTO;
import com.vi.corelib.filter.Filter;
//import com.vi.corelib.ActivityLogInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/ActivityLog")
@Slf4j
public class ActivityLogController {

	@Autowired
	ActivityLogService activityLogService;

	@GetMapping("/all")
	public ResponseEntity<List<ActivityLogDTO>> getAll(
			@Nullable @RequestParam HashMap<String, String> json, @RequestParam(value = "page", defaultValue = "0") int page,
	        @RequestParam(value = "size", defaultValue = "1000") int size) {
		    json.remove("page");
		    json.remove("size");
		    json.put("deleted","false");
		JsonNode jsonRequest = new ObjectMapper().convertValue(json, JsonNode.class);

		  System.out.println("Request JSON: " + jsonRequest);
		  System.out.println("Page: " + page + ", Size: " + size);	
		return ResponseEntity.ok().body(activityLogService.filterData(jsonRequest, page, size));
	}

	@GetMapping("/{id}")
	public ResponseEntity<ActivityLogDTO> getOne(@PathVariable Long id) {
		var activityLogDTO = activityLogService.get(id);
		return ResponseEntity.ok().body(activityLogDTO);
	}

	@PostMapping("/create")
	public ResponseEntity<ActivityLogDTO> create( @RequestBody ActivityLogDTO activityLogDTO) {
		var activityLogActivityLogDTO = activityLogService.create(activityLogDTO);
		return ResponseEntity.ok().body(activityLogActivityLogDTO);
	}

	@PutMapping("/update")
	public ResponseEntity<ActivityLogDTO> update( @RequestBody ActivityLogDTO activityLogDTO) {
		
		var activityLogActivityLogDTO = activityLogService.update(activityLogDTO);
		return ResponseEntity.ok().body(activityLogActivityLogDTO);
	}

	@GetMapping("/filter")
	public ResponseEntity<List<ActivityLogDTO>> filterData(@RequestParam(value = "search") String search) {
		
		return ResponseEntity.ok().body(activityLogService.filterData(search));
	}

	@GetMapping("/filter2")
	public ResponseEntity<List<ActivityLogDTO>> filterData2(
			@RequestParam HashMap<String, Object> json,
			@RequestParam(value = "page", defaultValue = "0") int page,
	        @RequestParam(value = "size", defaultValue = "1000") int size) {
		    json.remove("page");
		    json.remove("size");
	
		JsonNode jsonRequest = new ObjectMapper().convertValue(json, JsonNode.class);

		  System.out.println("Request JSON: " + jsonRequest);
		  System.out.println("Page: " + page + ", Size: " + size);	
		return ResponseEntity.ok().body(activityLogService.filterData(jsonRequest, page, size));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deleteOne(@PathVariable Long id) {
		return ResponseEntity.ok().body(activityLogService.delete(id));
	}
}
