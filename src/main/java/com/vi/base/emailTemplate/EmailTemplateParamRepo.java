package com.vi.base.emailTemplate;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vi.model.dao.LjmEmailParamDAO;

@Repository
public interface EmailTemplateParamRepo extends JpaRepository<LjmEmailParamDAO, Long> {
	
	@Query("SELECT e from EMAIL_PARAM e WHERE e.epSysId = :templateId")
	LjmEmailParamDAO getById(@Param("templateId") Long templateId);
	
	@Query("SELECT e from EMAIL_PARAM e WHERE e.epEtSysId.etSysId = :templateId")
	List<LjmEmailParamDAO> getParams(@Param("templateId") Long templateId);

}
