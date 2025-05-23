/*
 Version Number 0.0.1
*/

package com.vi.model.dto;


import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor 
public class SummaryDTO  {
 
	private Long userSeqNo;
    private String userRole;
    
    private Long totalCount;
    private BigDecimal newlyAdded;
    private BigDecimal onPriority;
    private BigDecimal completed;
    
    private Long totalEnquiry;
    private Long pendingEnquiry;
    private Long meetingReqEnquiry;
    private Long infoReqEnquiry;
    private Long infoProEnquiry;
    private Long quoteReqEnquiry;
    private Long quoteGenEnquiry;
    private Long accEnquiry;
    private Long rejEnquiry;
    
    private Long totalQuote;
    private Long totalProspect;
    
    private String monthtotalCount;
    private String monthnewlyAdded;
    private String monthonPriority;
    private String monthcompleted;
    
    private String monthtotalEnquiry;
    private String monthpendingEnquiry;
    private String monthmeetingReqEnquiry;
    private String monthinfoReqEnquiry;
    private String monthinfoProEnquiry;
    private String monthquoteReqEnquiry;
    private String monthquoteGenEnquiry;
    private String monthaccEnquiry;
    private String monthrejEnquiry;
    
    private String monthtotalQuote;
    private String monthtotalProspect;
    
    
}
    
	





  

