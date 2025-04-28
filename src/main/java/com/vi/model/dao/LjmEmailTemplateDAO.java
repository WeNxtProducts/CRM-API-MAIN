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

import jakarta.persistence.*;


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

    @Column(name = "ET_TEMP_NAME")
    private String etTempName;

    @Column(name = "ET_TEMP_DESC")
    private String etTempDesc;

    @Column(name = "ET_ACTIVE_YN")
    private String etActiveYn;

    @Lob
    @Column(name = "ET_MSG_BODY")
    private String etMsgBody;

    @Column(name = "ET_INS_ID")
    private String etInsId;

    @Column(name = "ET_INS_DT")
    private Date etInsDt;

    @Column(name = "ET_MOD_ID")
    private String etModId;

    @Column(name = "ET_MOD_DT")
    private Date etModDt;
    
    @Column(name = "DELETED")
    private Boolean deleted=false;
    
    @Column(name = "DELETED_AT")
    private Date deletedAt;
    
    @Column(name = "DELETED_BY")
    private String deletedBy;

}