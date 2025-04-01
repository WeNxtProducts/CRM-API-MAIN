/*
 Version Number 0.0.1
*/

package com.vi.model.dto;

import com.ibm.icu.math.BigDecimal;
import com.vi.model.BaseDto;
import com.vi.model.dao.UserDAO;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

import org.hibernate.annotations.NotFound;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor 
public class TaskDTO  {

    private Long taskSeqNo;
    private Long userSeqNo;
    private UserDAO user;
    private String taskName;
    private String taskDescription;
    private Date taskDueDate;
    private String taskStatus; // e.g., Pending, In Progress, Completed
    private String assignedTo; // e.g., User ID or Name
    private String taskPriority; // e.g., High, Medium, Low
    private String taskCreatedBy;
    private Date taskCreatedDate= Date.from(Instant.now());;
    private String taskUpdatedBy;
    private Date taskUpdatedDate; 
    private Boolean deleted=false;
    private Date deletedAt;
    private String deletedBy;

    
}