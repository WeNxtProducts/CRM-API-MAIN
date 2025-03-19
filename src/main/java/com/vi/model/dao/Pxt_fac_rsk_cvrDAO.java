/*
 Version Number 0.0.1
*/

package com.vi.model.dao;

import com.vi.model.BaseDao;
import lombok.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import jakarta.persistence.*;
import java.util.Date;

@Entity(name = "pxt_fac_rsk_cvr")
@Table(name = "pxt_fac_rsk_cvr")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Pxt_fac_rsk_cvrDAO  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long FRC_SYS_ID;
	
	@Column(name="FRC_FH_SYS_ID ")
	private Long FRC_FH_SYS_ID;
	
	@Column(name="FRC_UW_SYS_ID")
	private Long FRC_UW_SYS_ID;
	
	@Column(name="FRC_POL_IDX")
	private Long FRC_POL_IDX;
	
	@Column(name="FRC_FAC_IDX")
	private Long FRC_FAC_IDX;
	
	@Column(name="FRC_UR_RSK_ID")
	private String FRC_UR_RSK_ID;
	
	@Column(name="FRC_UR_SYS_ID")
	private Long FRC_UR_SYS_ID;
	
	@Column(name="FRC_RISK_TYP")
	private String FRC_RISK_TYP;

	@Column(name="FRC_PROD_ID")
	private String FRC_PROD_ID;

	@Column(name="FRC_RSK_CAT")
	private String FRC_RSK_CAT;

	@Column(name="FRC_BUS_TYP")
	private String FRC_BUS_TYP;

	@Column(name="FRC_TOD")
	private Date FRC_TOD;

	@Column(name="FRC_FMD")
	private Date FRC_FMD;

	@Column(name="FRC_END_TOD")
	private Date FRC_END_TOD;

	@Column(name="FRC_END_FMD")
	private Date FRC_END_FMD;

	
	@Column(name="FRC_CVR_CODE")
	private String FRC_CVR_CODE;

	@Column(name="FRC_RISK_REF_NO")
	private Long FRC_RISK_REF_NO;

	@Column(name="FRC_FAC_PERC")
	private Long FRC_FAC_PERC;

	@Column(name="FRC_FAC_RATE_YN")
	private Long FRC_FAC_RATE_YN;

	@Column(name="FRC_FAC_RATE")
	private Long FRC_FAC_RATE;

	@Column(name="FRC_UW_RATE")
	private Long FRC_UW_RATE;

	@Column(name="FRC_RATE_PER")
	private Long FRC_RATE_PER;

	@Column(name="FRC_RATE_TYP")
	private String FRC_RATE_TYP;

	@Column(name="FRC_WAR_YN")
	private Long FRC_WAR_YN;

	@Column(name="FRC_SI")
	private Long FRC_SI;

	@Column(name="FRC_SI_LC")
	private Long FRC_SI_LC;

	@Column(name="FRC_PML_PERC")
	private Long FRC_PML_PERC;

	@Column(name="FRC_PML_SI")
	private Long FRC_PML_SI;

	@Column(name="FRC_PML_SI_LC")
	private Long FRC_PML_SI_LC;

	@Column(name="FRC_PREM")
	private Long FRC_PREM;

	@Column(name="FRC_PREM_LC")
	private Long FRC_PREM_LC;

	@Column(name="FRC_RI_SI_YN")
	private Long FRC_RI_SI_YN;

	@Column(name="FRC_FAC_SI")
	private Long FRC_FAC_SI;

	@Column(name="FRC_FAC_SI_LC")
	private Long FRC_FAC_SI_LC;

	@Column(name="FRC_FAC_PML_SI")
	private Long FRC_FAC_PML_SI;

	@Column(name="FRC_FAC_PML_SI_LC")
	private Long FRC_FAC_PML_SI_LC;

	@Column(name="FRC_FAC_PREM")
	private Long FRC_FAC_PREM;

	@Column(name="FRC_FAC_PREM_LC")
	private Long FRC_FAC_PREM_LC;

	@Column(name="FRC_OVR_PREM")
	private Long FRC_OVR_PREM;

	@Column(name="FRC_OVR_PREM_LC")
	private Long FRC_OVR_PREM_LC;

	@Column(name="FRC_SPL_PREM")
	private Long FRC_SPL_PREM;

	@Column(name="FRC_SPL_PREM_LC")
	private Long FRC_SPL_PREM_LC;

	@Column(name="FRC_SI_ORG")
	private Long FRC_SI_ORG;

	@Column(name="FRC_SI_LC_ORG")
	private Long FRC_SI_LC_ORG;

	@Column(name="FRC_PML_SI_ORG")
	private Long FRC_PML_SI_ORG;

	@Column(name="FRC_PML_SI_LC_ORG")
	private Long FRC_PML_SI_LC_ORG;

	@Column(name="FRC_PREM_ORG")
	private Long FRC_PREM_ORG;

	@Column(name="FRC_PREM_LC_ORG")
	private Long FRC_PREM_LC_ORG;

	@Column(name="FRC_FAC_SI_ORG")
	private Long FRC_FAC_SI_ORG;

	@Column(name="FRC_FAC_SI_LC_ORG")
	private Long FRC_FAC_SI_LC_ORG;

	@Column(name="FRC_FAC_PML_SI_ORG")
	private Long FRC_FAC_PML_SI_ORG;

	@Column(name="FRC_FAC_PML_SI_LC_ORG")
	private Long FRC_FAC_PML_SI_LC_ORG;

	@Column(name="FRC_FAC_PREM_ORG")
	private Long FRC_FAC_PREM_ORG;

	@Column(name="FRC_FAC_PREM_LC_ORG")
	private Long FRC_FAC_PREM_LC_ORG;

	@Column(name="FRC_REC_TYP")
	private String FRC_REC_TYP;

	@Column(name="FRC_PLACE_REF_NO")
	private Long FRC_PLACE_REF_NO;

	@Column(name="FRC_TD_SYS_ID")
	private Long FRC_TD_SYS_ID;

	@Column(name="FRC_TD_TTY_ID")
	private String FRC_TD_TTY_ID;

	@Column(name="FRC_SEC_CODE")
	private String FRC_SEC_CODE;




}