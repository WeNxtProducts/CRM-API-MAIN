/*
 - Version Number s0.0.1
*/

package com.vi.base.modules.quotes;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.kafka.common.errors.ResourceNotFoundException;
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
import com.vi.base.modules.leads.LeadRepository;
import com.vi.model.dao.LeadDAO;
import com.vi.model.dao.QuoteDAO;
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
	QuoteRepository quoteRepository;
	
	@Autowired
	ActivityLogService activityLogService;
	
	@Autowired
	LeadRepository leadRepository;

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

	    if (!quoteDTOList.isEmpty()) {
	        Long enqSeqNo = quoteDTOList.get(0).getEnqSeqNo();

	        // ✅ Step 1: Find all existing quotes for this EnqSeqNo
	        List<QuoteDAO> existingQuotes = quoteRepository.findByEnqSeqNo(enqSeqNo);

	        // ✅ Step 2: Mark all old quotes (Todo, accept, reject) as "done"
	        for (QuoteDAO quote : existingQuotes) {
	            String status = quote.getIsAccepted();
	            if ("Todo".equalsIgnoreCase(status) || "accept".equalsIgnoreCase(status) || "reject".equalsIgnoreCase(status)) {
	                quote.setIsDone("done"); 
	            }
	        }

	        // ✅ Step 3: Save updated old quotes
	        quoteRepository.saveAll(existingQuotes);
	    }

	    // 🔁 Step 4: Create new quotes
	    for (QuoteDTO quoteDTO : quoteDTOList) {
	        QuoteDTO newQuote = new QuoteDTO();
	        newQuote.setEnqSeqNo(quoteDTO.getEnqSeqNo());
	        newQuote.setSaleId(quoteDTO.getSaleId());
	        newQuote.setLeadSeqNo(quoteDTO.getLeadSeqNo());
	        newQuote.setUserSeqNo(quoteDTO.getUserSeqNo());
	        newQuote.setQuoteDescription(quoteDTO.getQuoteDescription());
	        newQuote.setSumInsured(quoteDTO.getSumInsured());
	        newQuote.setLobName(quoteDTO.getLobName());
	        newQuote.setProductName(quoteDTO.getProductName());
	        newQuote.setPremium(quoteDTO.getPremium());
	        newQuote.setIsAccepted("Todo"); 
	        newQuote.setQuoteStatus("quoteReq");
	        newQuote.setQuoteCreatedDate(new Date());
	        newQuote.setQuoteUpdatedDate(new Date());
	        newQuote.setQuoteCreatedBy(quoteDTO.getQuoteCreatedBy());
	        newQuote.setQuoteUpdatedBy(quoteDTO.getQuoteUpdatedBy());
	        newQuote.setDeleted(false);
	        newQuote.setDeletedAt(quoteDTO.getDeletedAt());
	        newQuote.setDeletedBy(quoteDTO.getDeletedBy());

	        QuoteDTO savedQuote = quoteService.create(newQuote);
	        createdQuotes.add(savedQuote);

	        ActivityLogUtil.createActivityLog(savedQuote.getUserSeqNo(), "QUOTE_CREATED", "Quotes created", activityLogService);
	    }

	    return ResponseEntity.ok().body(createdQuotes);
	}


	@PutMapping("/update")
	public ResponseEntity<QuoteDTO> update(@RequestBody QuoteDTO quoteDTO) {

	    if (quoteDTO.getEnqSeqNo() != null) {

	        // 1. Update Lead if status is accEnq or rejEnq
	        if ("accEnq".equals(quoteDTO.getQuoteStatus()) || "rejEnq".equals(quoteDTO.getQuoteStatus())) {

	            LeadDAO lead = leadRepository.findById(quoteDTO.getLeadSeqNo())
	                .orElseThrow(() -> new ResourceNotFoundException(
	                    "Lead not found with id: " + quoteDTO.getLeadSeqNo()));

	            lead.setLeadStatus("Done");
	            lead.setLeadUpdatedDate(Date.from(Instant.now()));
	            leadRepository.save(lead);
	        }

	        // 2. Update the current quote
	        QuoteDTO updatedQuote = quoteService.update(quoteDTO);

	        // 3. Auto-reject other quotes for same EnqSeqNo
	        if ("accEnq".equals(quoteDTO.getQuoteStatus())) {
	            List<QuoteDAO> otherQuotes = quoteRepository.findByEnqSeqNo(quoteDTO.getEnqSeqNo()).stream()
	                .filter(q -> !q.getQuoteSeqNo().equals(quoteDTO.getQuoteSeqNo())) 
	                .collect(Collectors.toList());

	            for (QuoteDAO q : otherQuotes) {
	                if (!"rejEnq".equalsIgnoreCase(q.getQuoteStatus())) {
	                    q.setQuoteStatus("rejEnq");
	                }
	            }
	            
	            quoteRepository.saveAll(otherQuotes);
	        }

	        return ResponseEntity.ok().body(updatedQuote);
	    }

	    return ResponseEntity.badRequest().build();
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
	    public static void createActivityLog(Long userSeqNo, String quoteType,String quoteName, ActivityLogService activityLogService) {
	        ActivityLogDTO activityLogDTO = new ActivityLogDTO();
	        activityLogDTO.setUserSeqNo(userSeqNo);
	        activityLogDTO.setActivityLogDate(new Date(System.currentTimeMillis()));
	        activityLogDTO.setActivityLogType(quoteType);
	        activityLogDTO.setActivityLogDescription(quoteName);
	        activityLogService.create(activityLogDTO);
	    }
	}
}
