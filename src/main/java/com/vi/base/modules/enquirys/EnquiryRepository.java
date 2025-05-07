/*
 - Version Number 0.0.1
*/

package com.vi.base.modules.enquirys;

import com.vi.model.dao.EnquiryDAO;
import com.vi.model.dao.LjmEmailTemplateDAO;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface EnquiryRepository extends JpaRepository<EnquiryDAO,Long>, JpaSpecificationExecutor<EnquiryDAO> {

	Optional<EnquiryDAO> findByLeadSeqNo(Long leadSeqNo);

}
