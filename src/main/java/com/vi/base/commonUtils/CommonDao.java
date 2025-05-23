package com.vi.base.commonUtils;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public interface CommonDao {
	
    List<Map<String, Object>> getMapQuery(String query, Map<String, Object> queryParams);
    
    QUERY_MASTER getQueryLov(Integer queryId);
    
	List<QueryParamMasterDTO> getQueryParams(int sysId);

	SqlRowSet executeQuery(String query, Map<String, Object> emailTemplateQueryParams);
	
	List<LOVDTO> executeLOVQuery(String query, Map<String, Object> paramList);


}
