/*
 - Version Number s0.0.1
*/

package com.vi.base.modules.ljmemailtemplates;

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
import com.vi.model.dto.LjmEmailTemplateDTO;

//import com.vi.corelib.LjmEmailTemplateInfo;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/LjmEmailTemplate")
@Slf4j
public class LjmEmailTemplateController {

	@Autowired
	LjmEmailTemplateService ljmEmailTemplateService;
	
	@Autowired
	ActivityLogService activityLogService;

	@GetMapping("/all")
	public ResponseEntity<List<LjmEmailTemplateDTO>> getAll(
			@Nullable @RequestParam HashMap<String, String> json, 
			@RequestParam(value = "page", defaultValue = "0") int page,
	        @RequestParam(value = "size", defaultValue = "1000") int size) {
		    json.remove("page");
		    json.remove("size");
		    json.put("deleted","false");
		JsonNode jsonRequest = new ObjectMapper().convertValue(json, JsonNode.class);

		  System.out.println("Request JSON: " + jsonRequest);
		  System.out.println("Page: " + page + ", Size: " + size);	
		return ResponseEntity.ok().body(ljmEmailTemplateService.filterData(jsonRequest, page, size));
	}

	@GetMapping("/{id}")
	public ResponseEntity<LjmEmailTemplateDTO> getOne(@PathVariable Long id) {
		var ljmEmailTemplateDTO = ljmEmailTemplateService.get(id);
		return ResponseEntity.ok().body(ljmEmailTemplateDTO);
	}

	@PostMapping("/create")
	public ResponseEntity<LjmEmailTemplateDTO> create( @RequestBody LjmEmailTemplateDTO ljmEmailTemplateDTO ) {
		
		var ljmEmailTemplateLjmEmailTemplateDTO = ljmEmailTemplateService.create(ljmEmailTemplateDTO); 
					
		return ResponseEntity.ok().body(ljmEmailTemplateLjmEmailTemplateDTO);
	}


	@PutMapping("/update")
	public ResponseEntity<LjmEmailTemplateDTO> update( @RequestBody LjmEmailTemplateDTO ljmEmailTemplateDTO) {
		
		var ljmEmailTemplateLjmEmailTemplateDTO = ljmEmailTemplateService.update(ljmEmailTemplateDTO);
		
		return ResponseEntity.ok().body(ljmEmailTemplateLjmEmailTemplateDTO);
	}

	@GetMapping("/filter")
	public ResponseEntity<List<LjmEmailTemplateDTO>> filterData(@RequestParam(value = "search") String search) {
		
		return ResponseEntity.ok().body(ljmEmailTemplateService.filterData(search));
	}


	@GetMapping("/filter2")
	public ResponseEntity<List<LjmEmailTemplateDTO>> filterData2(
			@RequestParam HashMap<String, Object> json,
			@RequestParam(value = "page", defaultValue = "0") int page,
	        @RequestParam(value = "size", defaultValue = "1000") int size) {
		    json.remove("page");
		    json.remove("size");
		JsonNode jsonRequest = new ObjectMapper().convertValue(json, JsonNode.class);
		
		  System.out.println("Request JSON: " + jsonRequest);
		  System.out.println("Page: " + page + ", Size: " + size);		
		  return ResponseEntity.ok().body(ljmEmailTemplateService.filterData(jsonRequest, page, size));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deleteOne(@PathVariable Long id) {
		return ResponseEntity.ok().body(ljmEmailTemplateService.delete(id));
	}
	
}
