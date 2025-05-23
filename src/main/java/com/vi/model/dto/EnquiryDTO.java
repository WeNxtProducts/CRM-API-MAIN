/*
 Version Number 0.0.1
*/

package com.vi.model.dto;

import java.time.Instant;
import java.util.Date;

import com.vi.model.dao.QuoteDAO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor 
public class EnquiryDTO  {
 
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
    private Long quoteSeqNo;
    private QuoteDAO quoteDAO;



}