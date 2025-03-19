package com.vi.corelib.exceptions;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@JsonInclude(content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseError {
    private String errorCode;
    private String message;
    private String errorMessage;
}
