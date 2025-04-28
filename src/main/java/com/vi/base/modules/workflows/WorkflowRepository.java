/*
 - Version Number 0.0.1
*/

package com.vi.base.modules.workflows;

import com.vi.model.dao.WorkflowDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkflowRepository extends JpaRepository<WorkflowDAO,Long>, JpaSpecificationExecutor<WorkflowDAO> {

}
