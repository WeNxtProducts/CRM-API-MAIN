/*
 - Version Number 0.0.1
*/

package com.vi.extended.modules.quotes;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vi.model.dao.QuoteDAO;

@Repository
public interface QuoteRepositoryCustom extends JpaRepository<QuoteDAO,Long> {
    List<QuoteDAO> findByEnqSeqNo(Long enqSeqNo);
    
    List<QuoteDAO> findByEnqSeqNoAndUserSeqNo(Long enqSeqNo, Long userSeqNo);	
	
}
