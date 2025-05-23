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
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;


@Data
@Entity(name="EMAIL_PARAM")
@Table(name = "EMAIL_PARAM")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LjmEmailParamDAO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "EP_SYS_ID")
	private Long epSysId;
	
	@ManyToOne
	@JoinColumn(name = "EP_ET_SYS_ID")
	private LjmEmailTemplateDAO epEtSysId;
	
	@Column(name = "EP_TYPE")
	private String epType;
	
	@Column(name = "EP_PARAM_NAME")
	private String epParamName;
	
	@Column(name = "EP_VALUE")
	private String epValue;
	
	@Column(name = "EP_INS_ID")
	private String epInsId;
	
	@Column(name = "EP_INS_DT")
	private Date epInsDt;
	
	@Column(name = "EP_MOD_ID")
	private String epModId;
	
	@Column(name = "EP_MOD_DT")
	private Date epModDt;
	
	@Column(name = "DELETED")
    private Boolean deleted=false;
    
    @Column(name = "DELETED_AT")
    private Date deletedAt;
    
    @Column(name = "DELETED_BY")
    private String deletedBy;
	
}