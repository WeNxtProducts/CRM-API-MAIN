package com.vi.base.commonUtils;

import java.util.List;
import java.util.Map;

public interface CommonDao {
    List<Map<String, Object>> getMapQuery(String query, Map<String, Object> queryParams);
    QUERY_MASTER getQueryLov(Integer queryId);
}
