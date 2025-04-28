//package com.vi.base.emailTemplate;
//
//import java.sql.Date;
//
//import com.fasterxml.jackson.annotation.JsonProperty;
//import com.vi.model.dao.LjmEmailTemplateDAO;
//
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;
//import jakarta.persistence.SequenceGenerator;
//import jakarta.persistence.Table;
//
//@Entity
//@Table(name ="LJM_EMAIL_PARAM")
//public class LJM_EMAIL_PARAM {
//	
//	@Id
//  	@SequenceGenerator(name = "EmailTemplateParamSeq", sequenceName = "ET_SYS_ID_SEQ", allocationSize = 1)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EmailTemplateParamSeq")
//	@Column(name = "EP_SYS_ID")
//	@JsonProperty("EP_SYS_ID")
//	private Integer EP_SYS_ID;
//	
//	@ManyToOne
//	@JoinColumn(name = "EP_ET_SYS_ID")
//	@JsonProperty("EP_ET_SYS_ID")
//	private LjmEmailTemplateDAO EP_ET_SYS_ID;
//	
//	@Column(name = "EP_TYPE")
//	@JsonProperty("EP_TYPE")
//	private String EP_TYPE;
//	
//	@Column(name = "EP_PARAM_NAME")
//	@JsonProperty("EP_PARAM_NAME")
//	private String EP_PARAM_NAME;
//	
//	@Column(name = "EP_VALUE")
//	@JsonProperty("EP_VALUE")
//	private String EP_VALUE;
//	
//	@Column(name = "EP_INS_ID")
//	private String EP_INS_ID;
//	
//	@Column(name = "EP_INS_DT")
//	private Date EP_INS_DT;
//	
//	@Column(name = "EP_MOD_ID")
//	private String EP_MOD_ID;
//	
//	@Column(name = "EP_MOD_DT")
//	private Date EP_MOD_DT;
//
//	public Integer getEP_SYS_ID() {
//		return EP_SYS_ID;
//	}
//
//	public void setEP_SYS_ID(Integer eP_SYS_ID) {
//		EP_SYS_ID = eP_SYS_ID;
//	}
//
//	public LjmEmailTemplateDAO getEP_ET_SYS_ID() {
//		return EP_ET_SYS_ID;
//	}
//
//	public void setEP_ET_SYS_ID(LjmEmailTemplateDAO eP_ET_SYS_ID) {
//		EP_ET_SYS_ID = eP_ET_SYS_ID;
//	}
//
//	public String getEP_TYPE() {
//		return EP_TYPE;
//	}
//
//	public void setEP_TYPE(String eP_TYPE) {
//		EP_TYPE = eP_TYPE;
//	}
//
//	public String getEP_PARAM_NAME() {
//		return EP_PARAM_NAME;
//	}
//
//	public void setEP_PARAM_NAME(String eP_PARAM_NAME) {
//		EP_PARAM_NAME = eP_PARAM_NAME;
//	}
//
//	public String getEP_VALUE() {
//		return EP_VALUE;
//	}
//
//	public void setEP_VALUE(String eP_VALUE) {
//		EP_VALUE = eP_VALUE;
//	}
//
//	public String getEP_INS_ID() {
//		return EP_INS_ID;
//	}
//
//	public void setEP_INS_ID(String eP_INS_ID) {
//		EP_INS_ID = eP_INS_ID;
//	}
//
//	public Date getEP_INS_DT() {
//		return EP_INS_DT;
//	}
//
//	public void setEP_INS_DT(Date eP_INS_DT) {
//		EP_INS_DT = eP_INS_DT;
//	}
//
//	public String getEP_MOD_ID() {
//		return EP_MOD_ID;
//	}
//
//	public void setEP_MOD_ID(String eP_MOD_ID) {
//		EP_MOD_ID = eP_MOD_ID;
//	}
//
//	public Date getEP_MOD_DT() {
//		return EP_MOD_DT;
//	}
//
//	public void setEP_MOD_DT(Date eP_MOD_DT) {
//		EP_MOD_DT = eP_MOD_DT;
//	}
//
//}
