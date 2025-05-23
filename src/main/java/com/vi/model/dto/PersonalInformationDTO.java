/*
 Version Number 0.0.1
*/

package com.vi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class PersonalInformationDTO {

	    private String acExecutiveId;
	    private String address1;
	    private String address2;
	    private String address3;
	    private String approvedPreparedBy;
	    private String checkerYn;
	    private String cityCode;
	    private String cityName;
	    private String stateCode;
	    private String commissionVatYn;
	    private String companyName;
	    private String contactPersonName;
	    private String coreAppBrokerCode;
	    private String regulatoryCode;
	    private String countryCode;
	    private String custConfirmYn;
	    private String designation;
	    private String fax;
	    private String makerYn;
	    private String creditLimit;
	    private String taxExemptedYn;
	    private String taxExemptedCode;
	    private String pobox;
	    private String remarks;
	    private String userMail;
	    private String userMobile;
	    private String userName;
	    private String mobileCode;
	    private String whatsappCode;
	    private String whatsappNo;
	    private String vatRegNo;
	    private String customerCode;
	}
