package com.vi.corelib.api;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class RequestToken {
  private Boolean base64Encoded;
  private String apiKey;
  private String authToken;
  private String sharedSecret;
  private String authKey;
}
