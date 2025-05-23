/*
 Version Number 0.0.1
*/

package com.vi.model.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor 
public class IssuerLoginReq {

	    @JsonProperty("LoginId")
	    private String loginId;

	    @JsonProperty("UserType")
	    private String userType;

	    @JsonProperty("SubUserType")
	    private String subUserType;

	    @JsonProperty("OaCode")
	    private String oaCode;

	    @JsonProperty("AgencyCode")
	    private String agencyCode;

	    @JsonProperty("AttachedBranches")
	    private List<String> attachedBranches;

	    @JsonProperty("AttachedCompanies")
	    private List<String> attachedCompanies;

	    @JsonProperty("AttachedRegions")
	    private List<String> attachedRegions;

	    @JsonProperty("Password")
	    private String password;

	    @JsonProperty("Createdby")
	    private String createdBy;

	    @JsonProperty("Status")
	    private String status;

	    @JsonProperty("BrokerCompanyYn")
	    private String brokerCompanyYn;

	    @JsonFormat(pattern = "dd/MM/yyyy")
	    @JsonProperty("EffectiveDateStart")
	    private Date effectiveDateStart;

	    @JsonProperty("InsuranceId")
	    private String companyId;

	    @JsonProperty("ProductIds")
	    private List<String> productIds;

	    @JsonProperty("ReferralIds")
	    private List<String> referralIds;

	    @JsonProperty("DmlYN")
	    private String dmlYN;
	}