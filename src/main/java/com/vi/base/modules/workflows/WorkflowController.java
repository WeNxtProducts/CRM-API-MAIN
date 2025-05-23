/*
1 - Version Number s0.0.1
*/

package com.vi.base.modules.workflows;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.vi.model.dto.WorkflowDTO;
import com.vi.corelib.filter.Filter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/Workflow")
@Slf4j
public class WorkflowController {

	@Autowired
	WorkflowService workflowService;

	@GetMapping("/all")
	public ResponseEntity<List<WorkflowDTO>> getAll(
			@Nullable @RequestParam HashMap<String, String> json,
			@RequestParam(value = "page", defaultValue = "0") int page,
	        @RequestParam(value = "size", defaultValue = "1000") int size) {
		    json.remove("page");
		    json.remove("size");
		    json.put("deleted","false");
		JsonNode jsonRequest = new ObjectMapper().convertValue(json, JsonNode.class);

		  System.out.println("Request JSON: " + jsonRequest);
		  System.out.println("Page: " + page + ", Size: " + size);	
		return ResponseEntity.ok().body(workflowService.filterData(jsonRequest, page, size));
	}

	@GetMapping("/{id}")
	public ResponseEntity<WorkflowDTO> getOne(@PathVariable Long id) {
		var workflowDTO = workflowService.get(id);
		return ResponseEntity.ok().body(workflowDTO);
	}

	@PostMapping("/create")
	public ResponseEntity<WorkflowDTO> create( @RequestBody WorkflowDTO workflowDTO) {
		var workflowWorkflowDTO = workflowService.create(workflowDTO);
		return ResponseEntity.ok().body(workflowWorkflowDTO);
	}


	@PutMapping("/update")
	public ResponseEntity<WorkflowDTO> update( @RequestBody WorkflowDTO workflowDTO) {
		
		var workflowWorkflowDTO = workflowService.update(workflowDTO);
		return ResponseEntity.ok().body(workflowWorkflowDTO);
	}

	@GetMapping("/filter")
	public ResponseEntity<List<WorkflowDTO>> filterData(@RequestParam(value = "search") String search) {
		
		return ResponseEntity.ok().body(workflowService.filterData(search));
	}

	@GetMapping("/filter2")
	public ResponseEntity<List<WorkflowDTO>> filterData2(
			@RequestParam HashMap<String, Object> json, 
			@RequestParam(value = "page", defaultValue = "0") int page,
	        @RequestParam(value = "size", defaultValue = "1000") int size) {
		    json.remove("page");
		    json.remove("size");
		JsonNode jsonRequest = new ObjectMapper().convertValue(json, JsonNode.class);

		  System.out.println("Request JSON: " + jsonRequest);
		  System.out.println("Page: " + page + ", Size: " + size);	
		return ResponseEntity.ok().body(workflowService.filterData(jsonRequest, page, size));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deleteOne(@PathVariable Long id) {
		return ResponseEntity.ok().body(workflowService.delete(id));
	}
}
