package com.vi.base.emailTemplate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vi.model.dao.LjmEmailTemplateDAO;

@Repository
public interface EmailTemplateRepo extends JpaRepository<LjmEmailTemplateDAO, Long> {

	@Query("SELECT e from EMAIL_TEMPLATE e WHERE e.etSysId = :templateId")
	LjmEmailTemplateDAO getById(@Param("templateId") Long templateId);
	
	

}
