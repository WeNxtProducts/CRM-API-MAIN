/*
 - Version Number 0.0.1
*/

package com.vi.base.modules.pxt_fac_place_dfns;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.vi.model.dto.Pxt_fac_place_dfnDTO;
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
@RequestMapping("/Placement")
@Slf4j
public class Pxt_fac_place_dfnController {

	@Autowired   
	Pxt_fac_place_dfnService pxt_fac_place_dfnService;

	@GetMapping("/all")
	public ResponseEntity<List<Pxt_fac_place_dfnDTO>> getAll(@Nullable @RequestParam HashMap<String, String> json) {
		JsonNode jsonRequest = new ObjectMapper().convertValue(json, JsonNode.class);
		return ResponseEntity.ok().body(pxt_fac_place_dfnService.filterData(jsonRequest));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Pxt_fac_place_dfnDTO> getOne(@PathVariable Long id) {
		var pxt_fac_place_dfnDTO = pxt_fac_place_dfnService.get(id);
		return ResponseEntity.ok().body(pxt_fac_place_dfnDTO);
	}

	@PostMapping("/create")
	public ResponseEntity<Pxt_fac_place_dfnDTO> create( @RequestBody Pxt_fac_place_dfnDTO pxt_fac_place_dfnDTO) {
		var pxt_fac_place_dfnPxt_fac_place_dfnDTO = pxt_fac_place_dfnService.create(pxt_fac_place_dfnDTO);
		return ResponseEntity.ok().body(pxt_fac_place_dfnPxt_fac_place_dfnDTO);
	}


	@PutMapping("/update")
	public ResponseEntity<Pxt_fac_place_dfnDTO> update( @RequestBody Pxt_fac_place_dfnDTO pxt_fac_place_dfnDTO) {
		
		var pxt_fac_place_dfnPxt_fac_place_dfnDTO = pxt_fac_place_dfnService.update(pxt_fac_place_dfnDTO);
		return ResponseEntity.ok().body(pxt_fac_place_dfnPxt_fac_place_dfnDTO);
	}

	@GetMapping("/filter")
	public ResponseEntity<List<Pxt_fac_place_dfnDTO>> filterData(@RequestParam(value = "search") String search) {
		
		return ResponseEntity.ok().body(pxt_fac_place_dfnService.filterData(search));
	}

	@GetMapping("/filter2")
	public ResponseEntity<List<Pxt_fac_place_dfnDTO>> filterData2(@RequestParam HashMap<String, Object> json) {
	
		JsonNode jsonRequest = new ObjectMapper().convertValue(json, JsonNode.class);
		return ResponseEntity.ok().body(pxt_fac_place_dfnService.filterData(jsonRequest));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deleteOne(@PathVariable Long id) {
		return ResponseEntity.ok().body(pxt_fac_place_dfnService.delete(id));
	}
}
