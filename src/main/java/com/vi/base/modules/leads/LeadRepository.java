/*
 - Version Number 0.0.1
*/

package com.vi.base.modules.leads;

import com.vi.model.dao.LeadDAO;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface LeadRepository extends JpaRepository<LeadDAO,Long>, JpaSpecificationExecutor<LeadDAO> {

     List<LeadDAO> findAllByUserSeqNo(Long userSeqNo);

	 Optional<LeadDAO> findByUserSeqNo(Long userSeqNo);
}
