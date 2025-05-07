package com.vi.base.emailTemplate;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/emailTemplate")
public class EmailTemplateController {
	
	@Autowired
	private EmailTemplateService emailTemplateService;
	
	@PostMapping(value = "/sendMail", produces = "application/json")
	public ResponseEntity<Map<String, Object>> sendMail(
	        @RequestBody EmailRequestModel object,
	        HttpServletRequest request) {
	    try {
	        String result = emailTemplateService.sendMail(object, request);

	        Map<String, Object> response = new HashMap<>();
	        response.put("status", "success");
	        response.put("message", result);
	        return ResponseEntity.ok(response);

	    } catch (Exception e) {
	        Map<String, Object> errorResponse = new HashMap<>();
	        errorResponse.put("status", "error");
	        errorResponse.put("message", "Failed to send email");
	        errorResponse.put("details", e.getMessage());
	        return ResponseEntity.badRequest().body(errorResponse);
	    }
	}

	@GetMapping("/emailQueries")
	public String emailQueries() {
		try {
			return emailTemplateService.getEmailQueries();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return e.getMessage();
		}
	}
	
	@GetMapping("/getParams")
	public String getParams() {
		try {
			return emailTemplateService.getParams();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return e.getMessage();
		}
	}

}
