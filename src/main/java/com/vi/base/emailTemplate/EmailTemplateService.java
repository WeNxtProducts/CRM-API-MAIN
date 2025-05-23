package com.vi.base.emailTemplate;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;

public interface EmailTemplateService {

	public String sendMail(EmailRequestModel object, HttpServletRequest request) throws Exception;

//	public  String sendMail(String triggerCode, Long companyId, EmailRequestModel inputObject, HttpServletRequest request) throws Exception;
//	public String startAutoDispatch(String eventId);

	public String getEmailQueries() throws Exception;

	public String getParams() throws Exception;

}
