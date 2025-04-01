package com.vi.model.dto;

import java.util.Date;

import com.vi.model.dao.UserDAO;

import java.sql.Time;
import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor 
public class ActivityLogDTOCustom {
	
    private Long activityLogSeqNo;
    private Long userSeqNo;
    private UserDAO user;
    private String activityLogType; // e.g., Enquiry Generated, Quote Converted
    private String activityLogDescription;
    private Date activityLogDate;
    private String activityLogStatus; // e.g., Pending, Completed
    private String activityLogCreatedBy;
    private Date activityLogCreatedDate= Date.from(Instant.now());
    private String activityLogUpdatedBy;
    private Date activityLogUpdatedDate;
    private Boolean deleted=false;
    private Date deletedAt;
    private String deletedBy;

}
