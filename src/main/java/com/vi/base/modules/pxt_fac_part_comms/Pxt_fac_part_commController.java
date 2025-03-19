/*
 - Version Number 0.0.1
*/

package com.vi.base.modules.pxt_fac_part_comms;

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
import com.vi.model.dto.Pxt_fac_part_commDTO;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/commission")
@Slf4j
public class Pxt_fac_part_commController {

	@Autowired
	Pxt_fac_part_commService pxt_fac_part_commService;

	@GetMapping("/all")
	public ResponseEntity<List<Pxt_fac_part_commDTO>> getAll(@Nullable @RequestParam HashMap<String, String> json) {
		JsonNode jsonRequest = new ObjectMapper().convertValue(json, JsonNode.class);
		return ResponseEntity.ok().body(pxt_fac_part_commService.filterData(jsonRequest));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Pxt_fac_part_commDTO> getOne(@PathVariable Long id) {
		var pxt_fac_part_commDTO = pxt_fac_part_commService.get(id);
		return ResponseEntity.ok().body(pxt_fac_part_commDTO);
	}

	@PostMapping("/create")
	public ResponseEntity<Pxt_fac_part_commDTO> create( @RequestBody Pxt_fac_part_commDTO pxt_fac_part_commDTO) {
		var pxt_fac_part_commPxt_fac_part_commDTO = pxt_fac_part_commService.create(pxt_fac_part_commDTO);
		return ResponseEntity.ok().body(pxt_fac_part_commPxt_fac_part_commDTO);
	}


	@PutMapping("/update")
	public ResponseEntity<Pxt_fac_part_commDTO> update( @RequestBody Pxt_fac_part_commDTO pxt_fac_part_commDTO) {
		
		var pxt_fac_part_commPxt_fac_part_commDTO = pxt_fac_part_commService.update(pxt_fac_part_commDTO);
		return ResponseEntity.ok().body(pxt_fac_part_commPxt_fac_part_commDTO);
	}

	@GetMapping("/filter")
	public ResponseEntity<List<Pxt_fac_part_commDTO>> filterData(@RequestParam(value = "search") String search) {
		
		return ResponseEntity.ok().body(pxt_fac_part_commService.filterData(search));
	}

	@GetMapping("/filter2")
	public ResponseEntity<List<Pxt_fac_part_commDTO>> filterData2(@RequestParam HashMap<String, Object> json) {
	
		JsonNode jsonRequest = new ObjectMapper().convertValue(json, JsonNode.class);
		return ResponseEntity.ok().body(pxt_fac_part_commService.filterData(jsonRequest));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deleteOne(@PathVariable Long id) {
		return ResponseEntity.ok().body(pxt_fac_part_commService.delete(id));
	}
}
