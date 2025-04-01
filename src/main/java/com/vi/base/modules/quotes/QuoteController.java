/*
 - Version Number s0.0.1
*/

package com.vi.base.modules.quotes;

import java.util.ArrayList;
import java.util.Date;
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
import com.vi.model.dto.QuoteDTO;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/Quote")
@Slf4j
public class QuoteController {

	@Autowired
	QuoteService quoteService;
	
	@Autowired
	ActivityLogService activityLogService;

	@GetMapping("/all")
	public ResponseEntity<List<QuoteDTO>> getAll(
			@Nullable @RequestParam HashMap<String, String> json, 
			@RequestParam(value = "page", defaultValue = "0") int page,
	        @RequestParam(value = "size", defaultValue = "1000") int size) {
		    json.remove("page");
		    json.remove("size");
		    json.put("deleted","false");
		JsonNode jsonRequest = new ObjectMapper().convertValue(json, JsonNode.class);

		  System.out.println("Request JSON: " + jsonRequest);
		  System.out.println("Page: " + page + ", Size: " + size);	
		return ResponseEntity.ok().body(quoteService.filterData(jsonRequest, page, size));
	}

	@GetMapping("/{id}")
	public ResponseEntity<QuoteDTO> getOne(@PathVariable Long id) {
		var quoteDTO = quoteService.get(id);
		return ResponseEntity.ok().body(quoteDTO);
	}

	@PostMapping("/create")
	public ResponseEntity<List<QuoteDTO>> create(@RequestBody List<QuoteDTO> quoteDTOList) {
	    List<QuoteDTO> createdQuotes = new ArrayList<>();

	    for (QuoteDTO quoteDTO : quoteDTOList) {
	        QuoteDTO newQuote = new QuoteDTO();
	        newQuote.setEnqSeqNo(quoteDTO.getEnqSeqNo());
	        newQuote.setLeadSeqNo(quoteDTO.getLeadSeqNo());
	        newQuote.setUserSeqNo(quoteDTO.getUserSeqNo());
	        newQuote.setQuoteDescription(quoteDTO.getQuoteDescription());
	        newQuote.setSumInsured(quoteDTO.getSumInsured());
	        newQuote.setLobName(quoteDTO.getLobName());
	        newQuote.setProductName(quoteDTO.getProductName());
	        newQuote.setPremium(quoteDTO.getPremium());
	        newQuote.setQuoteStatus(quoteDTO.getQuoteStatus());
	        newQuote.setCreatedAt(new Date());
	        newQuote.setUpdatedAt(new Date());
	        newQuote.setDeleted(false);

	        QuoteDTO savedQuote = quoteService.create(newQuote);
	        createdQuotes.add(savedQuote);
	    }

	    ActivityLogUtil.createActivityLog("QUOTE_CREATED", "Quotes created", activityLogService);

	    return ResponseEntity.ok().body(createdQuotes);
	}




	@PutMapping("/update")
	public ResponseEntity<QuoteDTO> update( @RequestBody QuoteDTO quoteDTO) {
		
		var quoteQuoteDTO = quoteService.update(quoteDTO);
		
		return ResponseEntity.ok().body(quoteQuoteDTO);
	}

	@GetMapping("/filter")
	public ResponseEntity<List<QuoteDTO>> filterData(@RequestParam(value = "search") String search) {
		
		return ResponseEntity.ok().body(quoteService.filterData(search));
	}

	@GetMapping("/filter2")
	public ResponseEntity<List<QuoteDTO>> filterData2(
			@RequestParam HashMap<String, Object> json,
			@RequestParam(value = "page", defaultValue = "0") int page,
	        @RequestParam(value = "size", defaultValue = "1000") int size) {
		    json.remove("page");
		    json.remove("size");
		JsonNode jsonRequest = new ObjectMapper().convertValue(json, JsonNode.class);
		
		  System.out.println("Request JSON: " + jsonRequest);
		  System.out.println("Page: " + page + ", Size: " + size);		
		  return ResponseEntity.ok().body(quoteService.filterData(jsonRequest, page, size));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deleteOne(@PathVariable Long id) {
		return ResponseEntity.ok().body(quoteService.delete(id));
	}
	
	public class ActivityLogUtil {
	    public static void createActivityLog(String quoteType,String quoteName, ActivityLogService activityLogService) {
	        ActivityLogDTO activityLogDTO = new ActivityLogDTO();
	        activityLogDTO.setActivityLogDate(new Date(System.currentTimeMillis()));
	        activityLogDTO.setActivityLogType(quoteType);
	        activityLogDTO.setActivityLogDescription(quoteName);
	        activityLogService.create(activityLogDTO);
	    }
	}
}
