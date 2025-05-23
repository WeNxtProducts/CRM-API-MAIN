package com.vi.model.dto;

import java.util.Date;
import java.util.List;

import com.vi.model.dao.WorkflowDAO;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class LjmEmailTemplateDTO {

    private Long etSysId;
    private Long workStepId;
    private WorkflowDAO workflow;
    private String etTempName;
    private String etTempDesc;
    private String etActiveYn;
    private String etMsgBody;
    private String etInsId;
    private Date etInsDt;
    private String etModId;
    private Date etModDt;
    private String etSubject;
    private String etFrom;
    private String etTo;
    private String etCc;
    private String etBcc;
    private Boolean deleted=false;
    private Date deletedAt;
    private String deletedBy;
    
}
