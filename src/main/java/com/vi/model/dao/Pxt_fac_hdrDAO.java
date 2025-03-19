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

@Entity(name = "pxt_fac_hdr")
@Table(name = "pxt_fac_hdr")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Pxt_fac_hdrDAO  {
 
    @Id
	// @GeneratedValue(strategy = GenerationType.AUTO)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fh_seq")
    @SequenceGenerator(name = "fh_seq", sequenceName = "pxt_fac_hdr_SEQ", allocationSize = 1)
	private Long FH_SYS_ID;
	

	@Column(name="FH_UW_SYS_ID")
	private Long FH_UW_SYS_ID;
	
	@Column(name="FH_UW_NO")
	private String FH_UW_NO;
	
	@Column(name="FH_POL_IDX")
	private Long FH_POL_IDX;
	
	@Column(name="FH_FAC_IDX")
	private Long FH_FAC_IDX;
	
	@Column(name="FH_TOD")
	private Date FH_TOD;
	
	@Column(name="FH_FMD")
	private Date FH_FMD;
	
	@Column(name="FH_VER_NO")
	private String FH_VER_NO;

	@Column(name="FH_END_DESC")
	private String FH_END_DESC;

	@Column(name="FH_END_TYP")
	private String FH_END_TYP;

	@Column(name="FH_END_CODE")
	private String FH_END_CODE;

	@Column(name="FH_END_TOD")
	private Date FH_END_TOD;

	@Column(name="FH_END_FMD")
	private Date FH_END_FMD;

	@Column(name="FH_FAC_END_YN")
	private Long FH_FAC_END_YN;

	@Column(name="FH_END_NO")
	private String FH_END_NO;

	@Column(name="FH_COMP")
	private String FH_COMP;

	@Column(name="FH_DIVN")
	private String FH_DIVN;

	@Column(name="FH_LOB")
	private String FH_LOB;

	@Column(name="FH_COB")
	private String FH_COB;

	@Column(name="FH_PROD_CODE")
	private String FH_PROD_CODE;

	@Column(name="FH_SINGLE_PLACE")
	private Long FH_SINGLE_PLACE;

	@Column(name="FH_BASIS")
	private String FH_BASIS;

	@Column(name="FH_PERC_ALL_RSK_YN")
	private Long FH_PERC_ALL_RSK_YN;

	@Column(name="FH_FAC_PERC")
	private Long FH_FAC_PERC;

	@Column(name="FH_APD")
	private Date FH_APD;



	

	
}