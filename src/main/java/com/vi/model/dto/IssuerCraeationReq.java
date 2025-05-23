/*
 Version Number 0.0.1
*/

package com.vi.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor 
@JsonInclude(JsonInclude.Include.NON_NULL)
public class IssuerCraeationReq {

    @JsonProperty("LoginInformation")
    private IssuerLoginReq loginInformation;

    @JsonProperty("PersonalInformation")
    private IssuerPersonalInfoReq personalInformation;
}