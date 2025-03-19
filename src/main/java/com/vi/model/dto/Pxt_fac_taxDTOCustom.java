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
public class Pxt_fac_taxDTOCustom  {
 
    private Long ftSysId;
    private Long ftPolIdx;
    private Long ftFacIdx;
    private Long ftFpSysId;
    private Long ftFpsSysId;
    private String ftTaxCode;
    private String ftTaxDesc;
    private String ftTaxTyp;
    private String ftTaxOn;
    private Long ftTaxPerc;
    private Long ftTax;
    private Long ftTaxLc;
    private Long ftSrcAmt;
    private Long ftSrcAmtLc;
    private Long ftTaxOrg;
    private Long ftTaxLcOrg;
    
}
    
	



  

