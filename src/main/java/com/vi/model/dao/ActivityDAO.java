package com.vi.model.dao;

import java.sql.Time;
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
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Entity(name="WN_ACTIVITY")
@Table(name = "WN_ACTIVITY")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ActivityDAO {

    @Id
	// @GeneratedValue(strategy = GenerationType.AUTO)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ACTIVITY_SEQ_NO")
    private Long activitySeqNo;

    @Column(name = "ACTIVITY_REF_ID")
    private String activityRefId;
    
    @Column(name = "USER_SEQ_NO")
   	private Long userSeqNo;
  
    @Column(name = "ENQ_SEQ_NO")
    private Long enqSeqNo;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name="ENQ_SEQ_NO", referencedColumnName = "ENQ_SEQ_NO", insertable=false, updatable=false)
    private EnquiryDAO enquiry;
    
    //@NotBlank(message = "Activity type is required")
    @Column(name = "ACTIVITY_TYPE")
    private String activityType; // "APPOINTMENT" or "EVENT"

    @Column(name = "ACTIVITY_SUBJECT")
    private String activitySubject;

    @Column(name = "ACTIVITY_DESCRIPTION")
    private String activityDescription;

    //@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ACTIVITY_START_DATE")
    private Date activityStartDate;
    
    //@Temporal(TemporalType.DATE)
    @Column(name = "ACTIVITY_END_DATE")
    private Date activityEndDate;

    @Column(name = "ACTIVITY_START_TIME")
    private Time activityStartTime;

    @Column(name = "ACTIVITY_END_TIME")
    private Time activityEndTime;

    @Column(name = "ACTIVITY_STATUS")
    private String activityStatus="Pending";
    
    @Column(name = "PROBABILITY_PERCENTAGE")
    private Long probabilityPercentage;

    @Column(name = "ACTIVITY_CREATED_BY")
    private String activityCreatedBy;

    //@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ACTIVITY_CREATED_DATE")
    private Date activityCreatedDate;

    @Column(name = "ACTIVITY_UPDATED_BY")
    private String activityUpdatedBy;

    //@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ACTIVITY_UPDATED_DATE")
    private Date activityUpdatedDate;
  
    @Column(name = "ACTIVITY_PRIORITY")
    private String activityPriority;

    @Column(name = "REMINDER_SENT")
    private boolean reminderSent = true;
    
    @Column(name = "DELETED")
    private Boolean deleted=false;
    
    @Column(name = "DELETED_AT")
    private Date deletedAt;
    
    @Column(name = "DELETED_BY")
    private String deletedBy;
    
    

}