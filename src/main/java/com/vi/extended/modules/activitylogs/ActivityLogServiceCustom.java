/*
 - Version Number 0.0.1
*/

package com.vi.extended.modules.activitylogs;

import org.springframework.beans.factory.annotation.Autowired;

import com.vi.corelib.base.BaseService;
import com.vi.model.dao.ActivityLogDAO;
import com.vi.model.dto.ActivityLogDTOCustom;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

public interface ActivityLogServiceCustom extends BaseService<ActivityLogDTOCustom> {
	
//	@Autowired
//	private ActivityLogRepositoryCustom activityLogRepositoryCustom;
//
//	@Transactional
//	public static ActivityLogDTOCustom updateRemainderCount(Long activityLogSeqNo) {
//		var activityLogDAO = activityLogRepositoryCustom.findById(activityLogDTOCustom.getActivityLogSeqNo())
//				 .orElseThrow(() -> new EntityNotFoundException("Record not found with id: " + activityLogDTOCustom.getActivityLogSeqNo()));
//		
//	    System.out.println("Before Increment: " + activityLog.getRemainderCount());
//	    activityLogDTOCustom.setRemainderCount(activityLogDTOCustom.getRemainderCount() + 1); // Increment count
//	    System.out.println("After Increment: " + activityLogDTOCustom.getRemainderCount());
//
//	    activityLogRepositoryCustom.save(activityLogDTOCustom);
//	    return new ActivityLogDTOCustom(activityLog);
//	}

}
