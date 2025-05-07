package com.vi.model.dto;

import java.math.BigDecimal;
import java.time.Instant;
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
public class QuoteDTOCustom {
   
    private Long quoteSeqNo;
    private String quoteRefID;
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
    private  String isAccepted = "Todo";
    private  String isDone="Todo";
    private Long currUnderwriter;
    private Date quoteCreatedDate=Date.from(Instant.now());
    private String quoteCreatedBy;
    private Date quoteUpdatedDate; 
    private String quoteUpdatedBy;
    private Boolean deleted=false;
    private Date deletedAt;
    private String deletedBy;

}
