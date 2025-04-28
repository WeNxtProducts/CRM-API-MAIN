//package com.vi.base.emailTemplate;
//
//import java.lang.reflect.Field;
//import java.time.LocalDateTime;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Properties;
//import java.util.Set;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.jdbc.support.rowset.SqlRowSet;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//import com.vi.base.commonUtils.CommonDao;
//import com.vi.base.commonUtils.CommonService;
//import com.vi.base.commonUtils.LOVDTO;
//import com.vi.base.commonUtils.QUERY_MASTER;
//import com.vi.base.commonUtils.QueryParamMasterDTO;
//import com.vi.base.logging.EmailLogsDTO;
//import com.vi.model.dao.LjmEmailTemplateDAO;
//
//import jakarta.activation.DataHandler;
//import jakarta.mail.Message;
//import jakarta.mail.Multipart;
//import jakarta.mail.PasswordAuthentication;
//import jakarta.mail.Session;
//import jakarta.mail.Transport;
//import jakarta.mail.internet.InternetAddress;
//import jakarta.mail.internet.MimeBodyPart;
//import jakarta.mail.internet.MimeMessage;
//import jakarta.mail.internet.MimeMultipart;
//import jakarta.mail.util.ByteArrayDataSource;
//import jakarta.servlet.http.HttpServletRequest;
//
//@Service
//public class EmailTemplateServiceImpl implements EmailTemplateService {
//	
//	@Autowired
//	private EmailTemplateRepo emailTemplateRepo;
//	
//	@Autowired
//	private EmailTemplateParamRepo emailTemplateParamRepo;
//	
////	@Autowired
////	private AutoDispSetupRepo autoDispSetupRepo;
//	
//	@Autowired
//	private CommonService commonServiceImpl;
//	
//	@Autowired
//	private CommonDao commonDao;
//	
//	@SuppressWarnings("unused")
//	@Autowired
//	private JavaMailSender javaMailSender;
//	
////	@Autowired
////	private LoggerFunctionService loggingService;
//	
//	@Value("${spring.success.code}")
//	private String successCode;
//
//	@Value("${spring.error.code}")
//	private String errorCode;
//
//	@Value("${spring.warning.code}")
//	private String warningCode;
//
//	@Value("${spring.message.code}")
//	private String messageCode;
//
//	@Value("${spring.status.code}")
//	private String statusCode;
//
//	@Value("${spring.data.code}")
//	private String dataCode;
//	
//	static final String username = "dineshbalamurugan85@gmail.com";
//	static final String appPassword = "bwrlxbcytzgcbvjb";
//
////	@Override
////	public String createNewTemplate(EmailTemplateRequest emailTemplateDto) throws Exception {
////		JSONObject response = new JSONObject();
////		JSONObject data = new JSONObject();
////
////		try {
////			LJM_EMAIL_TEMPLATE template = new LJM_EMAIL_TEMPLATE();
////
////			Map<String, Map<String, String>> fieldMaps = new HashMap<>();
////			fieldMaps.put("frontForm", emailTemplateDto.getFrontForm().getFormFields());
////			for (Map.Entry<String, Map<String, String>> entry : fieldMaps.entrySet()) {
////				setTemplateFields(template, entry.getValue());
////			}
////
////			try {
////				LJM_EMAIL_TEMPLATE savedTemplate = emailTemplateRepo.save(template);
////				response.put(statusCode, successCode);
////				response.put(messageCode, "Email Template Details created successfully");
////				data.put("Id", savedTemplate.getET_SYS_ID());
////				response.put("data", data);
////			} catch (Exception e) {
////				response.put("statusCode", errorCode);
////				response.put("message", "An error occurred: " + e.getMessage());
////			}
////		} catch (Exception e) {
////			e.printStackTrace();
////			response.put("statusCode", errorCode);
////			response.put("message", "An error occurred: " + e.getMessage());
////		}
////
////		return response.toString();
////	}
////
////	private void setTemplateFields(LJM_EMAIL_TEMPLATE template, Map<String, String> value) throws Exception {
////		for (Map.Entry<String, String> entry : value.entrySet()) {
////			setTemplateField(template, entry.getKey(), entry.getValue());
////		}
////	}
////
////	private void setTemplateField(LJM_EMAIL_TEMPLATE template, String key, String value) throws Exception {
////		try {
////			Field field = LJM_EMAIL_TEMPLATE.class.getDeclaredField(key);
////			Class<?> fieldType = field.getType();
////			Object convertedValue = commonServiceImpl.convertStringToObject(value, fieldType);
////			String setterMethodName = "set" + key;
////			if (value != null && !value.isEmpty()) {
////				Method setter = LJM_EMAIL_TEMPLATE.class.getMethod(setterMethodName, fieldType);
////				setter.invoke(template, convertedValue);
////			}
////		} catch (NoSuchFieldException e) {
//////			e.printStackTrace();
////		}
////	}
////
////	@Override
////	public String updateTemplate(EmailTemplateRequest emailTemplateModel, Integer templateId) {
////		JSONObject response = new JSONObject();
////
////		try {
////			Integer claimCoverId = templateId;
////			Optional<LJM_EMAIL_TEMPLATE> optionalUser = emailTemplateRepo.findById(claimCoverId);
////			LJM_EMAIL_TEMPLATE claim = optionalUser.get();
////			if (claim != null) {
////				Map<String, Map<String, String>> fieldMaps = new HashMap<>();
////				fieldMaps.put("frontForm", emailTemplateModel.getFrontForm().getFormFields());
////				for (Map.Entry<String, Map<String, String>> entry : fieldMaps.entrySet()) {
////					setTemplateFields(claim, entry.getValue());
////				}
////
////				try {
////					LJM_EMAIL_TEMPLATE savedClaimDetails = emailTemplateRepo.save(claim);
////					response.put(statusCode, successCode);
////					response.put(messageCode, "Email Template Details Updated Successfully");
////				} catch (Exception e) {
////					response.put("statusCode", errorCode);
////					response.put("message", "An error occurred: " + e.getMessage());
////				}
////			}
////		} catch (Exception e) {
////			e.printStackTrace();
////			response.put("statusCode", errorCode);
////			response.put("message", "An error occurred: " + e.getMessage());
////		}
////
////		return response.toString();
////	}
////
////	@Override
////	public String deleteTemplate(Integer templateId) {
////		JSONObject response = new JSONObject();
////		try {
////			LJM_EMAIL_TEMPLATE existingTemplate = emailTemplateRepo.getById(templateId);
////			if (existingTemplate != null) {
////				emailTemplateRepo.deleteById(templateId);
////
////				response.put(statusCode, successCode);
////				response.put(messageCode, "Email Template Details Deleted Successfully");
////			} else {
////				response.put(statusCode, errorCode);
////				response.put(messageCode, "No Such Template Exists");
////			}
////		} catch (Exception e) {
////			response.put(statusCode, errorCode);
////			response.put(messageCode, e.getMessage());
////		}
////		return response.toString();
////	}
////
////	@Override
////	public String getTemplate(HttpServletRequest request, String screenCode, String screenName, Integer templateId) {
////		JSONObject response = new JSONObject();
////		String result = null;
////		try {
////			LJM_EMAIL_TEMPLATE existingTemplate = emailTemplateRepo.getById(templateId);
////			if (existingTemplate != null) {
////				response.put(statusCode, successCode);
////				response.put(messageCode, "Email Template Details Fetched Successfully");
////				 ObjectMapper mapper = new ObjectMapper();
////				  Map<String, Object> templateData = mapper.convertValue(existingTemplate, Map.class);
////				  
////				  
////				  JSONObject getObject = new JSONObject(templateData);
////				  
////				  result = commonServiceImpl.newEditTabs(request, getObject);
////				  
////				response.put(dataCode, templateData);
////			} else {
////				response.put(statusCode, errorCode);
////				response.put(messageCode, "No Such Template Exists");
////			}
////		} catch (Exception e) {
////			response.put(statusCode, errorCode);
////			response.put(messageCode, e.getMessage());
////			e.printStackTrace();
////		}
////		return result;
////	}
////
////	@Override
////	public String createTemplateParam(EmailTemplateRequest emailTemplateParams, Integer templateId) {
////		JSONObject response = new JSONObject();
////		JSONObject data = new JSONObject();
////
////		try {
////			LJM_EMAIL_PARAM templateParam = new LJM_EMAIL_PARAM();
////
////			Map<String, Map<String, String>> fieldMaps = new HashMap<>();
////			fieldMaps.put("frontForm", emailTemplateParams.getEmailParams().getFormFields());
////			fieldMaps.get("frontForm").put("EP_ET_SYS_ID", templateId.toString());
////			for (Map.Entry<String, Map<String, String>> entry : fieldMaps.entrySet()) {
////				setTemplateParamFields(templateParam, entry.getValue());
////			}
////
////			try {
////				LJM_EMAIL_PARAM savedTemplate = emailTemplateParamRepo.save(templateParam);
////				response.put(statusCode, successCode);
////				response.put(messageCode, "Email Template Param Created Successfully");
////				data.put("Id", savedTemplate.getEP_SYS_ID());
////				response.put("data", data);
////			} catch (Exception e) {
////				response.put("statusCode", errorCode);
////				response.put("message", "An error occurred: " + e.getMessage());
////			}
////		} catch (Exception e) {
////			e.printStackTrace();
////			response.put("statusCode", errorCode);
////			response.put("message", "An error occurred: " + e.getMessage());
////		}
////
////		return response.toString();
////	}
////
////	private void setTemplateParamFields(LJM_EMAIL_PARAM templateParam, Map<String, String> value) throws Exception{
////		for (Map.Entry<String, String> entry : value.entrySet()) {
////			setTemplateParamField(templateParam, entry.getKey(), entry.getValue());
////		}
////	}
////
////	private void setTemplateParamField(LJM_EMAIL_PARAM templateParam, String key, String value) throws Exception{
////		try {
////			Field field = LJM_EMAIL_PARAM.class.getDeclaredField(key);
////			Class<?> fieldType = field.getType();
////			Object convertedValue = null;
////			if(fieldType == LJM_EMAIL_TEMPLATE.class) {
////				convertedValue = getForeignObject(value);
////			}else {
////			convertedValue = commonServiceImpl.convertStringToObject(value, fieldType);
////			}
////			String setterMethodName = "set" + key;
////			if (value != null && !value.isEmpty()) {
////				Method setter = LJM_EMAIL_PARAM.class.getMethod(setterMethodName, fieldType);
////				setter.invoke(templateParam, convertedValue);
////			}
////		} catch (NoSuchFieldException e) {
//////			e.printStackTrace();
////		}
////	}
////
////	private LJM_EMAIL_TEMPLATE getForeignObject(String value) {
////		LJM_EMAIL_TEMPLATE template = emailTemplateRepo.getById(Integer.parseInt(value));
////		return template;
////	}
////
////	@Override
////	public String updateTemplateParam(EmailTemplateRequest emailTemplateParams, Integer tempParamId) {
////		JSONObject response = new JSONObject();
////
////		try {
////			Integer templateParamId = tempParamId;
////			Optional<LJM_EMAIL_PARAM> optionalUser = emailTemplateParamRepo.findById(templateParamId);
////			LJM_EMAIL_PARAM templateParam = optionalUser.get();
////			if (templateParam != null) {
////				Map<String, Map<String, String>> fieldMaps = new HashMap<>();
////				fieldMaps.put("frontForm", emailTemplateParams.getEmailParams().getFormFields());
////				for (Map.Entry<String, Map<String, String>> entry : fieldMaps.entrySet()) {
////					setTemplateParamFields(templateParam, entry.getValue());
////				}
////
////				try {
////					LJM_EMAIL_PARAM savedParamDetails = emailTemplateParamRepo.save(templateParam);
////					response.put(statusCode, successCode);
////					response.put(messageCode, "Email Template Param Updated Successfully");
////				} catch (Exception e) {
////					response.put("statusCode", errorCode);
////					response.put("message", "An error occurred: " + e.getMessage());
////				}
////			}
////		} catch (Exception e) {
////			e.printStackTrace();
////			response.put("statusCode", errorCode);
////			response.put("message", "An error occurred: " + e.getMessage());
////		}
////
////		return response.toString();
////	}
////
////	@Override
////	public String deleteTemplateParam(Integer templateId) {
////		JSONObject response = new JSONObject();
////		try {
////			LJM_EMAIL_PARAM existingTemplate = emailTemplateParamRepo.getById(templateId);
////			if (existingTemplate != null) {
////				emailTemplateParamRepo.deleteById(templateId);
////
////				response.put(statusCode, successCode);
////				response.put(messageCode, "Email Template Details Deleted Successfully");
////			} else {
////				response.put(statusCode, errorCode);
////				response.put(messageCode, "No Such Template Exists");
////			}
////		} catch (Exception e) {
////			response.put(statusCode, errorCode);
////			response.put(messageCode, e.getMessage());
////		}
////		return response.toString();
////	}
////
////	@Override
////	public String getTemplateParam(HttpServletRequest request, String screenCode, String screenName, Integer templateId) {
////		JSONObject response = new JSONObject();
////		String result = null;
////		try {
////			LJM_EMAIL_PARAM existingTemplate = emailTemplateParamRepo.getById(templateId);
////			if (existingTemplate != null) {
////				response.put(statusCode, successCode);
////				response.put(messageCode, "Email Template Details Fetched Successfully");
////				 ObjectMapper mapper = new ObjectMapper();
////				  Map<String, Object> templateData = mapper.convertValue(existingTemplate, Map.class);
////				  
////				  if(existingTemplate.getEP_TYPE().equals("Q")) {
////					  QUERY_MASTER query = commonDao.getQueryLov(Integer.parseInt(existingTemplate.getEP_VALUE()));
////					  templateData.put("EP_QUERY", query.getQM_QUERY());
////				  }
////				  
////				  JSONObject getObject = new JSONObject(templateData);
////				  
////				  result = commonServiceImpl.newEditTabs(request, getObject);
////				  
////				response.put(dataCode, templateData);
////			} else {
////				response.put(statusCode, errorCode);
////				response.put(messageCode, "No Such Template Exists");
////			}
////		} catch (Exception e) {
////			response.put(statusCode, errorCode);
////			response.put(messageCode, e.getMessage());
////			e.printStackTrace();
////		}
////		return result;
////	}
////
//	@Override
//	public String sendMail(Integer templateId, EmailRequestModel inputObject, HttpServletRequest request)throws Exception {
//		
//		Map<String, MultipartFile> multiPartList = new HashMap<>();
//
//		JSONObject response = new JSONObject();
//		try {
//			String to = "dineshbalamurugan85@gmail.com";
//			String from = "dineshbalamurugan85@gmail.com";
//			String host = "smtp.gmail.com";
//
//			Properties properties = System.getProperties();
//			properties.put("mail.smtp.auth", "true");
//			properties.put("mail.smtp.port", "587");
//			properties.setProperty("mail.smtp.host", host);
//			properties.setProperty("mail.smtp.starttls.enable", "true");
//
//			Session session = Session.getInstance(properties, new jakarta.mail.Authenticator() {
//				protected PasswordAuthentication getPasswordAuthentication() {
//					return new PasswordAuthentication(username, "beja kxnm kdsa zkyx");
//				}
//			});
//
//			LjmEmailTemplateDAO emailTemplate = emailTemplateRepo.getById(templateId);
//			List<LJM_EMAIL_PARAM> emailTemplateParams = emailTemplateParamRepo.getParams(templateId);
//			Map<String, Object> paramMap = new HashMap<>(inputObject.getContent());
//			StringBuilder toIds = new StringBuilder();
//			StringBuilder ccIds = new StringBuilder();
//			StringBuilder bccIds = new StringBuilder();
//			for (LJM_EMAIL_PARAM param : emailTemplateParams) {
//				if (param.getEP_TYPE().equals("S")) {
//					if (param.getEP_PARAM_NAME().equals("to")) {
//						toIds.append(param.getEP_VALUE() + ",");
//					} else if (param.getEP_PARAM_NAME().equals("cc")) {
//						ccIds.append(param.getEP_VALUE() + ",");
//					} else if (param.getEP_PARAM_NAME().equals("bcc")) {
//						bccIds.append(param.getEP_VALUE() + ",");
//					} else {
//						paramMap.put(param.getEP_PARAM_NAME(), param.getEP_VALUE());
//					}
//				} else if (param.getEP_TYPE().equals("Q")) {
//					List<QueryParamMasterDTO> queryParams = commonDao.getQueryParams(Integer.parseInt(param.getEP_VALUE()));
//					Map<String, Object> emailTemplateQueryParams = processEmailTemplateParams(queryParams, inputObject.getContent());
//					if (param.getEP_PARAM_NAME().equals("to")) {
//						QUERY_MASTER queryMaster = commonDao.getQueryLov(Integer.parseInt(param.getEP_VALUE()));
//						String query = queryMaster.getQM_QUERY();
//						SqlRowSet result = commonDao.executeQuery(query, emailTemplateQueryParams);
//
//						while (result.next()) {
//							toIds.append(result.getObject(1) + ",");
//							paramMap.put(param.getEP_PARAM_NAME(), result.getObject(1));
//						}
//					} else if (param.getEP_PARAM_NAME().equals("cc")) {
//						QUERY_MASTER queryMaster = commonDao.getQueryLov(Integer.parseInt(param.getEP_VALUE()));
//						String query = queryMaster.getQM_QUERY();
//						SqlRowSet result = commonDao.executeQuery(query, emailTemplateQueryParams);
//
//						while (result.next()) {
//							ccIds.append(result.getObject(1) + ",");
//							paramMap.put(param.getEP_PARAM_NAME(), result.getObject(1));
//						}
//					} else if (param.getEP_PARAM_NAME().equals("bcc")) {
//						QUERY_MASTER queryMaster = commonDao.getQueryLov(Integer.parseInt(param.getEP_VALUE()));
//						String query = queryMaster.getQM_QUERY();
//						SqlRowSet result = commonDao.executeQuery(query, emailTemplateQueryParams);
//
//						while (result.next()) {
//							bccIds.append(result.getObject(1) + ",");
//							paramMap.put(param.getEP_PARAM_NAME(), result.getObject(1));
//						}
//					} else {
//						QUERY_MASTER queryMaster = commonDao.getQueryLov(Integer.parseInt(param.getEP_VALUE()));
//						String query = queryMaster.getQM_QUERY();
//						SqlRowSet result = commonDao.executeQuery(query, emailTemplateQueryParams);
//						while (result.next()) {
//							paramMap.put(param.getEP_PARAM_NAME(), result.getObject(1));
//						}
//					}
//				} else if (param.getEP_TYPE().equals("P")) {
//					if (param.getEP_PARAM_NAME().equals("to")) {
//						toIds.append(inputObject.getContent().get(param.getEP_PARAM_NAME()) + ",");
//					} else if (param.getEP_PARAM_NAME().equals("cc")) {
//						ccIds.append(inputObject.getContent().get(param.getEP_PARAM_NAME()) + ",");
//					} else if (param.getEP_PARAM_NAME().equals("bcc")) {
//						bccIds.append(inputObject.getContent().get(param.getEP_PARAM_NAME()) + ",");
//					} else {
//						paramMap.put(param.getEP_PARAM_NAME(), inputObject.getContent().get(param.getEP_PARAM_NAME()));
//					}
//				}
//			}
//
//			
//			MimeMessage message = new MimeMessage(session);
//			message.setFrom(new InternetAddress(from));
//			if (inputObject.getToIds() != null && inputObject.getToIds().size() > 0) {
//				for (String ids : inputObject.getToIds()) {
//					toIds.append(ids + ",");
//				}
//			}
//
//			if (inputObject.getCcIds() != null && inputObject.getCcIds().size() > 0) {
//				for (String ccId : inputObject.getCcIds()) {
//					ccIds.append(ccId + ",");
//				}
//			}
//			if (inputObject.getBccIds() != null && inputObject.getBccIds().size() > 0) {
//				for (String bccId : inputObject.getBccIds()) {
//					bccIds.append(bccId + ",");
//				}
//			}
//			if (inputObject.getSubject() != null || !inputObject.getSubject().isEmpty()) {
//				paramMap.put("subject", inputObject.getSubject());
//			}
//			
//			StringBuilder attachments = new StringBuilder();
//			if (inputObject.getAttachments() != null && inputObject.getAttachments().size() > 0) {
//				Set<String> keys = inputObject.getAttachments().keySet();
//				for (String key : keys) {
//					
//
//					attachments.append(key + ",");
//					BASE64DecodedMultipartFile conv = new BASE64DecodedMultipartFile(
//							inputObject.getAttachments().get(key));
//					multiPartList.put(key, conv);
//				}
//			}
//			
//			Set<String> fileName = multiPartList.keySet();
//			
//			if(toIds.length() > 1) {
//			toIds.deleteCharAt(toIds.length() - 1);
//			}
//			
//			if(ccIds.length() > 1) {
//			ccIds.deleteCharAt(ccIds.length() - 1);
//			}
//			
//			if(bccIds.length() > 1) {
//			bccIds.deleteCharAt(bccIds.length() - 1);
//			}
//			
//
//			InternetAddress[] recipients = InternetAddress.parse(toIds.toString());
//			InternetAddress[] toRecipients = InternetAddress.parse(ccIds.toString());
//			InternetAddress[] bccRecipients = InternetAddress.parse(bccIds.toString());
//			message.addRecipients(Message.RecipientType.TO, recipients);
//			message.addRecipients(Message.RecipientType.CC, toRecipients);
//			message.addRecipients(Message.RecipientType.BCC, bccRecipients);
//
//			Document document = Jsoup.parse(emailTemplate.getEtMsgBody());
//
//			String formattedString = document.outerHtml();
//
//			for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
//				formattedString = formattedString.replace("$" + entry.getKey(), entry.getValue().toString());
//			}
//
//			message.setSubject(paramMap.get("subject").toString());
//			message.setText(formattedString);
//
//			MimeBodyPart mimeBodyPart = new MimeBodyPart();
//			mimeBodyPart.setContent(formattedString, "text/html");
//
//			Multipart multipart = new MimeMultipart();
//			multipart.addBodyPart(mimeBodyPart);
//			
//			for (String filename : fileName) {
//			    MimeBodyPart attachmentPart = new MimeBodyPart();
//			    String contentType = "application/octet-stream";
//			    if (filename.endsWith(".pdf")) {
//			        contentType = "application/pdf";
//			    } else if (filename.endsWith(".jpg") || filename.endsWith(".jpeg")) {
//			        contentType = "image/jpeg";
//			    } else if (filename.endsWith(".json")) {
//			        contentType = "application/json";
//			    }
//			    
//			    attachmentPart.setDataHandler(new DataHandler(new ByteArrayDataSource(multiPartList.get(filename).getBytes(), contentType)));
//			    attachmentPart.setFileName(filename);
//			    multipart.addBodyPart(attachmentPart);
//}
//			
//			if(attachments.toString().length() > 0) {
//			attachments.deleteCharAt(attachments.toString().length()-1);
//			}
//			message.setContent(multipart);
//
//			Transport.send(message);
//			
//			 Document doc = Jsoup.parse(formattedString);
//		     String con = doc.text();
//		     
//			
//			EmailLogsDTO logs = new EmailLogsDTO();
//			logs.setTo(toIds.toString());
//			logs.setTemplateName(emailTemplate.getEtTempName());
//			logs.setTemplateBody(con);
//			logs.setGenDate(LocalDateTime.now());
//			logs.setAttachments(attachments.toString()); 
////			loggingService.logToEmailHistoryLogs(logs, request);
//			response.put(statusCode, successCode);
//			response.put(messageCode, "Mail Sent Successfully");
//		} catch (Exception e) {
//			response.put(statusCode, errorCode);
//			response.put(messageCode, e.getMessage());
//			e.printStackTrace();
//		}
//
//		return response.toString();
//	}
//
//	private Map<String, Object> processEmailTemplateParams(List<QueryParamMasterDTO> queryParams, Map<String, Object> map) {
//		Map<String, Object> emailTemplateQueryParams = new HashMap<>();
//		for(QueryParamMasterDTO param : queryParams) {
//			if(map.get(param.getQPM_PARAM_NAME()) != null) {
//			emailTemplateQueryParams.put(param.getQPM_PARAM_NAME(), map.get(param.getQPM_PARAM_NAME()));
//			}
//		}
//		return emailTemplateQueryParams;
//	}
//
////	@Override
////	public String startAutoDispatch(String eventId) {
////		JSONObject response = new JSONObject();
////
////		try {
////			if (!eventId.startsWith("EVNT_")) {
////				AutoDispSetup autoDispatchDetails = autoDispSetupRepo.getByEventId(eventId);
////				if(autoDispatchDetails.getADS_ACTIVE_YN().equals("Y")) {
////					
////				}else {
////					response.put(statusCode, successCode);
////					response.put(messageCode, "The Event is Not Active");
////				}
////			} else {
////				response.put(statusCode, successCode);
////				response.put(messageCode, "Please Enter the Correct Event Id");
////			}
////		} catch (Exception e) {
////			response.put(statusCode, errorCode);
////			response.put(messageCode, e.getMessage());
////		}
////		return response.toString();
////	}
//
//	@Override
//	public String getEmailQueries()throws Exception {
//		JSONObject response = new JSONObject();
//		QUERY_MASTER query = commonDao.getQueryLov(75);
//		
//		if(query != null) {
//			List<LOVDTO> queryList = commonDao.executeLOVQuery(query.getQM_QUERY(), null);
//			
//			response.put(statusCode, successCode);
//			response.put(messageCode, "List Of Query Details Fetched Successfully");
//			response.put(dataCode, queryList);
//		}else {
//			response.put(statusCode, errorCode);
//			response.put(messageCode, "Can't get query to get List of Email Queries");
//		}
//		return response.toString();
//	}
//
//	@Override
//	public String getParams() throws Exception {
//		JSONObject response = new JSONObject();
//		
//		QUERY_MASTER query = commonDao.getQueryLov(250);
//		
//		if(query != null) {
//			List<LOVDTO> queryList = commonDao.executeLOVQuery(query.getQM_QUERY(), null);
//			
//			response.put(statusCode, successCode);
//			response.put(messageCode, "List Of Available Parameters Fetched Successfully");
//			response.put(dataCode, queryList);
//		}else {
//			response.put(statusCode, errorCode);
//			response.put(messageCode, "Can't get query to get List of Parameters");
//		}
//		
//		return response.toString();
//	}
//
//}
