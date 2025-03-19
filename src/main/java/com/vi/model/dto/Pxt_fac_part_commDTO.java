/*
 Version Number 0.0.1
*/

package com.vi.model.dto;

import com.vi.model.BaseDto;
import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor 
public class Pxt_fac_part_commDTO  {
 
    private Long fpcSysId;
    private Long fpcUwSysId;
    private Long fpcFpSysId;
    private Long fpcPolIdx;
    private Long fpcFacIdx;
    private String fpcCommCode;
    private Double fpcCommPer;
    private String fpcCommTyp;
    private Double fpcComm;
    private Double fpcCommLc;
    private Double fpcCommOrg;
    private Double fpcCommLcOrg;
    
}
    
	



  

