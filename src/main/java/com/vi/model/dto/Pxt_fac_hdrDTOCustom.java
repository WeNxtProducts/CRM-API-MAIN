/*
 Version Number 0.0.1
*/

package com.vi.model.dto;

import com.vi.model.BaseDto;
import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Pxt_fac_hdrDTOCustom  {
 
  private Long FH_SYS_ID;
  
  private Long FH_UW_SYS_ID;

  private String FH_UW_NO;

  private Long FH_POL_IDX;

  private Long FH_FAC_IDX = 0L;

  private Date FH_TOD;

  private Date FH_FMD;

  private String FH_VER_NO;

  private String FH_END_DESC;

  private String FH_END_TYP;

  private String FH_END_CODE;

  private Date FH_END_TOD;

  private Date FH_END_FMD;

  private Long FH_FAC_END_YN;

  private String FH_END_NO;

  private String FH_COMP;

  private String FH_DIVN;

  private String FH_LOB;

  private String FH_COB;

  private String FH_PROD_CODE;

  private Long FH_SINGLE_PLACE;

  private String FH_BASIS;

  private Long FH_PERC_ALL_RSK_YN;

  private Long FH_FAC_PERC;

  private Date FH_APD;



  

}