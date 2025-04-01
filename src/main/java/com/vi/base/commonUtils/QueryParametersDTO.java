package com.vi.base.commonUtils;

import java.util.Map;
import com.fasterxml.jackson.annotation.JsonProperty;

public class QueryParametersDTO {
    @JsonProperty("queryParams")
    private Map<String, Object> queryParameters;

    public Map<String, Object> getQueryParameters() {
        return queryParameters;
    }

    public void setQueryParameters(Map<String, Object> queryParameters) {
        this.queryParameters = queryParameters;
    }
}