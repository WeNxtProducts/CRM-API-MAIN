//package com.vi.base.emailTemplate;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.stereotype.Repository;
//
//import com.vi.model.dao.LjmEmailTemplateDAO;
//
//@Repository
//public interface EmailTemplateRepo extends JpaRepository<LjmEmailTemplateDAO, Integer> {
//
//	@Query("SELECT e from LJM_EMAIL_TEMPLATE e WHERE e.ET_SYS_ID = :templateId")
//	LjmEmailTemplateDAO getById(Integer templateId);
//
//}
