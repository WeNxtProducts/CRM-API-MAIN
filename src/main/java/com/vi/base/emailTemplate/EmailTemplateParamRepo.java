//package com.vi.base.emailTemplate;
//
//import java.util.List;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.stereotype.Repository;
//
//@Repository
//public interface EmailTemplateParamRepo extends JpaRepository<LJM_EMAIL_PARAM, Integer> {
//	
//	@Query("SELECT e from LJM_EMAIL_PARAM e WHERE e.EP_SYS_ID = :templateId")
//	LJM_EMAIL_PARAM getById(Integer templateId);
//	
//	@Query("SELECT e from LJM_EMAIL_PARAM e WHERE e.EP_ET_SYS_ID.ET_SYS_ID = :templateId")
//	List<LJM_EMAIL_PARAM> getParams(Integer templateId);
//
//}
