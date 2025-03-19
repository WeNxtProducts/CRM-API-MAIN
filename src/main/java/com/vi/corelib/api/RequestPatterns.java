package com.vi.corelib.api;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Getter
@Setter
@Data
public class RequestPatterns {
  private String name;
  private String type;
  private String description;
  private String endPoint;
  private String method;
  private String tokentype; // NA, authtoken, base64
  private RequestToken requestToken;
  private String authToken;
  private List<RequestBody> requestParam;
  private List<RequestBody> requestHeader;
  private List<RequestBody> requestBody;
  private List<RequestBody> responseBody;
  private Response responseObject;
  private String response; // TODO Need proper hanlding
}

