/*
 - Version Number s0.0.1
*/

package com.vi.base.modules.conversations;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.vi.model.dto.ConversationDTO;
import com.vi.corelib.filter.Filter;
//import com.vi.corelib.ConversationInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/Conversation")
@Slf4j
public class ConversationController {

	@Autowired
	ConversationService conversationService;

	@GetMapping("/all")
	public ResponseEntity<List<ConversationDTO>> getAll(
			@Nullable @RequestParam HashMap<String, String> json, 
			@RequestParam(value = "page", defaultValue = "0") int page,
	        @RequestParam(value = "size", defaultValue = "1000") int size) {
		    json.remove("page");
		    json.remove("size");
		    json.put("deleted","false");
		JsonNode jsonRequest = new ObjectMapper().convertValue(json, JsonNode.class);

		  System.out.println("Request JSON: " + jsonRequest);
		  System.out.println("Page: " + page + ", Size: " + size);	
		return ResponseEntity.ok().body(conversationService.filterData(jsonRequest, page, size));
	}

	@GetMapping("/{id}")
	public ResponseEntity<ConversationDTO> getOne(@PathVariable Long id) {
		var conversationDTO = conversationService.get(id);
		return ResponseEntity.ok().body(conversationDTO);
	}

	@PostMapping("/create")
	public ResponseEntity<ConversationDTO> create( @RequestBody ConversationDTO conversationDTO) {
		
		var conversationConversationDTO = conversationService.create(conversationDTO);
		return ResponseEntity.ok().body(conversationConversationDTO);
	}


	@PutMapping("/update")
	public ResponseEntity<ConversationDTO> update( @RequestBody ConversationDTO conversationDTO) {
		
		var conversationConversationDTO = conversationService.update(conversationDTO);
		return ResponseEntity.ok().body(conversationConversationDTO);
	}

	@GetMapping("/filter")
	public ResponseEntity<List<ConversationDTO>> filterData(@RequestParam(value = "search") String search) {
		
		return ResponseEntity.ok().body(conversationService.filterData(search));
	}

	@GetMapping("/filter2")
	public ResponseEntity<List<ConversationDTO>> filterData2(
			@RequestParam HashMap<String, Object> json, 
			@RequestParam(value = "page", defaultValue = "0") int page,
	        @RequestParam(value = "size", defaultValue = "1000") int size) {
		    json.remove("page");
		    json.remove("size");
	
		JsonNode jsonRequest = new ObjectMapper().convertValue(json, JsonNode.class);
		return ResponseEntity.ok().body(conversationService.filterData(jsonRequest, page, size));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deleteOne(@PathVariable Long id) {
		return ResponseEntity.ok().body(conversationService.delete(id));
	}
}
