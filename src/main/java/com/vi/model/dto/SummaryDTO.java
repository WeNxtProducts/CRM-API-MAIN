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
    private Long totalCount;
    private BigDecimal newlyAdded ;
    private BigDecimal onPriority;
    private BigDecimal completed;
    private String monthLeads;
   

}
    
	



  

