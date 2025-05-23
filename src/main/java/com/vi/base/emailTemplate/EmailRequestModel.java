package com.vi.base.emailTemplate;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public class EmailRequestModel {


	private Long masterTriggerId;
	private Long workStepCompanyId;
	private Long leadSeqNo;
	private Long quoteSeqNo;

	
	private Map<String, MultipartFile> attachments;
	public Long getMasterTriggerId() {
		return masterTriggerId;
	}
	public void setMasterTriggerId(Long masterTriggerId) {
		this.masterTriggerId = masterTriggerId;
	}
	public Long getWorkStepCompanyId() {
		return workStepCompanyId;
	}
	public void setWorkStepCompanyId(Long workStepCompanyId) {
		this.workStepCompanyId = workStepCompanyId;
	}
	public Long getLeadSeqNo() {
		return leadSeqNo;
	}
	public void setLeadSeqNo(Long leadSeqNo) {
		this.leadSeqNo = leadSeqNo;
	}
	public Map<String, MultipartFile> getAttachments() {
		return attachments;
	}
	public void setAttachments(Map<String, MultipartFile> attachments) {
		this.attachments = attachments;
	}
	public Long getQuoteSeqNo() {
		return quoteSeqNo;
	}
	public void setQuoteSeqNo(Long quoteSeqNo) {
		this.quoteSeqNo = quoteSeqNo;
	}
	
	
	
}
