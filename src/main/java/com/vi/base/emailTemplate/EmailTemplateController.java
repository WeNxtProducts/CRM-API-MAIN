//package com.vi.base.emailTemplate;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import jakarta.servlet.http.HttpServletRequest;
//
//@RestController
//@RequestMapping("/emailTemplate")
//public class EmailTemplateController {
//	
//	@Autowired
//	private EmailTemplateService emailTemplateService;
//	
////	@PostMapping("/createTemplate")
////	public String createTemplate(@RequestBody EmailTemplateRequest emailTemplateModel) {
////		try {
////		return emailTemplateService.createNewTemplate(emailTemplateModel);
////		}catch(Exception e) {
////			return e.getMessage();
////		}
////	}
////	
////	@PostMapping("/updateTemplate/{templateId}")
////	public String updateTemplate(@RequestBody EmailTemplateRequest emailTemplateModel, @PathVariable Integer templateId) {
////		return emailTemplateService.updateTemplate(emailTemplateModel, templateId);
////	}
////	
////	@PostMapping("/deleteTemplate/{templateId}")
////	public String deleteTemplate(@PathVariable Integer templateId) {
////		return emailTemplateService.deleteTemplate(templateId);
////	}
////	
////	@GetMapping("/getTemplate")
////	public String getTemplate(HttpServletRequest request, @RequestParam String screenCode, @RequestParam String screenName, @RequestParam Integer tranId) {
////		return emailTemplateService.getTemplate(request, screenCode, screenName, tranId);
////	}
////	
////	@PostMapping("/createTemplateParam/{templateId}")
////	public String createTemplateParam(@RequestBody EmailTemplateRequest emailTemplateModel, @PathVariable Integer templateId) {
////		return emailTemplateService.createTemplateParam(emailTemplateModel, templateId);
////	}
////	
////	@PostMapping("/updateTemplateParam/{pathParamId}")
////	public String updateTemplateParam(@RequestBody EmailTemplateRequest emailTemplateModel, @PathVariable Integer pathParamId) {
////		return emailTemplateService.updateTemplateParam(emailTemplateModel, pathParamId);
////	}
////	
////	@PostMapping("/deleteTemplateParam/{templateId}")
////	public String deleteTemplateParam(@PathVariable Integer templateId) {
////		return emailTemplateService.deleteTemplateParam(templateId);
////	}
////	
////	@GetMapping("/getTemplateParam")
////	public String getTemplateParam(HttpServletRequest request, @RequestParam String screenCode, @RequestParam String screenName, @RequestParam Integer tranId) {
////		return emailTemplateService.getTemplateParam(request, screenCode, screenName, tranId);
////	}
//	
//	@PostMapping("/sendMail")
//	public String sendMail(@RequestParam Integer templateId, @RequestBody EmailRequestModel object, HttpServletRequest request) {
//		try {
//			return emailTemplateService.sendMail(templateId, object, request);
//		} catch (Exception e) {
//			return e.getMessage();
//		}
//	}
//	
//	@GetMapping("/emailQueries")
//	public String emailQueries() {
//		try {
//			return emailTemplateService.getEmailQueries();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return e.getMessage();
//		}
//	}
//	
//	@GetMapping("/getParams")
//	public String getParams() {
//		try {
//			return emailTemplateService.getParams();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return e.getMessage();
//		}
//	}
//
//}
