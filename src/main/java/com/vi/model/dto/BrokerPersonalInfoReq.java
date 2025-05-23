/*
 Version Number 0.0.1
*/

package com.vi.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor 
@JsonIgnoreProperties(ignoreUnknown = true)
public class BrokerPersonalInfoReq {

	    @JsonProperty("UserName")
	    private String userName;

	    @JsonProperty("UserMobile")
	    private String userMobile;

	    @JsonProperty("UserMail")
	    private String userMail;

	    @JsonProperty("CompanyName")
	    private String companyName;

	    @JsonProperty("Address1")
	    private String address1;

	    @JsonProperty("Address2")
	    private String address2;

	    @JsonProperty("Address3")
	    private String address3;

	    @JsonProperty("CityCode")
	    private String cityCode;

	    @JsonProperty("CityName")
	    private String cityName;

	    @JsonProperty("StateCode")
	    private String stateCode;

	    @JsonProperty("StateName")
	    private String stateName;

	    @JsonProperty("CountryCode")
	    private String countryCode;

	    @JsonProperty("CountryName")
	    private String countryName;

	    @JsonProperty("Pobox")
	    private String pobox;

	    @JsonProperty("Fax")
	    private String fax;

	    @JsonProperty("Remarks")
	    private String remarks;

	    @JsonProperty("MissippiId")
	    private String missippiId;

	    @JsonProperty("ApprovedPreparedBy")
	    private String approvedPreparedBy;

	    @JsonProperty("CoreAppBrokerCode")
	    private String coreAppBrokerCode;

	    @JsonProperty("AcExecutiveId")
	    private String acExecutiveId;

	    @JsonProperty("TaxExemptedYn")
	    private String taxExemptedYn;

	    @JsonProperty("TaxExemptedCode")
	    private String taxExemptedCode;

	    @JsonProperty("RegulatoryCode")
	    private String regulatoryCode;

	    @JsonProperty("CreditLimit")
	    private String creditLimit;

	    @JsonProperty("Designation")
	    private String designation;

	    @JsonProperty("ContactPersonName")
	    private String contactPersonName;

	    @JsonProperty("MobileCode")
	    private String mobileCode;

	    @JsonProperty("WhatsappCode")
	    private String whatsappCode;

	    @JsonProperty("WhatsappNo")
	    private String whatsappNo;

	    @JsonProperty("CustomerCode")
	    private String customerCode;
	    
	    @JsonProperty("ApprovalId")
	    private String approvalId;

	   
}