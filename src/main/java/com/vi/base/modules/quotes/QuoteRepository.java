/*
 - Version Number 0.0.1
*/

package com.vi.base.modules.quotes;

import com.vi.model.dao.QuoteDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface QuoteRepository extends JpaRepository<QuoteDAO,Long>, JpaSpecificationExecutor<QuoteDAO> {

}
