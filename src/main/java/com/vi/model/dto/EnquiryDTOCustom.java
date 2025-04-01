/*
Version Number 0.0.1
*/

package com.vi.model.dto;

import com.ibm.icu.math.BigDecimal;
import com.vi.model.BaseDto;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Date;

import org.hibernate.annotations.NotFound;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor 
public class EnquiryDTOCustom  {

	 private Long enqSeqNo; 
	    private String enqRefId;
	    private Long userSeqNo;
	    private Long leadSeqNo;
	    private LeadDTO lead;
	    private String enqName;
	    private Date enqDate= Date.from(Instant.now());
	    private String enqUpdatedBy;
	    private Date enqUpdatedDate;
	    private String enqLobCode;
	    private String enqLobName;
	    private String enqProdCode;
	    private String enqProdName;
	    private String enqBusType;
	    private Date enqDateReceived;
	    private Date enqExpDate;
	    private Long enqSumInsured;
	    private Long enqSuggestedPrem;
	    private Long enqSuggestedRate;
	    private String enqIntermedCode;
	    private String enqIntermedName;
	    private String enqUnderwriterCode;
	    private String enqUnderwriter;
	    private String enqQuoteFlag;
	    private String enqRiskDesc;
	    private String enqRemarks;
	    private String enqStatus = "pending";
	    private String enqAssignedTo;
	    private String enqFirstName;
	    private String enqLastName;
	    private String enqEmail;
	    private String enqPhone;
	    private String enqMobile;
	    private String enqAddrLine1;
	    private String enqAddrLine2;
	    private String enqCountry;
	    private String enqType;
	    private Long enqExpectedValue;
	    private String enqDescription;
	    private String enqInternalNotes;
	    private String leadComments;
	    private String salesComments;
	    private Date enqNextFollowUpDate;
	    private String enqCreatedBy;
	    private Date enqCreatedDate = Date.from(Instant.now());
	    private Boolean deleted=false;
	    private Date deletedAt;
	    private String deletedBy;

}