/*
 - Version Number s0.0.1
*/

package com.vi.base.modules.activitys;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.vi.model.dto.ActivityDTO;
import com.vi.corelib.filter.Filter;
//import com.vi.corelib.ActivityInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Activity")
@Slf4j
public class ActivityController {

	@Autowired
	ActivityService activityService;

	@GetMapping("/all")
	public ResponseEntity<List<ActivityDTO>> getAll(
			@Nullable @RequestParam HashMap<String, String> json, 
			@RequestParam(value = "page", defaultValue = "0") int page,
	        @RequestParam(value = "size", defaultValue = "1000") int size) {
		    json.remove("page");
		    json.remove("size");
		    json.put("deleted","false");
		JsonNode jsonRequest = new ObjectMapper().convertValue(json, JsonNode.class);
		  System.out.println("Request JSON: " + jsonRequest);
		  System.out.println("Page: " + page + ", Size: " + size);	
		return ResponseEntity.ok().body(activityService.filterData(jsonRequest, page, size));
	}
	
	@GetMapping("/calendar")
	public ResponseEntity<Map<String, Object>> getCalendarData(
	        @RequestParam(value = "startDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
	        @RequestParam(value = "endDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
	        @RequestParam(value = "userSeqNo") Long userSeqNo) {

	    return activityService.getCalendarData(startDate, endDate, userSeqNo);
	}


	@GetMapping("/{id}")
	public ResponseEntity<ActivityDTO> getOne(@PathVariable Long id) {
		var activityDTO = activityService.get(id);
		return ResponseEntity.ok().body(activityDTO);
	}

	@PostMapping("/create")
	public ResponseEntity<ActivityDTO> create( @RequestBody ActivityDTO activityDTO) {
		var activityActivityDTO = activityService.create(activityDTO);
		return ResponseEntity.ok().body(activityActivityDTO);
	}


	@PutMapping("/update")
	public ResponseEntity<ActivityDTO> update( @RequestBody ActivityDTO activityDTO) {
		
		var activityActivityDTO = activityService.update(activityDTO);
		return ResponseEntity.ok().body(activityActivityDTO);
	}

	@GetMapping("/filter")
	public ResponseEntity<List<ActivityDTO>> filterData(@RequestParam(value = "search") String search) {
		
		return ResponseEntity.ok().body(activityService.filterData(search));
	}

	@GetMapping("/filter2")
	public ResponseEntity<List<ActivityDTO>> filterData2(
			@RequestParam HashMap<String, Object> json,
			@RequestParam(value = "page", defaultValue = "0") int page,
	        @RequestParam(value = "size", defaultValue = "1000") int size) {
		    json.remove("page");
		    json.remove("size");
	
		JsonNode jsonRequest = new ObjectMapper().convertValue(json, JsonNode.class);
		  System.out.println("Request JSON: " + jsonRequest);
		  System.out.println("Page: " + page + ", Size: " + size);	
		return ResponseEntity.ok().body(activityService.filterData(jsonRequest, page, size));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deleteOne(@PathVariable Long id) {
		return ResponseEntity.ok().body(activityService.delete(id));
	}
}
