package com.vi.base.logging;

import java.time.LocalDateTime;

public class EmailLogsDTO {
	
	private String to;
	
	private String templateName;
	
	private String templateBody;
	
	private LocalDateTime genDate;
	
	private String hostName;
	
	private String attachments;
	
	private String ipAddress;

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String getTemplateBody() {
		return templateBody;
	}

	public void setTemplateBody(String templateBody) {
		this.templateBody = templateBody;
	}

	public LocalDateTime getGenDate() {
		return genDate;
	}

	public void setGenDate(LocalDateTime genDate) {
		this.genDate = genDate;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getAttachments() {
		return attachments;
	}

	public void setAttachments(String attachments) {
		this.attachments = attachments;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

}
