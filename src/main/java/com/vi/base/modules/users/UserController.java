/*
 - Version Number s0.0.1
*/

package com.vi.base.modules.users;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.vi.model.dto.UserDTO;
import com.vi.corelib.filter.Filter;
import com.vi.corelib.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/User")
@Slf4j
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping("/all")
	public ResponseEntity<List<UserDTO>> getAll(
			@Nullable @RequestParam HashMap<String, String> json,
			@RequestParam(value = "page", defaultValue = "0") int page,
	        @RequestParam(value = "size", defaultValue = "1000") int size) {
		    json.remove("page");
		    json.remove("size");
		    json.put("deleted","false");
		JsonNode jsonRequest = new ObjectMapper().convertValue(json, JsonNode.class);

		  System.out.println("Request JSON: " + jsonRequest);
		  System.out.println("Page: " + page + ", Size: " + size);	
		return ResponseEntity.ok().body(userService.filterData(jsonRequest, page, size));
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> getOne(@PathVariable Long id) {
		var userDTO = userService.get(id);
		return ResponseEntity.ok().body(userDTO);
	}

	@PostMapping("/create")
	public ResponseEntity<UserDTO> create( @RequestBody UserDTO userDTO) {
		var userUserDTO = userService.create(userDTO);
		return ResponseEntity.ok().body(userUserDTO);
	}


	@PutMapping("/update")
	public ResponseEntity<UserDTO> update( @RequestBody UserDTO userDTO) {
		
		var userUserDTO = userService.update(userDTO);
		return ResponseEntity.ok().body(userUserDTO);
	}

	@GetMapping("/filter")
	public ResponseEntity<List<UserDTO>> filterData(@RequestParam(value = "search") String search) {
		
		return ResponseEntity.ok().body(userService.filterData(search));
	}

	@GetMapping("/filter2")
	public ResponseEntity<List<UserDTO>> filterData2(
			@RequestParam HashMap<String, Object> json, 
			@RequestParam(value = "page", defaultValue = "0") int page,
	        @RequestParam(value = "size", defaultValue = "1000") int size) {
		    json.remove("page");
		    json.remove("size");
		JsonNode jsonRequest = new ObjectMapper().convertValue(json, JsonNode.class);

		  System.out.println("Request JSON: " + jsonRequest);
		  System.out.println("Page: " + page + ", Size: " + size);	
		return ResponseEntity.ok().body(userService.filterData(jsonRequest, page, size));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deleteOne(@PathVariable Long id) {
		return ResponseEntity.ok().body(userService.delete(id));
	}
}
