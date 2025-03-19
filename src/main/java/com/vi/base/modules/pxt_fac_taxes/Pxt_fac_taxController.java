/*
 - Version Number 0.0.1
*/

package com.vi.base.modules.pxt_fac_taxes;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.vi.model.dto.Pxt_fac_taxDTO;
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
@RequestMapping("/tax")
@Slf4j
public class Pxt_fac_taxController {

	@Autowired
	Pxt_fac_taxService pxt_fac_taxService;

	@GetMapping("/all")
	public ResponseEntity<List<Pxt_fac_taxDTO>> getAll(@Nullable @RequestParam HashMap<String, String> json) {
		JsonNode jsonRequest = new ObjectMapper().convertValue(json, JsonNode.class);
		return ResponseEntity.ok().body(pxt_fac_taxService.filterData(jsonRequest));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Pxt_fac_taxDTO> getOne(@PathVariable Long id) {
		var pxt_fac_taxDTO = pxt_fac_taxService.get(id);
		return ResponseEntity.ok().body(pxt_fac_taxDTO);
	}

	@PostMapping("/create")
	public ResponseEntity<Pxt_fac_taxDTO> create( @RequestBody Pxt_fac_taxDTO pxt_fac_taxDTO) {
		var pxt_fac_taxPxt_fac_taxDTO = pxt_fac_taxService.create(pxt_fac_taxDTO);
		return ResponseEntity.ok().body(pxt_fac_taxPxt_fac_taxDTO);
	}


	@PutMapping("/update")
	public ResponseEntity<Pxt_fac_taxDTO> update( @RequestBody Pxt_fac_taxDTO pxt_fac_taxDTO) {
		
		var pxt_fac_taxPxt_fac_taxDTO = pxt_fac_taxService.update(pxt_fac_taxDTO);
		return ResponseEntity.ok().body(pxt_fac_taxPxt_fac_taxDTO);
	}

	@GetMapping("/filter")
	public ResponseEntity<List<Pxt_fac_taxDTO>> filterData(@RequestParam(value = "search") String search) {
		
		return ResponseEntity.ok().body(pxt_fac_taxService.filterData(search));
	}

	@GetMapping("/filter2")
	public ResponseEntity<List<Pxt_fac_taxDTO>> filterData2(@RequestParam HashMap<String, Object> json) {
	
		JsonNode jsonRequest = new ObjectMapper().convertValue(json, JsonNode.class);
		return ResponseEntity.ok().body(pxt_fac_taxService.filterData(jsonRequest));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deleteOne(@PathVariable Long id) {
		return ResponseEntity.ok().body(pxt_fac_taxService.delete(id));
	}
}
