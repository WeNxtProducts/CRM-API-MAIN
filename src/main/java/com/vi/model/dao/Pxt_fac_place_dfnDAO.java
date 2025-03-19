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

@Entity(name = "pxt_fac_place_dfn")
@Table(name = "pxt_fac_place_dfn")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Pxt_fac_place_dfnDAO  {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long FPD_SYS_ID;

    @Column(name = "FPD_POL_IDX")
    private Long FPD_POL_IDX;

    @Column(name = "FPD_UW_SYS_ID")
    private Long FPD_UW_SYS_ID;

    @Column(name = "FPD_SEC_ACNT_YN")
    private Long FPD_SEC_ACNT_YN;

    @Column(name = "FPD_FAC_PLACE_REF_NO")
    private Long FPD_FAC_PLACE_REF_NO;

    @Column(name = "FPD_FAC_IDX")
    private Long FPD_FAC_IDX;

    @Column(name = "FPD_PLACE_NO")
    private String FPD_PLACE_NO; 

    @Column(name = "FPD_FH_SYS_ID")
    private Long FPD_FH_SYS_ID;

    @Column(name = "FPD_SI")
    private Long FPD_SI = 0L; 

    @Column(name = "FPD_SI_LC")
    private Long FPD_SI_LC = 0L;

    @Column(name = "FPD_PML_SI")
    private Long FPD_PML_SI = 0L;

    @Column(name = "FPD_PML_SI_LC")
    private Long FPD_PML_SI_LC = 0L;

    @Column(name = "FPD_PREM")
    private Long FPD_PREM = 0L; 

    @Column(name = "FPD_PREM_LC")
    private Long FPD_PREM_LC = 0L;

    @Column(name = "FPD_FAC_SI")
    private Long FPD_FAC_SI = 0L; 

    @Column(name = "FPD_FAC_SI_LC")
    private Long FPD_FAC_SI_LC = 0L;

    @Column(name = "FPD_FAC_PML_SI")
    private Long FPD_FAC_PML_SI = 0L; 

    @Column(name = "FPD_FAC_PML_SI_LC")
    private Long FPD_FAC_PML_SI_LC = 0L;

    @Column(name = "FPD_FAC_PREM")
    private Long FPD_FAC_PREM = 0L; 

    @Column(name = "FPD_FAC_PREM_LC")
    private Long FPD_FAC_PREM_LC = 0L;

    @Column(name = "FPD_OVR_PREM")
    private Long FPD_OVR_PREM = 0L;

    @Column(name = "FPD_OVR_PREM_LC")
    private Long FPD_OVR_PREM_LC = 0L;

    @Column(name = "FPD_SPL_PREM")
    private Long FPD_SPL_PREM = 0L;

    @Column(name = "FPD_SPL_PREM_LC")
    private Long FPD_SPL_PREM_LC = 0L;

    @Column(name = "FPD_SI_ORG")
    private Long FPD_SI_ORG = 0L;

    @Column(name = "FPD_SI_LC_ORG")
    private Long FPD_SI_LC_ORG = 0L;

    @Column(name = "FPD_PML_SI_ORG")
    private Long FPD_PML_SI_ORG = 0L;

    @Column(name = "FPD_PML_SI_LC_ORG")
    private Long FPD_PML_SI_LC_ORG = 0L;

    @Column(name = "FPD_PREM_ORG")
    private Long FPD_PREM_ORG = 0L;

    @Column(name = "FPD_PREM_LC_ORG")
    private Long FPD_PREM_LC_ORG = 0L;

    @Column(name = "FPD_FAC_SI_ORG")
    private Long FPD_FAC_SI_ORG = 0L;

    @Column(name = "FPD_FAC_SI_LC_ORG")
    private Long FPD_FAC_SI_LC_ORG = 0L;

    @Column(name = "FPD_FAC_PML_SI_ORG")
    private Long FPD_FAC_PML_SI_ORG = 0L;

    @Column(name = "FPD_FAC_PML_SI_LC_ORG")
    private Long FPD_FAC_PML_SI_LC_ORG = 0L;

    @Column(name = "FPD_FAC_PREM_ORG")
    private Long FPD_FAC_PREM_ORG = 0L;

    @Column(name = "FPD_FAC_PREM_LC_ORG")
    private Long FPD_FAC_PREM_LC_ORG = 0L;

    @Column(name = "FPD_OVR_PREM_ORG")
    private Long FPD_OVR_PREM_ORG = 0L;

    @Column(name = "FPD_OVR_PREM_LC_ORG")
    private Long FPD_OVR_PREM_LC_ORG = 0L;

    @Column(name = "FPD_SPL_PREM_ORG")
    private Long FPD_SPL_PREM_ORG = 0L;

    @Column(name = "FPD_SPL_PREM_LC_ORG")
    private Long FPD_SPL_PREM_LC_ORG = 0L;





}