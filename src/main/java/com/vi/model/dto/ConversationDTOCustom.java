/*
 Version Number 0.0.1
*/

package com.vi.model.dto;

import com.ibm.icu.math.BigDecimal;
import com.vi.model.BaseDto;
import com.vi.model.dao.EnquiryDAO;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Date;

import org.hibernate.annotations.NotFound;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor 
public class ConversationDTOCustom  {
 
    private Long conversationSeqNo;
    private Long userSeqNo;
    private Long enqSeqNo; 
    private EnquiryDTO enquiry;
    private String convLeadComment;
    private String convSalesComment;
    private String convQuoteComment;
    private Date convCreatedAt = Date.from(Instant.now());
    private Date convUpdatedAt = Date.from(Instant.now());
    private Boolean deleted=false;
    private Date deletedAt;
    private String deletedBy;


}