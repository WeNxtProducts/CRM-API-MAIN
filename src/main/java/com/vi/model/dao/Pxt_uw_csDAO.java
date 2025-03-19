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

@Entity(name = "pxt_uw_cs")
@Table(name = "PXT_UW_CS")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Pxt_uw_csDAO  {
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "UCS_SYS_ID")
    private Long ucsSysId;

    @Column(name = "UCS_PML_PERC")
    private Double ucsPmlPerc;

    @Column(name = "UCS_PML_SI_LC")
    private Double ucsPmlSiLc;

    @Column(name = "UCS_PML_SI")
    private Double ucsPmlSi;

    @Column(name = "UCS_RATE_TYP")
    private String ucsRateTyp;

    @Column(name = "UCS_RSK_REF_NO")
    private Long ucsRskRefNo;

    @Column(name = "UCS_RSK_ID")
    private String ucsRskId;

    @Column(name = "UCS_PLAN_ID")
    private String ucsPlanId;

    @Column(name = "UCS_REC_TYP")
    private String ucsRecTyp;

    @Column(name = "UCS_PROD_ID")
    private String ucsProdId;

    @Column(name = "UCS_ADD_PREM_YN")
    private Integer ucsAddPremYn;

    @Column(name = "UCS_WAR_YN")
    private Integer ucsWarYn;

    @Column(name = "UCS_RI_SI_YN")
    private Integer ucsRiSiYn;

    @Column(name = "UCS_LOSS_LIMIT_LC")
    private Double ucsLossLimitLc;

    @Column(name = "UCS_LOSS_LIMIT")
    private Double ucsLossLimit;

    @Column(name = "UCS_MAX_SI_LC")
    private Double ucsMaxSiLc;

    @Column(name = "UCS_MAX_SI")
    private Double ucsMaxSi;

    @Column(name = "UCS_MIN_SI_LC")
    private Double ucsMinSiLc;

    @Column(name = "UCS_MIN_SI")
    private Double ucsMinSi;

    @Column(name = "UCS_UPD_ORG_PREM_LC")
    private Double ucsUpdOrgPremLc;

    @Column(name = "UCS_UPD_ORG_PREM")
    private Double ucsUpdOrgPrem;

    @Column(name = "UCS_UPD_PREM_LC")
    private Double ucsUpdPremLc;

    @Column(name = "UCS_UPD_PREM")
    private Double ucsUpdPrem;

    @Column(name = "UCS_RISK_TYP")
    private String ucsRiskTyp;

    @Column(name = "UCS_RATE_KEY_CHG_YN")
    private Integer ucsRateKeyChgYn;

    @Column(name = "UCS_RATE_KEY_ID")
    private Long ucsRateKeyId;

    @Column(name = "UCS_UPD_RATE")
    private Double ucsUpdRate;

    @Column(name = "UCS_RATE_PER")
    private Double ucsRatePer;

    @Column(name = "UCS_RATE")
    private Double ucsRate;

    @Column(name = "UCS_ORG_PREM_LC")
    private Double ucsOrgPremLc;

    @Column(name = "UCS_ORG_PREM")
    private Double ucsOrgPrem;

    @Column(name = "UCS_PREM_LC")
    private Double ucsPremLc;

    @Column(name = "UCS_PREM")
    private Double ucsPrem;

    @Column(name = "UCS_ORG_SI_LC")
    private Double ucsOrgSiLc;

    @Column(name = "UCS_ORG_SI")
    private Double ucsOrgSi;

    @Column(name = "UCS_SI_LC")
    private Double ucsSiLc;

    @Column(name = "UCS_SI")
    private Double ucsSi;

    @Column(name = "UCS_END_TOD")
    private Date ucsEndTod;

    @Column(name = "UCS_END_FMD")
    private Date ucsEndFmd;

    @Column(name = "UCS_POL_IDX")
    private Long ucsPolIdx;

    @Column(name = "UCS_TOD")
    private Date ucsTod;

    @Column(name = "UCS_FMD")
    private Date ucsFmd;

    @Column(name = "UCS_SMI_DESC")
    private String ucsSmiDesc;

    @Column(name = "UCS_SMI_ID")
    private String ucsSmiId;

    @Column(name = "UCS_CVR_DESC")
    private String ucsCvrDesc;

    @Column(name = "UCS_CVR_ID")
    private String ucsCvrId;

    @Column(name = "UCS_UR_SYS_ID")
    private Long ucsUrSysId;

    @Column(name = "UCS_UW_SYS_ID")
    private Long ucsUwSysId;

    @Column(name = "UCS_RSK_CAT")
    private String ucsRskCat;

    @Column(name = "UCS_TD_TTY_ID")
    private String ucsTdTtyId;

    @Column(name = "UCS_TD_SYS_ID")
    private Long ucsTdSysId;

    @Column(name = "UCS_TTY_LIMIT")
    private Double ucsTtyLimit;

    @Column(name = "UCS_BUS_TYP")
    private String ucsBusTyp;

    @Column(name = "UCS_ERR_YN")
    private Integer ucsErrYn;

    @Column(name = "UCS_POL_NO")
    private String ucsPolNo;

    @Column(name = "UCS_RETN_PER")
    private Double ucsRetnPer;

    @Column(name = "UCS_CQS_PER")
    private Double ucsCqsPer;

    @Column(name = "UCS_LMT_1")
    private Double ucsLmt1;

    @Column(name = "UCS_LMT_2")
    private Double ucsLmt2;

    @Column(name = "UCS_LMT_3")
    private Double ucsLmt3;

    @Column(name = "UCS_FAC_PER")
    private Double ucsFacPer;

    @Column(name = "UCS_FAC_PML_SI")
    private Double ucsFacPmlSi;

    @Column(name = "UCS_FAC_PML_SI_LC")
    private Double ucsFacPmlSiLc;

    @Column(name = "UCS_LMT_EXCEED")
    private String ucsLmtExceed;

    @Column(name = "UCS_ORG_PML_SI_LC")
    private Double ucsOrgPmlSiLc;

    @Column(name = "UCS_ORG_PML_SI")
    private Double ucsOrgPmlSi;

    @Column(name = "UCS_END_NO")
    private String ucsEndNo;

    @Column(name = "UCS_RI_BASIS")
    private String ucsRiBasis;

    @Column(name = "UCS_TTY_SI")
    private Double ucsTtySi;

    @Column(name = "UCS_PREM_CUR")
    private String ucsPremCur;

    @Column(name = "UCS_TTY_PREM")
    private Double ucsTtyPrem;

    @Column(name = "UCS_SI_CUR")
    private String ucsSiCur;

    @Column(name = "UCS_END_TYPE")
    private String ucsEndType;

}