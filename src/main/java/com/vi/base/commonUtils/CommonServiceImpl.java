package com.vi.base.commonUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class CommonServiceImpl implements CommonService {
    
    @Autowired
    private CommonDao commonDao;
    
    @Value("${spring.success.code}")
    private String successCode;

    @Value("${spring.error.code}")
    private String errorCode;

    @Value("${spring.message.code}")
    private String messageCode;

    @Value("${spring.status.code}")
    private String statusCode;

    @Value("${spring.data.code}")
    private String dataCode;

    @Override
    public String getMapQuery(Integer queryId, QueryParametersDTO queryParams) {
        JSONObject response = new JSONObject();
        try {
            QUERY_MASTER query = commonDao.getQueryLov(queryId);
            if (query != null) {
                List<Map<String, Object>> result = commonDao.getMapQuery(
                    query.getQM_QUERY(),
                    queryParams != null && queryParams.getQueryParameters() != null 
                        ? queryParams.getQueryParameters() 
                        : Map.of()
                );

                List<Map<String, Object>> finalResult = new ArrayList<>();
                if (!result.isEmpty()) {
                    for (Map<String, Object> resultMap : result) {
                        finalResult.add(resultMap);
                    }
                    response.put(statusCode, successCode);
                    response.put(messageCode, "Data Fetched Successfully");

                    if (!finalResult.isEmpty()) {
                        response.put(dataCode, finalResult);
                    }
                } else {
                    response.put(statusCode, successCode);
                    response.put(messageCode, "No Data Found");
                    response.put(dataCode, new JSONObject());
                }
            } else {
                response.put(statusCode, errorCode);
                response.put(messageCode, "Query not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.put(statusCode, errorCode);
            response.put(messageCode, e.getMessage());
        }
        return response.toString();
    }
}

