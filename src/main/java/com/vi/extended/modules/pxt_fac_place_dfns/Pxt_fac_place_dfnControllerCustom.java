/*
 - Version Number 0.0.1
*/

package com.vi.extended.modules.pxt_fac_place_dfns;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.vi.base.modules.pxt_fac_place_dfns.Pxt_fac_place_dfnService;
import com.vi.model.dto.BrokerDTO;
import com.vi.model.dto.CommisionDTO;
import com.vi.model.dto.ParticipantDTO;
import com.vi.model.dto.Pxt_fac_hdrDTO;
import com.vi.model.dto.TaxDTO;
import lombok.extern.slf4j.Slf4j;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;




@RestController
@RequestMapping("/Placement")
@Slf4j
public class Pxt_fac_place_dfnControllerCustom {
    @Autowired
    public Environment env;
	
	@Autowired
	Pxt_fac_place_dfnServiceCustom pxt_fac_place_dfnServiceCustom;

	@Autowired
	Pxt_fac_place_dfnService pxt_fac_place_dfnService;

	@PersistenceContext
	EntityManager em;

	
	@PostMapping("/process")
    @Transactional 
    public ResponseEntity<?> createPlacement(@RequestBody Pxt_fac_hdrDTO pxt_fac_hdrDTO) throws JsonProcessingException {
        try {
            em.createNativeQuery("CALL PXP_FAC.PXP_FAC_PLACE_POP(:uwSysId, :polIdx, :facIdx, :fhSysId)")
          .setParameter("uwSysId", pxt_fac_hdrDTO.getFH_UW_SYS_ID())
          .setParameter("polIdx", pxt_fac_hdrDTO.getFH_POL_IDX())
		  .setParameter("facIdx", pxt_fac_hdrDTO.getFH_FAC_IDX())
		  .setParameter("fhSysId", pxt_fac_hdrDTO.getFH_SYS_ID())
          .executeUpdate();

            return ResponseEntity.ok().body(true);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
    
    @GetMapping("/ParticipantCode")
    public ResponseEntity<?> getParticipantCode() {
        String sql = "SELECT CD_CUST_ID, CD_CUST_NAME, CD_LOCAL_CUST_YN FROM PXM_CUST_DTL WHERE CD_CATG = 'RICOMP' AND CD_CUST_TYPE = '004'";
        List<Object[]> queryResult = em.createNativeQuery(sql).getResultList();
        List<ParticipantDTO> result = queryResult.stream()
                .map(row -> new ParticipantDTO((String) row[0], (String) row[1], (Float) row[2]))
                .toList();

        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/BrokerCode")
    public ResponseEntity<?> getBrokerCode() {
        String sql = "SELECT CD_CUST_ID, CD_CUST_NAME, CD_LOCAL_CUST_YN FROM PXM_CUST_DTL WHERE CD_CATG = 'RIBROK' AND CD_CUST_TYPE = '018'";
        List<Object[]> queryResult = em.createNativeQuery(sql).getResultList();
        List<BrokerDTO> result = queryResult.stream()
                .map(row -> new BrokerDTO((String) row[0], (String) row[1], (Float) row[2]))
                .toList();

        return ResponseEntity.ok().body(result);
    }
    
    @GetMapping("/CommisionType")
    public ResponseEntity<?> getCommisionType() {
        String sql = "SELECT CM_TYPE , CM_DESC , CM_CODE  FROM PXM_COMM_MST WHERE CM_TYPE='FAC_COMM'";
        List<Object[]> queryResult = em.createNativeQuery(sql).getResultList();
        List<CommisionDTO> result = queryResult.stream()
                .map(row -> new CommisionDTO((String) row[0], (String) row[1], (String) row[2]))
                .toList();

        return ResponseEntity.ok().body(result);
    }
    
    @GetMapping("/TaxType")
    public ResponseEntity<?> getCommisionCode() {
        String sql = "SELECT CM_TYPE , CM_DESC , CM_CODE  FROM PXM_COMM_MST WHERE CM_TYPE='FAC_PREM_TAX'";
        List<Object[]> queryResult = em.createNativeQuery(sql).getResultList();
        List<TaxDTO> result = queryResult.stream()
                .map(row -> new TaxDTO((String) row[0], (String) row[1], (String) row[2]))
                .toList();

        return ResponseEntity.ok().body(result);
    }
    



}
