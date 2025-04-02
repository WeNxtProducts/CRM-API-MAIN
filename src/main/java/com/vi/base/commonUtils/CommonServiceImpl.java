package com.vi.base.commonUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.vi.base.commonUtils.QUERY_MASTER;
import com.vi.base.commonUtils.QueryParametersDTO;

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
	public String getMapQuery(Integer queryId, QueryParametersDTO queryParams) throws JSONException {
		JSONObject response = new JSONObject();
		try {
			QUERY_MASTER query = commonDao.getQueryLov(queryId);
			if (query != null) {
				List<Map<String, Object>> result = commonDao.getMapQuery(query.getQM_QUERY(),
						queryParams.getQueryParameters());

				List<Map<String, Object>> finalResult = new ArrayList<>();
				if (!result.isEmpty()) {
					for (int i = 0; i < result.size(); i++) {
						Map<String, Object> resultMap = result.get(i);
						finalResult.add(resultMap);
					}
					response.put(statusCode, successCode);
					response.put(messageCode, "Data Fetched Successfully");

					if (finalResult.size() >= 1) {
						response.put(dataCode, finalResult);
					}
				} else {
					response.put(statusCode, successCode);
					response.put(messageCode, "No Datas Found");
					response.put(dataCode, new JSONObject());
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put(statusCode, errorCode);
			response.put(messageCode, e.getMessage());
		}
		return response.toString();
	}
}

