package com.vi.model.dao;

import java.time.Instant;
import java.util.Date;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PostPersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Entity(name="WN_ENQUIRY")
@Table(name = "WN_ENQUIRY")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EnquiryDAO {

    @Id
	// @GeneratedValue(strategy = GenerationType.AUTO)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ENQ_SEQ_NO")
	private Long enqSeqNo; 
	
    @Column(name = "ENQ_REF_ID")
    private String enqRefId;
    
    @Column(name = "USER_SEQ_NO")
	private Long userSeqNo;
    
    @Column(name = "LEAD_SEQ_NO")
    private Long leadSeqNo;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name="LEAD_SEQ_NO", referencedColumnName = "LEAD_SEQ_NO", insertable=false, updatable=false)
    private LeadDAO lead;

    @Column(name = "ENQ_NAME")
    private String enqName;
    
    @Column(name = "ENQ_DATE")
    private Date enqDate;

    @Column(name = "ENQ_UPDATED_BY")
    private String enqUpdatedBy;

    @Column(name = "ENQ_UPDATED_DATE")
    private Date enqUpdatedDate;

    @Column(name = "ENQ_LOB_CODE")
    private String enqLobCode;

    @Column(name = "ENQ_LOB_NAME")
    private String enqLobName;

    @Column(name = "ENQ_PROD_CODE")
    private String enqProdCode;

    @Column(name = "ENQ_PROD_NAME")
    private String enqProdName;

    @Column(name = "ENQ_BUS_TYPE")
    private String enqBusType;

//    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ENQ_DATE_RECEIVED")
    private Date enqDateReceived;

//    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ENQ_EXP_DATE")
    private Date enqExpDate;

    @Column(name = "ENQ_SUM_INSURED")
    private Long enqSumInsured;

    @Column(name = "ENQ_SUGGESTED_PREM")
    private Long enqSuggestedPrem;

    @Column(name = "ENQ_SUGGESTED_RATE")
    private Long enqSuggestedRate;

    @Column(name = "ENQ_INTERMED_CODE")
    private String enqIntermedCode;

    @Column(name = "ENQ_INTERMED_NAME")
    private String enqIntermedName;

    @Column(name = "ENQ_UNDERWRITER_CODE")
    private String enqUnderwriterCode;

    @Column(name = "ENQ_UNDERWRITER")
    private String enqUnderwriter;

    @Column(name = "ENQ_QUOTE_FLAG")
    private String enqQuoteFlag;

    @Lob
    @Column(name = "ENQ_RISK_DESC")
    private String enqRiskDesc;

    @Lob
    @Column(name = "ENQ_REMARKS")
    private String enqRemarks;

    @Column(name = "ENQ_STATUS")
    private String enqStatus = "pending";

    @Column(name = "ENQ_ASSIGNED_TO")
    private String enqAssignedTo;

    @Column(name = "ENQ_FIRST_NAME")
    private String enqFirstName;

    @Column(name = "ENQ_LAST_NAME")
    private String enqLastName;

    @Column(name = "ENQ_EMAIL")
    private String enqEmail;

    @Column(name = "ENQ_PHONE")
    private String enqPhone;

    @Column(name = "ENQ_MOBILE")
    private String enqMobile;

    @Column(name = "ENQ_ADDR_LINE1")
    private String enqAddrLine1;

    @Column(name = "ENQ_ADDR_LINE2")
    private String enqAddrLine2;

    @Column(name = "ENQ_COUNTRY")
    private String enqCountry;

    @Column(name = "ENQ_TYPE")
    private String enqType;

    @Column(name = "ENQ_EXPECTED_VALUE")
    private Long enqExpectedValue;

    @Lob
    @Column(name = "ENQ_DESCRIPTION")
    private String enqDescription;

    @Lob
    @Column(name = "ENQ_INTERNAL_NOTES")
    private String enqInternalNotes;
    
    @Lob
    @Column(name = "ENQ_LEAD_COMMENTS")
    private String leadComments;
    
    @Lob
    @Column(name = "ENQ_SALES_COMMENTS")
    private String salesComments;

    //@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ENQ_NEXT_FOLLOW_UP_DATE")
    private Date enqNextFollowUpDate;

    @Column(name = "ENQ_CREATED_BY")
    private String enqCreatedBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ENQ_CREATED_DATE")
    private Date enqCreatedDate= Date.from(Instant.now());

    @Column(name = "DELETED")
    private Boolean deleted=false;
    
    @Column(name = "DELETED_AT")
    private Date deletedAt;
    
    @Column(name = "DELETED_BY")
    private String deletedBy;
	 
    @PostPersist
    public void generateEnqRefId() {
        if (this.enqRefId == null && this.enqSeqNo != null) {
            this.enqRefId = String.format("E%07d", this.enqSeqNo);
        }
    }
    

}