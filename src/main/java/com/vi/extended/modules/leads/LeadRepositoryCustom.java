/*
 - Version Number 0.0.1
*/

package com.vi.extended.modules.leads;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vi.model.dao.LeadDAO;

@Repository
public interface LeadRepositoryCustom extends JpaRepository<LeadDAO,Long> {
	


}
