package com.vi.model.dto;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LjmEmailTemplateDTO {

    private Long etSysId;
    private String etTempName;
    private String etTempDesc;
    private String etActiveYn;
    private String etMsgBody;
    private String etInsId;
    private Date etInsDt;
    private String etModId;
    private Date etModDt;
    private Boolean deleted=false;
    private Date deletedAt;
    private String deletedBy;
    
}
