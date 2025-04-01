package com.vi.model.dao;

import java.util.Date;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Data
@Entity(name="WN_TASK")
@Table(name = "WN_TASK")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TaskDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TASK_SEQ_NO")
    private Long taskSeqNo;
    
    @Column(name = "USER_SEQ_NO")
	private Long userSeqNo;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name="USER_SEQ_NO", referencedColumnName = "USER_SEQ_NO", insertable=false, updatable=false)
    private UserDAO user;

    @Column(name = "TASK_NAME")
    private String taskName;

    @Column(name = "TASK_DESCRIPTION")
    private String taskDescription;

    @Column(name = "TASK_DUE_DATE")
    private Date taskDueDate;

    @Column(name = "TASK_STATUS")
    private String taskStatus; // e.g., Pending, In Progress, Completed

    @Column(name = "ASSIGNED_TO")
    private String assignedTo; // e.g., User ID or Name

    @Column(name = "TASK_PRIORITY")
    private String taskPriority; // e.g., High, Medium, Low
    
    @Column(name = "TASK_CREATED_BY", length = 50)
    private String taskCreatedBy;

    @Column(name = "TASK_CREATED_DATE")
    private Date taskCreatedDate;

    @Column(name = "TASK_UPDATED_BY", length = 50)
    private String taskUpdatedBy;
    
    @Column(name = "TASK_UPDATED_DATE")
    private Date taskUpdatedDate; 
    
    @Column(name = "DELETED")
    private Boolean deleted=false;
    
    @Column(name = "DELETED_AT")
    private Date deletedAt;
    
    @Column(name = "DELETED_BY")
    private String deletedBy;
    
}