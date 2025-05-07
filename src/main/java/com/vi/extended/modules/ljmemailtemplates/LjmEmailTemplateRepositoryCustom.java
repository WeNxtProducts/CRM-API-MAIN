/*
 - Version Number 0.0.1
*/

package com.vi.extended.modules.ljmemailtemplates;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vi.model.dao.LjmEmailTemplateDAO;

@Repository
public interface LjmEmailTemplateRepositoryCustom extends JpaRepository<LjmEmailTemplateDAO,Long> {
	
//	@Query("SELECT COUNT(*) AS records FROM LjmEmailTemplateDAO")
//	long countByLjmEmailTemplateSeqNo();

}
