/*
 - Version Number 0.0.1
*/

package com.vi.base.modules.pxt_fac_rsk_cvrs;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.vi.model.dto.Pxt_fac_rsk_cvrDTO;
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
@RequestMapping("/Pxtfacrskcvr")
@Slf4j
public class Pxt_fac_rsk_cvrController {

	@Autowired   
	Pxt_fac_rsk_cvrService pxt_fac_rsk_cvrService;

	@GetMapping("/all")
	public ResponseEntity<List<Pxt_fac_rsk_cvrDTO>> getAll(@Nullable @RequestParam HashMap<String, String> json) {
		JsonNode jsonRequest = new ObjectMapper().convertValue(json, JsonNode.class);
		return ResponseEntity.ok().body(pxt_fac_rsk_cvrService.filterData(jsonRequest));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Pxt_fac_rsk_cvrDTO> getOne(@PathVariable Long id) {
		var pxt_fac_rsk_cvrDTO = pxt_fac_rsk_cvrService.get(id);
		return ResponseEntity.ok().body(pxt_fac_rsk_cvrDTO);
	}

	@PostMapping("/create")
	public ResponseEntity<Pxt_fac_rsk_cvrDTO> create( @RequestBody Pxt_fac_rsk_cvrDTO pxt_fac_rsk_cvrDTO) {
		var pxt_fac_rsk_cvrPxt_fac_rsk_cvrDTO = pxt_fac_rsk_cvrService.create(pxt_fac_rsk_cvrDTO);
		return ResponseEntity.ok().body(pxt_fac_rsk_cvrPxt_fac_rsk_cvrDTO);
	}


	@PutMapping("/update")
	public ResponseEntity<Pxt_fac_rsk_cvrDTO> update( @RequestBody Pxt_fac_rsk_cvrDTO pxt_fac_rsk_cvrDTO) {
		
		var pxt_fac_rsk_cvrPxt_fac_rsk_cvrDTO = pxt_fac_rsk_cvrService.update(pxt_fac_rsk_cvrDTO);
		return ResponseEntity.ok().body(pxt_fac_rsk_cvrPxt_fac_rsk_cvrDTO);
	}

	@GetMapping("/filter")
	public ResponseEntity<List<Pxt_fac_rsk_cvrDTO>> filterData(@RequestParam(value = "search") String search) {
		
		return ResponseEntity.ok().body(pxt_fac_rsk_cvrService.filterData(search));
	}

	@GetMapping("/filter2")
	public ResponseEntity<List<Pxt_fac_rsk_cvrDTO>> filterData2(@RequestParam HashMap<String, Object> json) {
	
		JsonNode jsonRequest = new ObjectMapper().convertValue(json, JsonNode.class);
		return ResponseEntity.ok().body(pxt_fac_rsk_cvrService.filterData(jsonRequest));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deleteOne(@PathVariable Long id) {
		return ResponseEntity.ok().body(pxt_fac_rsk_cvrService.delete(id));
	}
}
