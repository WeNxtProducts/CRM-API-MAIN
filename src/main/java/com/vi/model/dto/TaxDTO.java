package com.vi.model.dto;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaxDTO {
    private String taxType;
    private String typeDescription;
    private String taxCode;
}
