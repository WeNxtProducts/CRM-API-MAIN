/*
 Version Number 0.0.1
*/

package com.vi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class LoginInformationDTO {

    private String agencyCode;
    private String bankCode;
    private String brokerCompanyYn;
    private String createdby;
    private String effectiveDateStart;
    private String insuranceId;
    private String loginId;
    private String oaCode;
    private String password;
    private String status;
    private String subUserType;
    private String userType;
    private String cbcNo;
}
