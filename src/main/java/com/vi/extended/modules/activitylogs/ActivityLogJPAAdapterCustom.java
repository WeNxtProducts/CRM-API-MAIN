/*
 - Version Number 0.0.1
*/

package com.vi.extended.modules.activitylogs;

import com.vi.model.dao.ActivityLogDAO;
import com.vi.model.dto.ActivityLogDTO;
import com.vi.model.dto.ActivityLogDTOCustom;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

public class ActivityLogJPAAdapterCustom implements ActivityLogPersistentCustom {
	@Autowired
	ActivityLogRepositoryCustom activityLogRepositoryCustom;

	@Override
	public List<ActivityLogDTOCustom> fetchAll() {
		var activityLogDAOList = activityLogRepositoryCustom.findAll();
		return ActivityLogMapperCustom.INSTANCE.activityLogDAOListToActivityLogDTOListCustom(activityLogDAOList);
	}

	@Override
	public ActivityLogDTOCustom get(Long id) {
		var activityLogDAO = activityLogRepositoryCustom.findById(id);
		if (activityLogDAO.isPresent()) {
			return ActivityLogMapperCustom.INSTANCE.activityLogDAOToActivityLogDTOCustom(activityLogDAO.get());
		}
		return null;
	}

	@Override
	public ActivityLogDTOCustom create(ActivityLogDTOCustom activityLogDTOCustom) {
		var activityLogDAO = ActivityLogMapperCustom.INSTANCE.activityLogDTOCustomToActivityLogDAO(activityLogDTOCustom);
		ActivityLogDAO newActivityLog = activityLogRepositoryCustom.save(activityLogDAO);
		return ActivityLogMapperCustom.INSTANCE.activityLogDAOToActivityLogDTOCustom(newActivityLog);
	}

	//updated
	@Override
	public ActivityLogDTOCustom update(ActivityLogDTOCustom activityLogDTOCustom) {
		var activityLogDAO = activityLogRepositoryCustom.findById(activityLogDTOCustom.getActivityLogSeqNo())
				 .orElseThrow(() -> new EntityNotFoundException("Record not found with id: " + activityLogDTOCustom.getActivityLogSeqNo()));
		var oldData = ActivityLogMapperCustom.INSTANCE.activityLogDAOToActivityLogDTOCustom(activityLogDAO);
		ActivityLogMapperCustom.INSTANCE.assignValuesCustom(activityLogDTOCustom, activityLogDAO);
		ActivityLogDAO newActivityLog = activityLogRepositoryCustom.save(activityLogDAO);
		return ActivityLogMapperCustom.INSTANCE.activityLogDAOToActivityLogDTOCustom(newActivityLog);
	}
	

	@Override
	public Boolean delete(Long id) {
		var activityLogDAO = activityLogRepositoryCustom.getById(id);
	//	activityLogDAO.setDeleted(true);
		activityLogRepositoryCustom.save(activityLogDAO);
		return true;
	}
}
