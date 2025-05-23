/*
 Version Number 0.0.1
*/

package com.vi.model.dto;

import com.vi.model.BaseDto;
import com.vi.model.dao.LjmEmailTemplateDAO;
import com.vi.model.dao.MasterWorkflowDAO;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor 
public class WorkflowDTO  {
    
    private Long workStepId;	
	private Long etSysId;
	private LjmEmailTemplateDAO template;
    private Long masterTriggerId;
	private MasterWorkflowDAO masterWorkflow;    
    private String notificationStatus;
    private String workStepMail;    
    private String workStepSms;    
    private String workStepWhatsApp;    
    private Long workStepCompanyId;   
    private Boolean deleted=false;    
    private Date deletedAt;    
    private String deletedBy;


}
	



  

