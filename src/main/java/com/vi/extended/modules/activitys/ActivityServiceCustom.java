/*
 - Version Number 0.0.1
*/

package com.vi.extended.modules.activitys;

import org.springframework.beans.factory.annotation.Autowired;

import com.vi.corelib.base.BaseService;
import com.vi.model.dao.ActivityDAO;
import com.vi.model.dto.ActivityDTOCustom;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

public interface ActivityServiceCustom extends BaseService<ActivityDTOCustom> {
	
//	@Autowired
//	private ActivityRepositoryCustom activityRepositoryCustom;
//
//	@Transactional
//	public static ActivityDTOCustom updateRemainderCount(Long activitySeqNo) {
//		var activityDAO = activityRepositoryCustom.findById(activityDTOCustom.getActivitySeqNo())
//				 .orElseThrow(() -> new EntityNotFoundException("Record not found with id: " + activityDTOCustom.getActivitySeqNo()));
//		
//	    System.out.println("Before Increment: " + activity.getRemainderCount());
//	    activityDTOCustom.setRemainderCount(activityDTOCustom.getRemainderCount() + 1); // Increment count
//	    System.out.println("After Increment: " + activityDTOCustom.getRemainderCount());
//
//	    activityRepositoryCustom.save(activityDTOCustom);
//	    return new ActivityDTOCustom(activity);
//	}

}
