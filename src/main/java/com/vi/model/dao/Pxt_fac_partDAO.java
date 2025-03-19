package com.vi.model.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Data
@Entity(name="pxt_fac_part")
@Table(name = "pxt_fac_part")
@AllArgsConstructor
@NoArgsConstructor
public class Pxt_fac_partDAO {

     @Id
	// @GeneratedValue(strategy = GenerationType.AUTO)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fac_part_seq")
    @SequenceGenerator(name = "fac_part_seq", sequenceName = "pxt_fac_part_SEQ", allocationSize = 1)
    @Column(name = "FP_SYS_ID")
    private Long fpSysId;

    @Column(name = "FP_UW_SYS_ID")
    private Long fpUwSysId;

    @Column(name = "FP_POL_IDX")
    private Long fpPolIdx;

    @Column(name = "FP_FAC_IDX")
    private Long fpFacIdx;

    @Column(name = "FP_FPD_SYS_ID")
    private Long fpFpdSysId;

    @Column(name = "FP_PLACE_NO")
    private String fpPlaceNo;

    @Column(name = "FP_PART_CODE")
    private String fpPartCode;

    @Column(name = "FP_PART_PERC")
    private Long fpPartPerc;

    @Column(name = "FP_BRK_CODE")
    private String fpBrkCode;

    @Column(name = "FP_SI")
    private Long fpSi;

    @Column(name = "FP_SI_LC")
    private Long fpSiLc;

    @Column(name = "FP_PML_SI")
    private Long fpPmlSi;

    @Column(name = "FP_PREM_LC")
    private Long fpPremLc;

    @Column(name = "FP_PML_SI_LC")
    private Long fpPmlSiLc;

    @Column(name = "FP_PREM")
    private Long fpPrem;

    @Column(name = "FP_OVR_PREM_LC")
    private Long fpOvrPremLc;

    @Column(name = "FP_OVR_PREM")
    private Long fpOvrPrem;

    @Column(name = "FP_SI_ORG")
    private Long fpSiOrg;

    @Column(name = "FP_SI_LC_ORG")
    private Long fpSiLcOrg;

    @Column(name = "FP_PML_SI_ORG")
    private Long fpPmlSiOrg;

    @Column(name = "FP_PML_SI_LC_ORG")
    private Long fpPmlSiLcOrg;

    @Column(name = "FP_PREM_ORG")
    private Long fpPremOrg;

    @Column(name = "FP_PREM_LC_ORG")
    private Long fpPremLcOrg;

    @Column(name = "FP_OVR_PREM_ORG")
    private Long fpOvrPremOrg;

    @Column(name = "FP_OVR_PREM_LC_ORG")
    private Long fpOvrPremLcOrg;
}
