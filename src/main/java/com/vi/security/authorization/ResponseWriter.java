package com.vi.security.authorization;


import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ResponseWriter {

    public String getResponse(int statusCode, String content) {
        if(content==null) {
            content = "{}";
        }
        return  "{ \"statusCode\":"+statusCode+",\"data\":"+content+"}";
    }
    public String getResponse(int statusCode, String content,Long requestId) {
        if(content==null) {
            content = "{}";
        }
        return  "{ \"requestId\":"+requestId+",\"statusCode\":"+statusCode+",\"data\":"+content+"}";
    }
}
