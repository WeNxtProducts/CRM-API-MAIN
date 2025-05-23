/*
 Version Number 0.0.1
*/

package com.vi.model.dto;

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
import java.time.LocalDate;
import java.util.Date;

import org.hibernate.annotations.NotFound;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor 
public class LeadDTO  {
 
    private Long leadSeqNo;
    private String leadRefId;
    private Long userSeqNo;
    private UserDTO user;
    private String leadFlag; 
    private String leadName;
    private String leadFirstName;
    private String leadLastName;
    private String leadEmail;
    private String leadPhoneNo;
    private String leadMobileNo;
    private String leadAddrLine1;
    private String leadAddrLine2;
    private String leadCountry;
    private String leadType;
    private String leadStatus ="Todo";
    private String leadPriority;
    private String leadSource;
    private Long enqCount=0L;
    private Long remainderCount=0L;
    private Long leadAssignedTo;
    private String leadCreatedBy;
    private Date leadCreatedDate= Date.from(Instant.now());
    private String leadUpdatedBy;
    private Date leadUpdatedDate; 
    private String leadDescription;
    private String leadComments;
    private String leadInternalNotes;
    private Boolean deleted=false;
    private Date deletedAt;
    private String deletedBy;

}
