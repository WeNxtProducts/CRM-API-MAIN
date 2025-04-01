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

import java.sql.Time;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

import org.hibernate.annotations.NotFound;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor 
public class ActivityDTOCustom  {
 
	private Long activitySeqNo;
    private String activityRefId;
    private Long userSeqNo;
    private Long enqSeqNo; 
    private EnquiryDTO enquiry;
    private String activityType; // "APPOINTMENT" or "EVENT"
    private String activitySubject;
    private String activityDescription;
    private Date activityStartDate;	    
    private Date activityEndDate;
    private Time activityStartTime;
    private Time activityEndTime;
    private String activityStatus;
    private Long probabilityPercentage;
    private String activityCreatedBy;
    private Date activityCreatedDate = Date.from(Instant.now());
    private String activityUpdatedBy;
    private Date activityUpdatedDate;	  
    private String activityPriority;
    private boolean reminderSent;
    private Boolean deleted=false;
    private Date deletedAt;
    private String deletedBy;
	    
	}