/*
 Version Number 0.0.1
*/

package com.vi.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor 

public class IssuerPersonalInfoReq {

	    @JsonProperty("UserName")
	    private String userName;

	    @JsonProperty("UserMobile")
	    private String userMobile;

	    @JsonProperty("UserMail")
	    private String userMail;

	    @JsonProperty("Address1")
	    private String address1;

	    @JsonProperty("Address2")
	    private String address2;

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

	    @JsonProperty("MobileCode")
	    private String mobileCode;

	    @JsonProperty("Remarks")
	    private String remarks;

	    @JsonProperty("WhatappCode")
	    private String whatsappCode;

	    @JsonProperty("WhatsappNo")
	    private String whatsappNo;
	}