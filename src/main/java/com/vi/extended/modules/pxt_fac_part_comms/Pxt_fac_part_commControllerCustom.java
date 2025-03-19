/*
 - Version Number 0.0.1
*/

package com.vi.extended.modules.pxt_fac_part_comms;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vi.base.modules.pxt_fac_part_comms.Pxt_fac_part_commService;
import com.vi.model.dto.BrokerDTO;
import com.vi.model.dto.CommisionDTO;
import com.vi.model.dto.ParticipantDTO;
import com.vi.model.dto.Pxt_fac_hdrDTO;
import com.vi.model.dto.Pxt_fac_part_commDTO;
import com.vi.model.dto.Pxt_fac_part_commDTOCustom;
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
@RequestMapping("/commission")
@Slf4j
public class Pxt_fac_part_commControllerCustom {
    @Autowired
    public Environment env;
	
	@Autowired
	Pxt_fac_part_commServiceCustom pxt_fac_part_commServiceCustom;

	@Autowired
	Pxt_fac_part_commService pxt_fac_part_commService;

	@PersistenceContext
	EntityManager em;

	
    @PostMapping("/save")
    public ResponseEntity<Pxt_fac_part_commDTOCustom> save(@RequestBody Pxt_fac_part_commDTOCustom pxt_fac_part_commDTOCustom) {
        if (pxt_fac_part_commDTOCustom.getFpcSysId() == null) {
            var createdDTO = pxt_fac_part_commServiceCustom.create(pxt_fac_part_commDTOCustom);
            return ResponseEntity.ok().body(createdDTO);
        } else {
            var updatedDTO = pxt_fac_part_commServiceCustom.update(pxt_fac_part_commDTOCustom);
            return ResponseEntity.ok().body(updatedDTO);
        }
    }
    



}
