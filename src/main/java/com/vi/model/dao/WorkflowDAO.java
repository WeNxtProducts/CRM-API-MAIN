package com.vi.model.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;


@Data
@Entity(name="WORKFLOW")
@Table(name = "WORKFLOW")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class WorkflowDAO {

	@Id
//  @GeneratedValue(strategy = GenerationType.AUTO)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "WORK_STEP_ID")
    private Long workStepId;
	
	@Column(name = "ET_SYS_ID")
	private Long etSysId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name="ET_SYS_ID", referencedColumnName = "ET_SYS_ID", insertable=false, updatable=false)
	private LjmEmailTemplateDAO template;

//    @Lob
//    @Column(name = "WORK_STEP_DESCRIPTION")
//    private String workStepDescription;
    
	@Column(name = "MASTER_TRIGGER_ID")
    private Long masterTriggerId;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name="MASTER_TRIGGER_ID", referencedColumnName = "MASTER_TRIGGER_ID", insertable=false, updatable=false)
	private MasterWorkflowDAO masterWorkflow;
    
    @Column(name = "NOTIFICATION_STATUS")
    private String notificationStatus;
    
    @Column(name = "WORK_STEP_MAIL")
    private String workStepMail;
    
    @Column(name = "WORK_STEP_SMS")
    private String workStepSms;
    
    @Column(name = "WORK_STEP_WHATSAPP")
    private String workStepWhatsApp;
    
    @Column(name = "WORK_STEP_COMPANY_ID")
    private Long workStepCompanyId;
    
    @Column(name = "DELETED")
    private Boolean deleted=false;
    
    @Column(name = "DELETED_AT")
    private Date deletedAt;
    
    @Column(name = "DELETED_BY")
    private String deletedBy;

}
