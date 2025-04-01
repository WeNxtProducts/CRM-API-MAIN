package com.vi.extended.modules.fileattributes;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.vi.model.dto.FileAttributesDTOCustom;
import com.vi.model.dto.SummaryDTO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/FileAttributes")
@Slf4j
public class FileAttributesControllerCustom {

    @Autowired
    private Environment env;

    @Autowired
    private FileAttributesServiceCustom fileAttributesServiceCustom;  // Ensure it's Autowired

    @PersistenceContext
    private EntityManager em;   

    	
    	@PostMapping("/new/uploadMultiple")
    	public ResponseEntity<String> uploadMultipleDocuments(@RequestBody List<Map<String, Object>> fileRequests) {
    		try {
    			String response = fileAttributesServiceCustom.uploadMultipleDocuments(fileRequests);
    			return ResponseEntity.ok(response);
    		} catch (Exception e) {
    			e.printStackTrace();
    			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing file upload.");
    		}
    	}
    	
    	@PostMapping("/retrieve")
    	public ResponseEntity<Map<String, Object>> retriveFiles(@RequestBody List<Map<String, String>> filePaths)
    	        throws IOException {
    	    Map<String, Object> response = new HashMap<>();
    	    List<String> paths = filePaths.stream().map(filePathMap -> filePathMap.get("path")).toList();
    	    List<String> base64List = fileAttributesServiceCustom.getFileBase64Strings(paths);

    	    
    	    response.put("status", "SUCCESS");
    	    response.put("status_msg", "Files processed successfully");
    	    response.put("base64Strings", base64List);  
    	    return ResponseEntity.ok(response);
    	}
    }
