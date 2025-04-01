/*
 - Version Number 0.0.1
*/

package com.vi.extended.modules.activitylogs;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vi.model.dto.ActivityLogDTOCustom;

public class ActivityLogServiceImplCustom implements ActivityLogServiceCustom {

	private ActivityLogPersistentCustom activityLogpersistentCustom;

	public ActivityLogServiceImplCustom(ActivityLogPersistentCustom activityLogpersistentCustom) {
		this.activityLogpersistentCustom = activityLogpersistentCustom;
	}

	@Override
	public List<ActivityLogDTOCustom> fetchAll() {
		return activityLogpersistentCustom.fetchAll();
	}

	@Override
	public ActivityLogDTOCustom get(Long id) {
		return activityLogpersistentCustom.get(id);
	}

	@Override
	public ActivityLogDTOCustom create(ActivityLogDTOCustom activityLogDTOCustom) {
		return activityLogpersistentCustom.create(activityLogDTOCustom);
	}

	@Override
	public ActivityLogDTOCustom update(ActivityLogDTOCustom activityLogDTOCustom) {
		return activityLogpersistentCustom.update(activityLogDTOCustom);
	}

	@Override
	public Boolean delete(Long id) {
		return activityLogpersistentCustom.delete(id);
	}
}