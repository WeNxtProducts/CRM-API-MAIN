/*
 - Version Number 0.0.1
*/

package com.vi.extended.modules.activitys;

import com.vi.base.modules.activitys.ActivityMapper;
import com.vi.model.dao.ActivityDAO;
import com.vi.model.dto.ActivityDTO;
import com.vi.model.dto.ActivityDTOCustom;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

public class ActivityJPAAdapterCustom implements ActivityPersistentCustom {
	@Autowired
	ActivityRepositoryCustom activityRepositoryCustom;

	@Override
	public List<ActivityDTOCustom> fetchAll() {
		var activityDAOList = activityRepositoryCustom.findAll();
		return ActivityMapperCustom.INSTANCE.activityDAOListToActivityDTOListCustom(activityDAOList);
	}

	@Override
	public ActivityDTOCustom get(Long id) {
		var activityDAO = activityRepositoryCustom.findById(id);
		if (activityDAO.isPresent()) {
			return ActivityMapperCustom.INSTANCE.activityDAOToActivityDTOCustom(activityDAO.get());
		}
		return null;
	}

	@Override
	public ActivityDTOCustom create(ActivityDTOCustom activityDTOCustom) {
		var activityDAO = ActivityMapperCustom.INSTANCE.activityDTOCustomToActivityDAO(activityDTOCustom);
		ActivityDAO newActivity = activityRepositoryCustom.save(activityDAO);
		return ActivityMapperCustom.INSTANCE.activityDAOToActivityDTOCustom(newActivity);
	}

	//updated
	@Override
	public ActivityDTOCustom update(ActivityDTOCustom activityDTOCustom) {
		var activityDAO = activityRepositoryCustom.findById(activityDTOCustom.getActivitySeqNo())
				 .orElseThrow(() -> new EntityNotFoundException("Record not found with id: " + activityDTOCustom.getActivitySeqNo()));
		var oldData = ActivityMapperCustom.INSTANCE.activityDAOToActivityDTOCustom(activityDAO);
		ActivityMapperCustom.INSTANCE.assignValuesCustom(activityDTOCustom, activityDAO);
		ActivityDAO newActivity = activityRepositoryCustom.save(activityDAO);
		return ActivityMapperCustom.INSTANCE.activityDAOToActivityDTOCustom(newActivity);
	}
	

	@Override
	public Boolean delete(Long id) {
		var activityDAO = activityRepositoryCustom.getById(id);
	//	activityDAO.setDeleted(true);
		activityRepositoryCustom.save(activityDAO);
		return true;
	}
}
