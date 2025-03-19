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
public class Pxt_fac_partDTO  {
 
    private Long fpSysId;
    private Long fpUwSysId;
    private Long fpPolIdx;
    private Long fpFacIdx;
    private Long fpFpdSysId;
    private String fpPlaceNo;
    private String fpPartCode;
    private Long fpPartPerc;
    private String fpBrkCode;
    private Long fpSi;
    private Long fpSiLc;
    private Long fpPmlSi;
    private Long fpPremLc;
    private Long fpPmlSiLc;
    private Long fpPrem;
    private Long fpOvrPremLc;
    private Long fpOvrPrem;
    private Long fpSiOrg;
    private Long fpSiLcOrg;
    private Long fpPmlSiOrg;
    private Long fpPmlSiLcOrg;
    private Long fpPremOrg;
    private Long fpPremLcOrg;
    private Long fpOvrPremOrg;
    private Long fpOvrPremLcOrg;
    
}
    
	



  

