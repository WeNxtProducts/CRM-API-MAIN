/*
 - Version Number 0.0.1
*/

package com.vi.extended.modules.pxt_fac_parts;

import com.vi.base.modules.pxt_fac_parts.Pxt_fac_partService;
import com.vi.model.dto.Pxt_fac_partDTOCustom;

import lombok.extern.slf4j.Slf4j;
import java.util.Map;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import jakarta.transaction.Transactional;




@RestController
@RequestMapping("/Participant")
@Slf4j
public class Pxt_fac_partControllerCustom {
    @Autowired
    public Environment env;
	
	@Autowired
	Pxt_fac_partServiceCustom pxt_fac_partServiceCustom;

	@Autowired
	Pxt_fac_partService pxt_fac_partService;

	@PersistenceContext
	EntityManager em;

	
    @PostMapping("/save")
    public ResponseEntity<Pxt_fac_partDTOCustom> save(@RequestBody Pxt_fac_partDTOCustom pxt_fac_partDTOCustom) {
        if (pxt_fac_partDTOCustom.getFpSysId() == null) {
            var createdDTO = pxt_fac_partServiceCustom.create(pxt_fac_partDTOCustom);
            return ResponseEntity.ok().body(createdDTO);
        } else {
            var updatedDTO = pxt_fac_partServiceCustom.update(pxt_fac_partDTOCustom);
            return ResponseEntity.ok().body(updatedDTO);
        }
    }

    @PostMapping("/approve")
    @Transactional
    public ResponseEntity<?> approve(@RequestBody Pxt_fac_partDTOCustom pxt_fac_partDTOCustom) {
        try {
            Long uwSysId = pxt_fac_partDTOCustom.getFpUwSysId();
            Long polIdx = pxt_fac_partDTOCustom.getFpPolIdx();
            Long facIdx = pxt_fac_partDTOCustom.getFpFacIdx();

            StoredProcedureQuery query = em.createStoredProcedureQuery("PXP_FAC.PXP_FAC_ACNT_GEN")
                    .registerStoredProcedureParameter("P_UW_SYS_ID", Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter("P_POL_IDX", Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter("P_FAC_IDX", Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter("P_APPR_STS", Integer.class, ParameterMode.OUT)
                    .registerStoredProcedureParameter("P_ERR_CODE", String.class, ParameterMode.OUT)
                    .registerStoredProcedureParameter("P_ERR_MSG", String.class, ParameterMode.OUT)
                    .setParameter("P_UW_SYS_ID", uwSysId)
                    .setParameter("P_POL_IDX", polIdx)
                    .setParameter("P_FAC_IDX", facIdx);

            query.execute();

            Integer approvalStatus = (Integer) query.getOutputParameterValue("P_APPR_STS");
            String errorCode = (String) query.getOutputParameterValue("P_ERR_CODE");
            String errorMessage = (String) query.getOutputParameterValue("P_ERR_MSG");

            return ResponseEntity.ok(Map.of(
                    "approvalStatus", approvalStatus,
                    "errorCode", errorCode,
                    "errorMessage", errorMessage
            ));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Error executing stored procedure", "details", e.getMessage()));
        }
    }

    

 

}
