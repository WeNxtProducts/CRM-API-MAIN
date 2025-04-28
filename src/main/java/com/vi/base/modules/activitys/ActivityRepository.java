/*
 - Version Number 0.0.1
*/

package com.vi.base.modules.activitys;

import com.vi.model.dao.ActivityDAO;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityRepository extends JpaRepository<ActivityDAO,Long>, JpaSpecificationExecutor<ActivityDAO> {

	List<ActivityDAO> findByUserSeqNoAndActivityStartDateBetween(Long userSeqNo, Date startDate, Date endDate);

}
