/*
 - Version Number 0.0.1
*/

package com.vi.base.modules.pxt_uw_css;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.vi.model.dto.Pxt_uw_csDTO;
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
@RequestMapping("/PolicyMaster")
@Slf4j
public class Pxt_uw_csController {

	@Autowired
	Pxt_uw_csService pxt_uw_csService;

	@GetMapping("/all")
	public ResponseEntity<List<Pxt_uw_csDTO>> getAll(@Nullable @RequestParam HashMap<String, String> json) {
		JsonNode jsonRequest = new ObjectMapper().convertValue(json, JsonNode.class);
		return ResponseEntity.ok().body(pxt_uw_csService.filterData(jsonRequest));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Pxt_uw_csDTO> getOne(@PathVariable Long id) {
		var pxt_uw_csDTO = pxt_uw_csService.get(id);
		return ResponseEntity.ok().body(pxt_uw_csDTO);
	}
	
	@PostMapping("/create")
	public ResponseEntity<Pxt_uw_csDTO> create( @RequestBody Pxt_uw_csDTO pxt_uw_csDTO) {
		var pxt_uw_csPxt_uw_csDTO = pxt_uw_csService.create(pxt_uw_csDTO);
		return ResponseEntity.ok().body(pxt_uw_csPxt_uw_csDTO);
	}


	@PutMapping("/update")
	public ResponseEntity<Pxt_uw_csDTO> update( @RequestBody Pxt_uw_csDTO pxt_uw_csDTO) {
		
		var pxt_uw_csPxt_uw_csDTO = pxt_uw_csService.update(pxt_uw_csDTO);
		return ResponseEntity.ok().body(pxt_uw_csPxt_uw_csDTO);
	}

	@GetMapping("/filter")
	public ResponseEntity<List<Pxt_uw_csDTO>> filterData(@RequestParam(value = "search") String search) {
		
		return ResponseEntity.ok().body(pxt_uw_csService.filterData(search));
	}

	@GetMapping("/filter2")
	public ResponseEntity<List<Pxt_uw_csDTO>> filterData2(@RequestParam HashMap<String, Object> json) {
	
		JsonNode jsonRequest = new ObjectMapper().convertValue(json, JsonNode.class);
		return ResponseEntity.ok().body(pxt_uw_csService.filterData(jsonRequest));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deleteOne(@PathVariable Long id) {
		return ResponseEntity.ok().body(pxt_uw_csService.delete(id));
	}
}
