/*
 Version Number 0.0.1
*/

package com.vi.model.dto;

import com.vi.model.BaseDto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor 
public class WorkflowDTO  {
    
    private Long workStepId;
    private String workStepDescription;    
    private String notificationStatus;    
    private String workStepMail;    
    private String workStepSms;
    private String workStepWhatsApp;
    private Long workStepCompanyId;
    private Boolean deleted=false;
    private Date deletedAt;
    private String deletedBy;

}
	



  

