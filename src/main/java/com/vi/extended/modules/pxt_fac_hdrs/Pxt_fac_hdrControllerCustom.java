/*
 - Version Number 0.0.1
*/

package com.vi.extended.modules.pxt_fac_hdrs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vi.base.modules.pxt_fac_hdrs.Pxt_fac_hdrService;
import com.vi.base.modules.pxt_fac_rsk_cvrs.Pxt_fac_rsk_cvrService;
import com.vi.model.dto.Pxt_fac_hdrDTO;


import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.http.MediaType;
import java.util.Base64;


@RestController
@RequestMapping("/Pxtfachdr")
@Slf4j
public class Pxt_fac_hdrControllerCustom {
    @Autowired
    public Environment env;
	
	@Autowired
	Pxt_fac_hdrServiceCustom pxt_fac_hdrServiceCustom;

	@Autowired
	Pxt_fac_hdrService pxt_fac_hdrService;

	@PersistenceContext
	EntityManager em;

	@Autowired   
	Pxt_fac_rsk_cvrService pxt_fac_rsk_cvrService;

	@PostMapping("/fetchDetails")
    @Transactional 
	public ResponseEntity<?> fetchDetails(@RequestBody Pxt_fac_hdrDTO pxt_fac_hdrDTO) throws JsonProcessingException {

		var pxt_fac_hdrPxt_fac_hdrDTO = pxt_fac_hdrService.create(pxt_fac_hdrDTO);
		System.out.print(pxt_fac_hdrPxt_fac_hdrDTO);

		em.createNativeQuery("CALL PXP_FAC.PXP_FAC_POP(:uwSysId, :polIdx, :facIdx, :facPerc)")
          .setParameter("uwSysId", pxt_fac_hdrPxt_fac_hdrDTO.getFH_UW_SYS_ID())
          .setParameter("polIdx", pxt_fac_hdrPxt_fac_hdrDTO.getFH_POL_IDX())
		  .setParameter("facIdx", pxt_fac_hdrPxt_fac_hdrDTO.getFH_FAC_IDX())
		  .setParameter("facPerc", pxt_fac_hdrPxt_fac_hdrDTO.getFH_FAC_PERC())
          .executeUpdate();


		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, Object> json = Map.of("FRC_FH_SYS_ID",pxt_fac_hdrPxt_fac_hdrDTO.getFH_SYS_ID() );

		JsonNode jsonRequest = objectMapper.convertValue(json, JsonNode.class);

		var pxt_fac_rsk_cvrPxt_fac_rsk_cvrDTO = pxt_fac_rsk_cvrService.filterData(jsonRequest);
		

		ObjectMapper objectMapper1 = new ObjectMapper();
		JsonNode jsonArray = objectMapper1.convertValue(pxt_fac_rsk_cvrPxt_fac_rsk_cvrDTO, JsonNode.class);

		List<Map<String, Object>> dataList = new ArrayList<>();
        jsonArray.forEach(node -> dataList.add(objectMapper1.convertValue(node, Map.class)));

        System.out.print(dataList);


        Map<String, Map<String, Object>> structuredData = organizeData(dataList);

        System.out.println(structuredData);

		return ResponseEntity.ok().body(structuredData);
	}

	public static String invokeOracleProcedure(Environment env, Pxt_fac_hdrDTO pxtFacHdrDTO) {
        String credentials = env.getProperty("server.oracle.username") + ":" +
                              env.getProperty("server.oracle.password");
        String encodedAuth = Base64.getEncoder().encodeToString(credentials.getBytes());
        String authHeader = "Basic " + encodedAuth;

        WebClient webClient = WebClient.create(env.getProperty("server.oracle"));

        String requestBody = String.format("""
                {
                    "packageName": "%s",
                    "procedureName": "%s",
                    "inParams": {
                        "P_POLICY_NO": "%s",
                        "P_END_NO_IDX": %s,
                        "P_END_SR_NO": 0
                    }
                }
                """,
                env.getProperty("server.oracle.packageName"),
                env.getProperty("server.oracle.procedureName"),
                pxtFacHdrDTO.getFH_UW_NO(),
                pxtFacHdrDTO.getFH_POL_IDX());

        System.out.println(requestBody);

        return webClient.post()
                .uri("/common/invokeProcedure")
                .header("Authorization", authHeader)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    
}

	
public static Map<String, Map<String, Object>> organizeData(List<Map<String, Object>> dataList) {
    return dataList.stream()
        .collect(Collectors.groupingBy(
            item -> String.valueOf(item.get("frc_SEC_CODE")), // Safe conversion
            Collectors.collectingAndThen(Collectors.toList(), secGroup -> {
                Map<String, Object> secMap = new HashMap<>();

                List<Map<String, Object>> risks = secGroup.stream()
                    .collect(Collectors.groupingBy(item -> String.valueOf(item.get("frc_UR_RSK_ID"))))
                    .entrySet().stream()
                    .map(entry -> {
                        String riskId = entry.getKey();
                        List<Map<String, Object>> riskItems = entry.getValue();

                        Map<String, Object> riskData = new HashMap<>();
                        riskData.put("frc_FH_SYS_ID", String.valueOf(getOrDefault(riskItems.get(0), "frc_FH_SYS_ID")));
                        riskData.put("frc_UR_RSK_ID", riskId);
                        riskData.put("frc_RISK_TYP", String.valueOf(getOrDefault(riskItems.get(0), "frc_RISK_TYP")));
                        riskData.put("expanded", true);
                        riskData.put("currencies", List.of("USD", "INR"));

                        List<Map<String, Object>> covers = riskItems.stream().map(item -> {
                            Map<String, Object> cover = new HashMap<>();
                            cover.put("frc_SYS_ID", item.get("frc_SYS_ID"));
                            cover.put("frc_CVR_CODE", String.valueOf(getOrDefault(item, "frc_CVR_CODE")));
                            cover.put("cqs", "0%");
                            cover.put("frc_FAC_RATE", String.valueOf(getOrDefault(item, "frc_FAC_RATE")));
                            cover.put("tty", "0%");
                            cover.put("frc_SI", String.valueOf(getOrDefault(item, "frc_SI")));
                            cover.put("frc_PREM", String.valueOf(getOrDefault(item, "frc_PREM")));
                            cover.put("frc_FAC_SI", String.valueOf(getOrDefault(item, "frc_FAC_SI")));
                            cover.put("frc_FAC_PREM", String.valueOf(getOrDefault(item, "frc_FAC_PREM")));
                            cover.put("frc_PLACE_REF_NO", String.valueOf(getOrDefault(item, "frc_PLACE_REF_NO")));
                            return cover;
                        }).collect(Collectors.toList());

                        riskData.put("covers", covers);
                        return riskData;
                    })
                    .collect(Collectors.toList());

                secMap.put("risks", risks);
                return secMap;
            })
        ));
}


private static Object getOrDefault(Map<String, Object> map, String key) {
    return map.getOrDefault(key, "N/A");
}
	
	


}
