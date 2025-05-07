package com.vi.base.emailTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;

import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.vi.base.commonUtils.CommonDao;
import com.vi.base.commonUtils.LOVDTO;
import com.vi.base.commonUtils.QUERY_MASTER;
import com.vi.base.logging.EmailLogsDTO;
import com.vi.base.modules.enquirys.EnquiryRepository;
import com.vi.base.modules.leads.LeadRepository;
import com.vi.base.modules.quotes.QuoteRepository;
import com.vi.base.modules.users.UserRepository;
import com.vi.base.modules.workflows.WorkflowRepository;
import com.vi.model.dao.EnquiryDAO;
import com.vi.model.dao.LeadDAO;
import com.vi.model.dao.LjmEmailTemplateDAO;
import com.vi.model.dao.QuoteDAO;
import com.vi.model.dao.UserDAO;
import com.vi.model.dao.WorkflowDAO;

import jakarta.activation.DataHandler;
import jakarta.mail.Message;
import jakarta.mail.Multipart;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import jakarta.mail.util.ByteArrayDataSource;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class EmailTemplateServiceImpl implements EmailTemplateService {

    @Autowired
    private EmailTemplateRepo emailTemplateRepo;

    @Autowired
    private WorkflowRepository workFlowRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private LeadRepository leadRepo;
    
    @Autowired
    private EnquiryRepository enquiryRepo;
    
    @Autowired
    private QuoteRepository quoteRepo;

    @Autowired
    private CommonDao commonDao;

    @Value("${spring.success.code}")
    private String successCode;

    @Value("${spring.error.code}")
    private String errorCode;

    @Value("${spring.message.code}")
    private String messageCode;

    @Value("${spring.status.code}")
    private String statusCode;

    @Value("${spring.data.code}")
    private String dataCode;

    private static final String SMTP_USERNAME = "dineshbalamurugan85@gmail.com";
    private static final String SMTP_PASSWORD = "beja kxnm kdsa zkyx";
    private static final String SMTP_HOST = "smtp.gmail.com";
    private static final int SMTP_PORT = 587;
    private String FROM_NAME = "No Reply";

    @Override
    public String sendMail(EmailRequestModel inputObject, HttpServletRequest request) throws Exception {
        Map<String, MultipartFile> attachments = inputObject.getAttachments() != null ? 
        inputObject.getAttachments() : new HashMap<>();
        JSONObject response = new JSONObject();

        try {
            // 1. Get workflow configuration
            WorkflowDAO workflow = workFlowRepo.findByMasterTriggerIdAndWorkStepCompanyId(
                inputObject.getMasterTriggerId(), 
                inputObject.getWorkStepCompanyId()
            );

            if (!"Y".equals(workflow.getWorkStepMail()) || !"Y".equals(workflow.getNotificationStatus())) {
                response.put(statusCode, errorCode);
                response.put(messageCode, "Email notifications are disabled for this workflow");
                return response.toString();
            }

            // 2. Get email template
            LjmEmailTemplateDAO emailTemplate = emailTemplateRepo.findById(workflow.getEtSysId())
                .orElseThrow(() -> new Exception("Email template not found for ID: " + workflow.getEtSysId()));

            // 3. Get lead details
            LeadDAO lead = leadRepo.findById(inputObject.getLeadSeqNo())
                .orElseThrow(() -> new Exception("Lead not found with ID: " + inputObject.getLeadSeqNo()));

            // 4. Determine sender and recipients based on role
            EmailRecipients recipients = determineRecipients(lead, inputObject.getMasterTriggerId());

            // 5. Get associated enquiry
            EnquiryDAO enquiry = null;
            Optional<EnquiryDAO> enquiryDetail = enquiryRepo.findByLeadSeqNo(inputObject.getLeadSeqNo());
            if(enquiryDetail.isPresent()) {
            	enquiry = enquiryDetail.get();
            }
//                .orElseThrow(() -> new Exception("Enquiry not found for lead ID: " + inputObject.getLeadSeqNo()));

            // 6. Prepare template parameters
            Map<String, Object> templateParams = prepareTemplateParameters(lead, enquiry, recipients);

            // 7. Handle underwriter-specific cases
            if (inputObject.getMasterTriggerId() >= 5 && inputObject.getMasterTriggerId() <= 12) {
                handleUnderwriterEmails(inputObject, emailTemplate, lead, templateParams, attachments, response);
            } else {
                // 8. Send regular role-based email
                sendRoleBasedEmail(emailTemplate, recipients, templateParams, attachments, response);
            }

            response.put(statusCode, successCode);
            response.put(messageCode, "Emails sent successfully");

        } catch (Exception e) {
            response.put(statusCode, errorCode);
            response.put(messageCode, "Error sending emails: " + e.getMessage());
            e.printStackTrace();
        }

        return response.toString();
    }

    private EmailRecipients determineRecipients(LeadDAO lead, Long masterTriggerId) {
        EmailRecipients recipients = new EmailRecipients();
        
        if (lead.getUser().getUserRole().equalsIgnoreCase("Manager")) {
            recipients.setManagerId(lead.getUser().getUserSeqNo());
            recipients.setSalesId(lead.getLeadAssignedTo());
        } else if (lead.getUser().getUserRole().equalsIgnoreCase("Sales")) {
            recipients.setSalesId(lead.getUser().getUserSeqNo());
            recipients.setManagerId(Long.parseLong(lead.getUser().getAssignedTo()));
        }  
        
        // For quote-related triggers, get underwriters
        if (masterTriggerId >= 5 && masterTriggerId <= 12) {
            List<QuoteDAO> quotes = quoteRepo.findByLeadSeqNo(lead.getLeadSeqNo());
            recipients.setUnderwriterIds(quotes.stream()
                .filter(q -> "Todo".equalsIgnoreCase(q.getIsAccepted()))
                .map(QuoteDAO::getUserSeqNo)
                .filter(Objects::nonNull)
                .distinct()
                .collect(Collectors.toList()));
        }
        
        return recipients;
    }

    private Map<String, Object> prepareTemplateParameters(LeadDAO lead, EnquiryDAO enquiry, EmailRecipients recipients) {
        Map<String, Object> params = new HashMap<>();
        
        // Lead parameters
        params.put("LeadName", lead.getLeadName());
        params.put("LeadSeqNo", lead.getLeadSeqNo());
        params.put("LeadRefId", lead.getLeadRefId());

        
        // Enquiry parameters
        if(enquiry!=null) {
        params.put("EnqName", enquiry.getEnqName());
        params.put("EnqSeqNo", enquiry.getEnqSeqNo());
        params.put("EnqRefId", enquiry.getEnqRefId());
        params.put("EnqStatus", enquiry.getEnqStatus());
        }
        
     // Quote parameters
        List<QuoteDAO> quotes = quoteRepo.findByLeadSeqNo(lead.getLeadSeqNo());
        if (!quotes.isEmpty()) {
            params.put("QuoteStatus", quotes.get(0).getQuoteStatus());
        } else {
            params.put("QuoteStatus", "N/A");
        }
              
        
        // User parameters
        userRepo.findById(recipients.getSalesId()).ifPresent(sales -> {
            params.put("SalesName", sales.getUserName());
            params.put("SalesSeqNo", sales.getUserSeqNo());
            params.put("SalesRefId", sales.getUserRefId());

            
        });
        
        userRepo.findById(recipients.getManagerId()).ifPresent(manager -> {
            params.put("ManagerName", manager.getUserName());
            params.put("ManagerSeqNo", manager.getUserSeqNo());
            params.put("ManagerRefId", manager.getUserRefId());

        });
        
        return params;
    }

    private void handleUnderwriterEmails(EmailRequestModel inputObject, LjmEmailTemplateDAO template, LeadDAO lead, 
                                        Map<String, Object> baseParams, 
                                        Map<String, MultipartFile> attachments,
                                        JSONObject response) throws Exception {
        // Get all unique underwriters
        List<QuoteDAO> quotes = quoteRepo.findByLeadSeqNo(lead.getLeadSeqNo());
//        List<Long> underwriterIds = quotes.stream()
//            .filter(q -> "Todo".equalsIgnoreCase(q.getIsAccepted()))
//            .map(QuoteDAO::getUserSeqNo)
//            .filter(Objects::nonNull)
//            .distinct()
//            .collect(Collectors.toList());
        List<Long> underwriterIds = new ArrayList<>();
        List<Long> todoTriggerIds = Arrays.asList(5L, 6L);
        for (QuoteDAO quote : quotes) {
            if (todoTriggerIds.contains(inputObject.getMasterTriggerId()) && "Todo".equalsIgnoreCase(quote.getIsAccepted()) && "Todo".equalsIgnoreCase(quote.getIsDone())) {
                Long userSeqNo = quote.getUserSeqNo();
                underwriterIds.add(userSeqNo);
                
            }
        }
        
        List<Long> acceptTriggerIds = Arrays.asList(7L, 8L, 9L, 10L, 11L, 12L);
        for (QuoteDAO quote : quotes) {
            if (acceptTriggerIds.contains(inputObject.getMasterTriggerId()) && "accept".equalsIgnoreCase(quote.getIsAccepted()) && "Todo".equalsIgnoreCase(quote.getIsDone())) {
                Long userSeqNo = quote.getUserSeqNo();
                underwriterIds.add(userSeqNo);
                // System.out.println(acceptTriggerIds.contains(inputObject.getMasterTriggerId()));
            }
        }
        
        if (!underwriterIds.isEmpty()) {
            // Send to each underwriter
            for (Long underwriterId : underwriterIds) {
                UserDAO underwriter = userRepo.findById(underwriterId)
                    .orElseThrow(() -> new Exception("Underwriter not found with ID: " + underwriterId));
                
                Map<String, Object> params = new HashMap<>(baseParams);
                params.put("UnderwriterName", underwriter.getUserName());
                params.put("UnderwriterSeqNo", underwriter.getUserSeqNo());
                params.put("UnderwriterRefId", underwriter.getUserRefId());
                                
                sendSingleEmail(
                    template,
                    underwriter.getUserEmail(),
                    null, // No specific recipient override
                    params,
                    attachments,
                    response
                );
            }
        } else {
        	Object obj = Long.valueOf(baseParams.get("ManagerSeqNo").toString());
        	long value = (Long) obj;
            // Fallback to manager if no underwriters
            sendRoleBasedEmail(template, 
                new EmailRecipients(value, null, null), 
                baseParams, 
                attachments, 
                response);
        }
    }

    private void sendRoleBasedEmail(LjmEmailTemplateDAO template, 
                                  EmailRecipients recipients,
                                  Map<String, Object> params,
                                  Map<String, MultipartFile> attachments,
                                  JSONObject response) throws Exception {
        String toEmail = null;
        
        // Determine primary recipient based on template configuration
        if (template.getEtTo().equals("sId")) {
//        	System.out.println(recipients.getSalesId() + "*");

            toEmail = userRepo.findById(recipients.getSalesId())
                .map(UserDAO::getUserEmail)
                .orElse(null);
        } else if (template.getEtTo().equals("mId")) {
//        	System.out.println(recipients.getManagerId() + "*");
            toEmail = userRepo.findById(recipients.getManagerId())
                .map(UserDAO::getUserEmail)
                .orElse(null);
        }
        
        sendSingleEmail(template, toEmail, null, params, attachments, response);
    }

    private void sendSingleEmail(LjmEmailTemplateDAO template,
                               String specificToEmail,
                               String specificCcEmail,
                               Map<String, Object> params,
                               Map<String, MultipartFile> attachments,
                               JSONObject response) throws Exception {
        Session session = createMailSession();
        MimeMessage message = new MimeMessage(session);

        // Set basic headers
//        
//        LeadDAO lead = leadRepo.findById(inputObject.getLeadSeqNo())

        
        message.setFrom(new InternetAddress(SMTP_USERNAME, FROM_NAME));
        message.setSubject(replacePlaceholders(template.getEtSubject(), params));

        // Process recipients
//        System.out.println(specificToEmail);
        List<String> toEmails = new ArrayList<>();
        if (specificToEmail != null) {
//        	System.out.println(specificToEmail);
            toEmails.add(specificToEmail);
        } else {
            toEmails.addAll(resolveRecipients(template.getEtTo(), params));
        }
        
        List<String> ccEmails = new ArrayList<>();
        if (specificCcEmail != null) {
            ccEmails.add(specificCcEmail);
        } else {
            ccEmails.addAll(resolveRecipients(template.getEtCc(), params));
        }
        
        List<String> bccEmails = resolveRecipients(template.getEtBcc(), params);

        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(String.join(",", toEmails)));
        message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(String.join(",", ccEmails)));
        message.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(String.join(",", bccEmails)));

        // Build email content
        String formattedBody = replacePlaceholders(template.getEtMsgBody(), params);
        MimeBodyPart textPart = new MimeBodyPart();
        textPart.setContent(formattedBody, "text/html; charset=utf-8");

        // Handle attachments
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(textPart);
        
        for (Map.Entry<String, MultipartFile> entry : attachments.entrySet()) {
            MimeBodyPart attachmentPart = new MimeBodyPart();
            attachmentPart.setFileName(entry.getKey());
            attachmentPart.setDataHandler(new DataHandler(
                new ByteArrayDataSource(entry.getValue().getBytes(), getContentType(entry.getKey()))
            ));
            multipart.addBodyPart(attachmentPart);
        }

        message.setContent(multipart);
        Transport.send(message);

        logEmail(template, toEmails, ccEmails, formattedBody, attachments.keySet());
    }

    private Session createMailSession() {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", SMTP_HOST);
        props.put("mail.smtp.port", SMTP_PORT);

        return Session.getInstance(props, new jakarta.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(SMTP_USERNAME, SMTP_PASSWORD);
            }
        });
    }

    private List<String> resolveRecipients(String recipientType, Map<String, Object> params) {
        List<String> emails = new ArrayList<>();
        
        if (recipientType == null || recipientType.isEmpty()) {
            return emails;
        }
        
        switch (recipientType) {
            case "sId":
                if (params.get("SalesSeqNo") != null) {
                    userRepo.findById((Long) params.get("SalesSeqNo"))
                        .ifPresent(user -> emails.add(user.getUserEmail()));
                }
                break;
            case "mId":
                if (params.get("ManagerSeqNo") != null) {
                    userRepo.findById((Long) params.get("ManagerSeqNo"))
                        .ifPresent(user -> emails.add(user.getUserEmail()));
                }
                break;
            case "uId":
                if (params.get("UnderwriterSeqNo") != null) {
                    userRepo.findById((Long) params.get("UnderwriterSeqNo"))
                        .ifPresent(user -> emails.add(user.getUserEmail()));
                }
                break;
            default:
                // Assume it's a direct email address
                emails.add(recipientType);
        }
        
        return emails;
    }

    private String replacePlaceholders(String text, Map<String, Object> params) {
        if (text == null) return "";
        
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            if (entry.getValue() != null) {
                text = text.replace("$" + entry.getKey(), entry.getValue().toString());
            }
        }
        return text;
    }

    private String getContentType(String filename) {
        if (filename.endsWith(".pdf")) return "application/pdf";
        if (filename.endsWith(".jpg") || filename.endsWith(".jpeg")) return "image/jpeg";
        if (filename.endsWith(".png")) return "image/png";
        if (filename.endsWith(".doc") || filename.endsWith(".docx")) return "application/msword";
        if (filename.endsWith(".xls") || filename.endsWith(".xlsx")) return "application/vnd.ms-excel";
        return "application/octet-stream";
    }

    private void logEmail(LjmEmailTemplateDAO template, List<String> to, List<String> cc, 
                        String body, Set<String> attachments) {
        EmailLogsDTO log = new EmailLogsDTO();
        log.setTo(String.join(",", to));
//        log.setCc(String.join(",", cc));
        log.setTemplateName(template.getEtTempName());
        log.setTemplateBody(Jsoup.parse(body).text());
        log.setGenDate(LocalDateTime.now());
        log.setAttachments(String.join(",", attachments));
        // Save to database or logging service
    }

    @Override
    public String getEmailQueries() throws Exception {
        JSONObject response = new JSONObject();
        QUERY_MASTER query = commonDao.getQueryLov(75);
        
        if (query != null) {
            List<LOVDTO> queryList = commonDao.executeLOVQuery(query.getQM_QUERY(), null);
            response.put(statusCode, successCode);
            response.put(messageCode, "List Of Query Details Fetched Successfully");
            response.put(dataCode, queryList);
        } else {
            response.put(statusCode, errorCode);
            response.put(messageCode, "Can't get query to get List of Email Queries");
        }
        return response.toString();
    }

    @Override
    public String getParams() throws Exception {
        JSONObject response = new JSONObject();
        QUERY_MASTER query = commonDao.getQueryLov(250);
        
        if (query != null) {
            List<LOVDTO> queryList = commonDao.executeLOVQuery(query.getQM_QUERY(), null);
            response.put(statusCode, successCode);
            response.put(messageCode, "List Of Available Parameters Fetched Successfully");
            response.put(dataCode, queryList);
        } else {
            response.put(statusCode, errorCode);
            response.put(messageCode, "Can't get query to get List of Parameters");
        }
        return response.toString();
    }

    // Helper class to manage recipients
    private static class EmailRecipients {
        private Long salesId;
        private Long managerId;
        private List<Long> underwriterIds;
        
        public EmailRecipients() {}
        
        public EmailRecipients(Long salesId, Long managerId, List<Long> underwriterIds) {
            this.salesId = salesId;
            this.managerId = managerId;
            this.underwriterIds = underwriterIds;
        }
        
        // Getters and setters
        public Long getSalesId() { return salesId; }
        public void setSalesId(Long salesId) { this.salesId = salesId; }
        public Long getManagerId() { return managerId; }
        public void setManagerId(Long managerId) { this.managerId = managerId; }
        public List<Long> getUnderwriterIds() { return underwriterIds; }
        public void setUnderwriterIds(List<Long> underwriterIds) { this.underwriterIds = underwriterIds; }
    }
}