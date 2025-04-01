/*
 Version Number 0.0.1
*/

package com.vi.model.dto;

import com.vi.model.BaseDto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor 
public class UserDTO  {
 
    
    private Long userSeqNo;
    private String userRefId;
    private String userName;
    private String userEmail;
    private String userPhoneNo;
    private String userRole; 
    private String userPassword;
    private String assignedTo; 
    private String createdBy;
    private Date createdDate=Date.from(Instant.now());;
    private String updatedBy;
    private Date updatedDate;
    private Boolean deleted=false;
    private Date deletedAt;
    private String deletedBy;


}
    
	



  

