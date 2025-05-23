/*
 Version Number 0.0.1
*/

package com.vi.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor 
public class BrokerCreationReq {
    
    @JsonProperty("LoginInformation")
    private BrokerLoginInfoReq loginInformation;

    @JsonProperty("PersonalInformation")
    private BrokerPersonalInfoReq personalInformation;
}