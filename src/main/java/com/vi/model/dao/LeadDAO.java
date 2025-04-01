package com.vi.model.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;


@Data
@Entity(name="WN_LEAD")
@Table(name = "WN_LEAD")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LeadDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LEAD_SEQ_NO")
    private Long leadSeqNo;

    @Column(name = "LEAD_REF_ID")
    private String leadRefId;
    
	@Column(name = "USER_SEQ_NO")
	private Long userSeqNo;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name="USER_SEQ_NO", referencedColumnName = "USER_SEQ_NO", insertable=false, updatable=false)
    private UserDAO user;

    @Column(name = "LEAD_FLAG")
    private String leadFlag; // 'e' for existing customer, 'l' for new lead

    @Column(name = "LEAD_NAME")
    private String leadName;
    
	@Column(name = "LEAD_FIRST_NAME")
    private String leadFirstName;

    @Column(name = "LEAD_LAST_NAME")
    private String leadLastName;

    @Column(name = "LEAD_EMAIL")
    private String leadEmail;

    @Column(name = "LEAD_PHONE_NO")
    private String leadPhoneNo;

    @Column(name = "LEAD_MOBILE_NO")
    private String leadMobileNo;

    @Column(name = "LEAD_ADDR_LINE1")
    private String leadAddrLine1;

    @Column(name = "LEAD_ADDR_LINE2")
    private String leadAddrLine2;

    @Column(name = "LEAD_COUNTRY")
    private String leadCountry;

    @Column(name = "LEAD_TYPE")
    private String leadType;

    @Column(name = "LEAD_STATUS")
    private String leadStatus = "Todo";

    @Column(name = "LEAD_PRIORITY")
    private String leadPriority;

    @Column(name = "LEAD_SOURCE")
    private String leadSource;

    @Column(name = "REMAINDER_COUNT")
    private Long remainderCount=0L;

    @Column(name = "LEAD_ASSIGNED_TO")
    private Long leadAssignedTo;

    @Column(name = "LEAD_CREATED_BY")
    private String leadCreatedBy;

    @Column(name = "LEAD_CREATED_DATE")
    private Date leadCreatedDate = Date.from(Instant.now());

    @Column(name = "LEAD_UPDATED_BY")
    private String leadUpdatedBy;

    @Column(name = "LEAD_UPDATED_DATE")
    private Date leadUpdatedDate; 

    @Lob
    @Column(name = "lead_desciption")
    private String leadDescription;

    @Lob
    @Column(name = "LEAD_COMMENTS")
    private String leadComments;

    @Lob
    @Column(name = "LEAD_INTERNAL_NOTES")
    private String leadInternalNotes;
    
    @Column(name = "DELETED")
    private Boolean deleted=false;
    
    @Column(name = "DELETED_AT")
    private Date deletedAt;
    
    @Column(name = "DELETED_BY")
    private String deletedBy;
    
}

