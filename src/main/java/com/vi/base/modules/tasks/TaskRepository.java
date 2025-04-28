/*
 - Version Number 0.0.1
*/

package com.vi.base.modules.tasks;

import com.vi.model.dao.TaskDAO;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<TaskDAO,Long>, JpaSpecificationExecutor<TaskDAO> {

	List<TaskDAO> findByUserSeqNoAndTaskDueDateBetween(Long userSeqNo, Date startDate, Date endDate);

}
