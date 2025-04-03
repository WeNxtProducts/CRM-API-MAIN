/*
 - Version Number 0.0.1
*/

package com.vi.base.modules.quotes;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.vi.model.dao.QuoteDAO;

@Repository
public interface QuoteRepository extends JpaRepository<QuoteDAO, Long>, JpaSpecificationExecutor<QuoteDAO> {
	List<QuoteDAO> findByEnqSeqNo(Long seqno);
}
