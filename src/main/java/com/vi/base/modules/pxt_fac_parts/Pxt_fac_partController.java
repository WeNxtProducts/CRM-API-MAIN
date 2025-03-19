/*
 - Version Number 0.0.1
*/

package com.vi.base.modules.pxt_fac_parts;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.vi.model.dto.Pxt_fac_partDTO;
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
@RequestMapping("/Participant")
@Slf4j
public class Pxt_fac_partController {

	@Autowired
	Pxt_fac_partService pxt_fac_partService;

	@GetMapping("/all")
	public ResponseEntity<List<Pxt_fac_partDTO>> getAll(@Nullable @RequestParam HashMap<String, String> json) {
		JsonNode jsonRequest = new ObjectMapper().convertValue(json, JsonNode.class);
		return ResponseEntity.ok().body(pxt_fac_partService.filterData(jsonRequest));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Pxt_fac_partDTO> getOne(@PathVariable Long id) {
		var pxt_fac_partDTO = pxt_fac_partService.get(id);
		return ResponseEntity.ok().body(pxt_fac_partDTO);
	}

	@PostMapping("/create")
	public ResponseEntity<Pxt_fac_partDTO> create( @RequestBody Pxt_fac_partDTO pxt_fac_partDTO) {
		var pxt_fac_partPxt_fac_partDTO = pxt_fac_partService.create(pxt_fac_partDTO);
		return ResponseEntity.ok().body(pxt_fac_partPxt_fac_partDTO);
	}


	@PutMapping("/update")
	public ResponseEntity<Pxt_fac_partDTO> update( @RequestBody Pxt_fac_partDTO pxt_fac_partDTO) {
		
		var pxt_fac_partPxt_fac_partDTO = pxt_fac_partService.update(pxt_fac_partDTO);
		return ResponseEntity.ok().body(pxt_fac_partPxt_fac_partDTO);
	}

	@GetMapping("/filter")
	public ResponseEntity<List<Pxt_fac_partDTO>> filterData(@RequestParam(value = "search") String search) {
		
		return ResponseEntity.ok().body(pxt_fac_partService.filterData(search));
	}

	@GetMapping("/filter2")
	public ResponseEntity<List<Pxt_fac_partDTO>> filterData2(@RequestParam HashMap<String, Object> json) {
	
		JsonNode jsonRequest = new ObjectMapper().convertValue(json, JsonNode.class);
		return ResponseEntity.ok().body(pxt_fac_partService.filterData(jsonRequest));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deleteOne(@PathVariable Long id) {
		return ResponseEntity.ok().body(pxt_fac_partService.delete(id));
	}
}
