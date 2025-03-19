package com.vi.corelib.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vi.corelib.api.MicroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

@Component
public class TaxCalculation {
    @Autowired
    public Environment env;

    public List getTariff(String hsnCode,String tax_category,Number taxable_amount,String customer_type,Long tax_template_id,String state_id,String country) throws JsonProcessingException {
        var objectMapper = new ObjectMapper();
        var result = objectMapper.readTree(String.valueOf(getHsnRate(hsnCode)));
        var tariff = new LinkedList<Object>();
        for (JsonNode i:result){
            var obj = new HashMap<String,Object>();
            getTariffList(taxable_amount, tariff, obj, i, objectMapper.readTree(String.valueOf(getTaxTemplate(i.get("taxComponentId").asLong()))));
        }
     return tariff;
    }

    private void getTariffList(Number taxable_amount, LinkedList<Object> tariff, HashMap<String, Object> obj, JsonNode i, JsonNode taxTemplate) {
        obj.put("priceComponentId", i.get("taxComponentId").asLong());
        obj.put("name", taxTemplate.get("priceClass").asText());
        obj.put("tariff", i.get("tariff").asDouble());
        var amount = (i.get("tariff").asDouble() * taxable_amount.longValue()/ 100);
        obj.put("amount",amount);
        tariff.push(obj);
    }


    private Object getHsnRate(String hsnCode){
        MicroService service = new MicroService();
        String baseUrl = env.getProperty("server.admin");
        return  service.getData(baseUrl, "hsncomponentrate/hsnCode?hsnCode="+hsnCode, null);
    }

    private Object getTaxTemplate(Long id){
        MicroService service = new MicroService();
        String baseUrl = env.getProperty("server.admin");
        return  service.getData(baseUrl, "pricecomponent/"+id, null);
    }
}
