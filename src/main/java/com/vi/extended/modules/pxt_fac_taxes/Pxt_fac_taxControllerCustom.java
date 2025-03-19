/*
 - Version Number 0.0.1
*/

package com.vi.extended.modules.pxt_fac_taxes;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vi.base.modules.pxt_fac_taxes.Pxt_fac_taxService;
import com.vi.model.dto.BrokerDTO;
import com.vi.model.dto.CommisionDTO;
import com.vi.model.dto.ParticipantDTO;
import com.vi.model.dto.Pxt_fac_hdrDTO;
import com.vi.model.dto.Pxt_fac_taxDTO;
import com.vi.model.dto.Pxt_fac_taxDTOCustom;
import com.vi.model.dto.TaxDTO;
import com.vi.corelib.utils.JsonHelper;


import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;




@RestController
@RequestMapping("/tax")
@Slf4j
public class Pxt_fac_taxControllerCustom {
    @Autowired
    public Environment env;
	
	@Autowired
	Pxt_fac_taxServiceCustom pxt_fac_taxServiceCustom;

	@Autowired
	Pxt_fac_taxService pxt_fac_taxService;

	@PersistenceContext
	EntityManager em;

	
    @PostMapping("/save")
    public ResponseEntity<Pxt_fac_taxDTOCustom> save(@RequestBody Pxt_fac_taxDTOCustom pxt_fac_taxDTOCustom) {
        if (pxt_fac_taxDTOCustom.getFtSysId() == null) {
            var createdDTO = pxt_fac_taxServiceCustom.create(pxt_fac_taxDTOCustom);
            return ResponseEntity.ok().body(createdDTO);
        } else {
            var updatedDTO = pxt_fac_taxServiceCustom.update(pxt_fac_taxDTOCustom);
            return ResponseEntity.ok().body(updatedDTO);
        }
    }
    



}
