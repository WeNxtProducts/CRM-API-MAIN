package com.vi.model.dto;

import java.util.Date;

import com.vi.model.dao.LjmEmailTemplateDAO;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class LjmEmailParamDTO {

	private Long epSysId;
	private LjmEmailTemplateDTO epEtSysId;
	private String epType;
	private String epParamName;
	private String epValue;
	private String epInsId;
	private Date epInsDt;
	private String epModId;
	private Date epModDt;
	private Boolean deleted=false;
    private Date deletedAt;
    private String deletedBy;
	
}