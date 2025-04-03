package com.vi.model.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor 
public class QuoteDTO {

    private Long quoteSeqNo;
    private Long saleId;
    private Long enqSeqNo;
    private EnquiryDTO enquiry;
    private Long leadSeqNo;
    private Long userSeqNo;
    private String quoteDescription;
    private BigDecimal sumInsured;
    private String lobName;
    private String productName;
    private BigDecimal premium;
    private BigDecimal suggestedRate;
    private String quoteStatus = "Pending";
    private Long quoteCount;
    private  String isAccepted;
    private Date createdAt;
    private Date updatedAt;
    private Boolean deleted=false;
}
