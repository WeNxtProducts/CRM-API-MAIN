package com.vi.model.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "WN_QUOTE")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class QuoteDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "QUOTE_SEQ_NO")
    private Long quoteSeqNo;
    
    @Column(name = "SALES_ID")
    private Long saleId;

    @Column(name = "ENQ_SEQ_NO", nullable = false)
    private Long enqSeqNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name="ENQ_SEQ_NO", referencedColumnName = "ENQ_SEQ_NO", insertable=false, updatable=false)
    private EnquiryDAO enquiry;

    @Column(name = "LEAD_SEQ_NO")
    private Long leadSeqNo;
    
    @Column(name = "USER_SEQ_NO")
    private Long userSeqNo;

    @Column(name = "QUOTE_DESCRIPTION")
    private String quoteDescription;

    @Column(name = "SUM_INSURED", precision = 15, scale = 2)
    private BigDecimal sumInsured;

    @Column(name = "LOB_NAME")
    private String lobName;

    @Column(name = "PRODUCT_NAME")
    private String productName;

    @Column(name = "PREMIUM", precision = 15, scale = 2)
    private BigDecimal premium;

    @Column(name = "SUGGESTED_RATE")
    private BigDecimal suggestedRate;

    @Column(name = "QUOTE_STATUS")
    private String quoteStatus;
    
    @Column(name = "IS_ACCEPTED")
    private  String isAccepted;
    
    @Column(name = "QUOTE_COUNT")
    private Long quoteCount;

    @Column(name = "CREATED_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "UPDATED_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @Column(name = "DELETED")
    private Boolean deleted=false;

	
		
}


