package com.vi.base.commonUtils;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/common")
public class CommonController {
	
    @Autowired
    private CommonService service;
    
    @PostMapping("/getMapQuery")
    public String getMapQuery(@RequestParam Integer queryId, @Nullable @RequestBody QueryParametersDTO queryParams) throws JSONException {
        return service.getMapQuery(queryId, queryParams);
    }
}
