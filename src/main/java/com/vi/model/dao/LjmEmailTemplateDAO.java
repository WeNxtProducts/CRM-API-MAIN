package com.vi.model.dao;

import java.util.Date;
import java.util.List;

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
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Data
@Entity(name="EMAIL_TEMPLATE")
@Table(name = "EMAIL_TEMPLATE")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LjmEmailTemplateDAO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ET_SYS_ID")
    private Long etSysId;
	
//	@Column(name = "WORK_STEP_ID")
//  private Long workStepId;
//	
//	@ManyToOne(fetch = FetchType.LAZY)
//  @NotFound(action = NotFoundAction.IGNORE)
//  @JoinColumn(name="WORK_STEP_ID", referencedColumnName = "WORK_STEP_ID", insertable=false, updatable=false)
//	private WorkflowDAO workflow;

    @Column(name = "ET_TEMP_NAME")
    private String etTempName;

    @Column(name = "ET_TEMP_DESC")
    private String etTempDesc;

    @Column(name = "ET_ACTIVE_YN")
    private String etActiveYn;

    @Column(name = "ET_MSG_BODY", length = 2500)
    private String etMsgBody;

    @Column(name = "ET_INS_ID")
    private String etInsId;

    @Column(name = "ET_INS_DT")
    private Date etInsDt;

    @Column(name = "ET_MOD_ID")
    private String etModId;

    @Column(name = "ET_MOD_DT")
    private Date etModDt;
    
    @Column(name = "ET_SUBJECT")
    private String etSubject;
    
    @Column(name = "ET_FROM")
    private String etFrom;
    
    @Column(name = "ET_TO")
    private String etTo;
    
    @Column(name = "ET_CC")
    private String etCc;
    
    @Column(name = "ET_BCC")
    private String etBcc;
    
    @Column(name = "DELETED")
    private Boolean deleted=false;
    
    @Column(name = "DELETED_AT")
    private Date deletedAt;
    
    @Column(name = "DELETED_BY")
    private String deletedBy;

}