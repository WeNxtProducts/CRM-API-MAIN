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
@Entity(name="MASTER_WORKFLOW")
@Table(name = "MASTER_WORKFLOW")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MasterWorkflowDAO {

	@Id
    @Column(name = "MASTER_TRIGGER_ID")
    private Long masterTriggerId;
	
    @Column(name = "MASTER_TRIGGER_CODE")
    private String masterTriggerCode;
	
	@Column(name = "MASTER_TRIGGER_DESCRIPTION")
    private String masterTriggerDescription;
    
}
