/*
 - Version Number 0.0.1
*/

package com.vi.base.modules.activitylogs;

import com.vi.model.dao.ActivityLogDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityLogRepository extends JpaRepository<ActivityLogDAO,Long>, JpaSpecificationExecutor<ActivityLogDAO> {

}
