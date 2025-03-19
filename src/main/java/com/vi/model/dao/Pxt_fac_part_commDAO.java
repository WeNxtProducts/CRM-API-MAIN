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

@Entity(name = "pxt_fac_part_comm")
@Table(name = "pxt_fac_part_comm")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Pxt_fac_part_commDAO  {
	
    @Id
	// @GeneratedValue(strategy = GenerationType.AUTO)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fac_part_comm_seq")
    @SequenceGenerator(name = "fac_part_comm_seq", sequenceName = "pxt_fac_part_comm_SEQ", allocationSize = 1)
    @Column(name = "FPC_SYS_ID")
    private Long fpcSysId;

    @Column(name = "FPC_UW_SYS_ID")
    private Long fpcUwSysId;

    @Column(name = "FPC_FP_SYS_ID")
    private Long fpcFpSysId;

    @Column(name = "FPC_POL_IDX")
    private Long fpcPolIdx;

    @Column(name = "FPC_FAC_IDX")
    private Long fpcFacIdx;

    @Column(name = "FPC_COMM_CODE", length = 20)
    private String fpcCommCode;

    @Column(name = "FPC_COMM_PER")
    private Double fpcCommPer;

    @Column(name = "FPC_COMM_TYP", length = 10)
    private String fpcCommTyp;

    @Column(name = "FPC_COMM")
    private Double fpcComm;

    @Column(name = "FPC_COMM_LC")
    private Double fpcCommLc;

    @Column(name = "FPC_COMM_ORG")
    private Double fpcCommOrg;

    @Column(name = "FPC_COMM_LC_ORG")
    private Double fpcCommLcOrg;

}