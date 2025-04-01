/*
 - Version Number 0.0.1
*/

package com.vi.extended.modules.tasks;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vi.model.dao.TaskDAO;

@Repository
public interface TaskRepositoryCustom extends JpaRepository<TaskDAO,Long> {
	
//	@Query("SELECT COUNT(*) AS records FROM TaskDAO")
//	long countByTaskSeqNo();

}
