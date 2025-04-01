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
@Entity(name="WN_ACTIVITYLOG")
@Table(name = "WN_ACTIVITYLOG")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ActivityLogDAO {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ACTIVITYLOG_SEQ_NO")
    private Long activityLogSeqNo;
    
    @Column(name = "USER_SEQ_NO")
	private Long userSeqNo;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name="USER_SEQ_NO", referencedColumnName = "USER_SEQ_NO", insertable=false, updatable=false)
    private UserDAO user;

    @Column(name = "ACTIVITYLOG_TYPE")
    private String activityLogType; // e.g., Enquiry Generated, Quote Converted

    @Column(name = "ACTIVITYLOG_DESCRIPTION")
    private String activityLogDescription;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ACTIVITYLOG_DATE")
    private Date activityLogDate;

    @Column(name = "ACTIVITYLOG_STATUS")
    private String activityLogStatus; // e.g., Pending, Completed

    @Column(name = "ACTIVITYLOG_CREATED_BY")
    private String activityLogCreatedBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ACTIVITYLOG_CREATED_DATE")
    private Date activityLogCreatedDate;

    @Column(name = "ACTIVITYLOG_UPDATED_BY")
    private String activityLogUpdatedBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ACTIVITYLOG_UPDATED_DATE")
    private Date activityLogUpdatedDate;
    
    @Column(name = "DELETED")
    private Boolean deleted=false;
    
    @Column(name = "DELETED_AT")
    private Date deletedAt;
    
    @Column(name = "DELETED_BY")
    private String deletedBy;

}
