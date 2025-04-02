package com.vi.base.commonUtils;

import org.json.JSONException;

public interface CommonService {
    String getMapQuery(Integer queryId, QueryParametersDTO queryParams) throws JSONException;
}

