package com.vi.corelib.api;

import org.springframework.http.HttpStatus;

public class CustomRuntimeException  extends Exception  {

    public CustomRuntimeException(String message, HttpStatus statusCode, ResponseError errorObject) {

    }
}
