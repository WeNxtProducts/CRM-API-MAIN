/*
 - Version Number s0.0.1
*/

package com.vi.base.modules.enquirys;

import java.sql.Date;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
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
import com.vi.base.modules.quotes.QuoteRepository;
import com.vi.base.modules.quotes.QuoteService;
import com.vi.model.dao.LeadDAO;
import com.vi.model.dao.QuoteDAO;
import com.vi.model.dto.ActivityLogDTO;
import com.vi.model.dto.EnquiryDTO;
import com.vi.model.dto.QuoteDTO;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/Enquiry")
@Slf4j
public class EnquiryController {

	@Autowired
	EnquiryService enquiryService;

	@Autowired
	ActivityLogService activityLogService;

	@Autowired
	QuoteService quoteService;

	@Autowired
	EnquiryRepository enquiryRepository;
	
	@Autowired
	QuoteRepository quoteRepository;
	
	@Autowired
	LeadRepository leadRepository;

	@GetMapping("/all")
	public ResponseEntity<List<EnquiryDTO>> getAll(@Nullable @RequestParam HashMap<String, String> json,
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "size", defaultValue = "1000") int size) {
		json.remove("page");
		json.remove("size");
		json.put("deleted", "false");
		JsonNode jsonRequest = new ObjectMapper().convertValue(json, JsonNode.class);

		System.out.println("Request JSON: " + jsonRequest);
		System.out.println("Page: " + page + ", Size: " + size);
		return ResponseEntity.ok().body(enquiryService.filterData(jsonRequest, page, size));
	}

	@GetMapping("/{id}")
	public ResponseEntity<EnquiryDTO> getOne(@PathVariable Long id) {
		var enquiryDTO = enquiryService.get(id);
		return ResponseEntity.ok().body(enquiryDTO);
	}

	@PostMapping("/create")
	public ResponseEntity<EnquiryDTO> create(@RequestBody EnquiryDTO enquiryDTO) {

		var enquiryEnquiryDTO = enquiryService.create(enquiryDTO);
	    
	    if(enquiryDTO.getLeadSeqNo() != null) {
	        LeadDAO lead = leadRepository.findById(enquiryDTO.getLeadSeqNo())
	            .orElseThrow(() -> new ResourceNotFoundException("Lead not found with id: " + enquiryDTO.getLeadSeqNo()));
	        
	        lead.setEnqCount(lead.getEnqCount() + 1);
	        lead.setLeadStatus("In-progress");
	        lead.setLeadUpdatedDate(Date.from(Instant.now()));
	        leadRepository.save(lead);
	    }
	    
	    
	    ActivityLogUtil.createActivityLog(enquiryEnquiryDTO.getUserSeqNo(), enquiryEnquiryDTO.getEnqProdName(),
	            "Enquiry created: " + enquiryEnquiryDTO.getEnqName(), activityLogService);

	    return ResponseEntity.ok().body(enquiryEnquiryDTO);
	}

	@PutMapping("/update")
	public ResponseEntity<EnquiryDTO> update(@RequestBody EnquiryDTO enquiryDTO) {

	    // 1. Set updated date
	    enquiryDTO.setEnqUpdatedDate(Date.from(Instant.now()));

	    // 2. Update the enquiry
	    EnquiryDTO updatedEnquiry = enquiryService.update(enquiryDTO);
	    System.out.println("log: " + updatedEnquiry);

	    // 3. If enquiry ID is present, proceed with quote updates
	    if (enquiryDTO.getEnqSeqNo() != null) {

	        // Map enquiry status to quote status
	        String status = mapEnquiryStatusToQuoteStatus(enquiryDTO.getEnqStatus());

	        // Case 1: Enquiry Rejected → reject ALL related quotes
	        if ("rejEnq".equalsIgnoreCase(enquiryDTO.getEnqStatus())) {
	            List<QuoteDAO> allQuotes = quoteRepository.findByEnqSeqNo(enquiryDTO.getEnqSeqNo());
	            for (QuoteDAO quote : allQuotes) {
	                quote.setQuoteStatus("rejEnq");
	            }
	            quoteRepository.saveAll(allQuotes);
	        }

	        // Case 2: Update specific quote status using quoteSeqNo
	        if (enquiryDTO.getQuoteSeqNo() != null) {
	            Optional<QuoteDAO> optionalQuote = quoteRepository.findByQuoteSeqNo(enquiryDTO.getQuoteSeqNo());

	            if (optionalQuote.isPresent()) {
	                QuoteDAO quote = optionalQuote.get();
	                quote.setQuoteStatus(status);
	                QuoteDAO savedQuote = quoteRepository.save(quote);
	                updatedEnquiry.setQuoteDAO(savedQuote);
	            } else {
	                System.out.println("Quote not found with ID: " + enquiryDTO.getQuoteSeqNo());
	            }
	        } else {
	            System.out.println("QuoteSeqNo is null. Skipping quote update.");
	        }

	        // Optional: Lead status update for accEnq or rejEnq (you can uncomment if needed)
	        
	        if ("accEnq".equalsIgnoreCase(enquiryDTO.getEnqStatus()) || "rejEnq".equalsIgnoreCase(enquiryDTO.getEnqStatus())) {
	            LeadDAO lead = leadRepository.findById(enquiryDTO.getLeadSeqNo())
	                .orElseThrow(() -> new ResourceNotFoundException("Lead not found with id: " + enquiryDTO.getLeadSeqNo()));

	            lead.setLeadStatus("Done");
	            lead.setLeadUpdatedDate(Date.from(Instant.now()));
	            leadRepository.save(lead);
	        }
	        
	    }

	    // 4. Return response
	    return ResponseEntity.ok().body(updatedEnquiry);
	}


	// Status Mapping Method
	private String mapEnquiryStatusToQuoteStatus(String enqStatus) {
	    if (enqStatus == null)
	        return null;

	    switch (enqStatus) {
	        case "pending": 
	        	return "pending";
	        case "meetingReq": 
	        	return "meetingReq";
	        case "infoReq": 
	        	return "infoReq";
	        case "infoPro": 
	        	return "infoPro";
	        case "quoteReq": 
	        	return "quoteReq";
	        case "quoteGen": 
	        	return "quoteGen";
	        case "accEnq": 
	        	return "accEnq";
	        case "rejEnq": 
	        	return "rejEnq";
	        default: 
	        	return enqStatus;
	    }
	}


	@GetMapping("/filter")
	public ResponseEntity<List<EnquiryDTO>> filterData(@RequestParam(value = "search") String search) {
		return ResponseEntity.ok().body(enquiryService.filterData(search));
	}

	@GetMapping("/filter2")
	public ResponseEntity<List<EnquiryDTO>> filterData2(@RequestParam HashMap<String, Object> json,
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "size", defaultValue = "1000") int size) {
		json.remove("page");
		json.remove("size");

		JsonNode jsonRequest = new ObjectMapper().convertValue(json, JsonNode.class);

		System.out.println("Request JSON: " + jsonRequest);
		System.out.println("Page: " + page + ", Size: " + size);
		return ResponseEntity.ok().body(enquiryService.filterData(jsonRequest, page, size));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deleteOne(@PathVariable Long id) {
		return ResponseEntity.ok().body(enquiryService.delete(id));
	}
	
	public class ActivityLogUtil {
		public static void createActivityLog(Long userSeqNo, String enqProdName, String enqName,
				ActivityLogService activityLogService) {
			ActivityLogDTO activityLogDTO = new ActivityLogDTO();
	        activityLogDTO.setUserSeqNo(userSeqNo);
			activityLogDTO.setActivityLogDate(new Date(System.currentTimeMillis()));
			activityLogDTO.setActivityLogType(enqProdName);
			activityLogDTO.setActivityLogDescription(enqName);
			activityLogService.create(activityLogDTO);
		}
	}
}