package com.vi.model.dto;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BrokerDTO {
    private String brokerCode;
    private String brokerName;
    private Float localYN;
}
