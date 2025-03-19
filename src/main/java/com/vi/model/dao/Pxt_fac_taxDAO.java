package com.vi.model.dao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity(name="pxt_fac_tax")
@Table(name="pxt_fac_tax")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pxt_fac_taxDAO {

    @Id
	
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fac_tax_seq")
    @SequenceGenerator(name = "fac_tax_seq", sequenceName = "pxt_fac_tax_SEQ", allocationSize = 1)
    @Column(name = "FT_SYS_ID")
    private Long ftSysId;

    @Column(name = "FT_POL_IDX")
    private Long ftPolIdx;

    @Column(name = "FT_FAC_IDX")
    private Long ftFacIdx;

    @Column(name = "FT_FP_SYS_ID")
    private Long ftFpSysId;

    @Column(name = "FT_FPS_SYS_ID")
    private Long ftFpsSysId;

    @Column(name = "FT_TAX_CODE")
    private String ftTaxCode;

    @Column(name = "FT_TAX_DESC")
    private String ftTaxDesc;

    @Column(name = "FT_TAX_TYP")
    private String ftTaxTyp;

    @Column(name = "FT_TAX_ON")
    private String ftTaxOn;

    @Column(name = "FT_TAX_PERC")
    private Long ftTaxPerc;

    @Column(name = "FT_TAX")
    private Long ftTax;

    @Column(name = "FT_TAX_LC")
    private Long ftTaxLc;

    @Column(name = "FT_SRC_AMT")
    private Long ftSrcAmt;

    @Column(name = "FT_SRC_AMT_LC")
    private Long ftSrcAmtLc;

    @Column(name = "FT_TAX_ORG")
    private Long ftTaxOrg;

    @Column(name = "FT_TAX_LC_ORG")
    private Long ftTaxLcOrg;
}
