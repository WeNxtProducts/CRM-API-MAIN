package com.vi.model.dao;

import java.time.Instant;
import java.util.Date;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Entity(name = "WN_CONVERSATION")
@Table(name = "WN_CONVERSATION")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ConversationDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CONV_SEQ_NO")
    private Long conversationSeqNo;
    
    @Column(name = "USER_SEQ_NO")
   	private Long userSeqNo;

    @Column(name = "ENQ_SEQ_NO")
    private Long enqSeqNo;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ENQ_SEQ_NO", referencedColumnName = "ENQ_SEQ_NO", insertable = false, updatable = false)
    private EnquiryDAO enquiry;

    @Lob
    @Column(name = "CONV_LEAD_COMMENT")
    private String convLeadComment;

    @Lob
    @Column(name = "CONV_SALES_COMMENT")
    private String convSalesComment;
    
    @Lob
    @Column(name = "CONV_QUOTE_COMMENT")
    private String convQuoteComment;

    @Column(name = "CONV_CREATED_AT")
    private Date convCreatedAt = Date.from(Instant.now());

    @Column(name = "CONV_UPDATED_AT")
    private Date convUpdatedAt = Date.from(Instant.now());
    
    @Column(name = "DELETED")
    private Boolean deleted=false;
    
    @Column(name = "DELETED_AT")
    private Date deletedAt;
    
    @Column(name = "DELETED_BY")
    private String deletedBy;

}