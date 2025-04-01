/*
 - Version Number 0.0.1
*/

package com.vi.extended.modules.activitylogs;

import com.vi.model.dao.ActivityLogDAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityLogRepositoryCustom extends JpaRepository<ActivityLogDAO,Long> {

}
