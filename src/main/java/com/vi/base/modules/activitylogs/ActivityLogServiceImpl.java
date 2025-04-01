/*
 - Version Number 0.0.1
*/

package com.vi.base.modules.activitylogs;

import com.fasterxml.jackson.databind.JsonNode;
import com.vi.model.dto.ActivityLogDTO;
import java.util.List;

public class ActivityLogServiceImpl implements ActivityLogService {

	private ActivityLogPersistent activityLogPersistent;

	public ActivityLogServiceImpl(ActivityLogPersistent activityLogPersistent) {
		this.activityLogPersistent = activityLogPersistent;
	}

	@Override
	public List<ActivityLogDTO> fetchAll() {
		return activityLogPersistent.fetchAll();
	}

	@Override
	public ActivityLogDTO get(Long id) {
		return activityLogPersistent.get(id);
	}

	@Override
	public ActivityLogDTO create(ActivityLogDTO activityLogDTO) {
		return activityLogPersistent.create(activityLogDTO);
	}

	@Override
	public ActivityLogDTO update(ActivityLogDTO activityLogDTO) {
		return activityLogPersistent.update(activityLogDTO);
	}

	@Override
	public Boolean delete(Long id) {
		return activityLogPersistent.delete(id);
	}

	@Override
	public List<ActivityLogDTO> filterData(String search) {
		return activityLogPersistent.filterData(search);
	}

	@Override
	public List<ActivityLogDTO> filterData(JsonNode search, int page, int size) {
		return activityLogPersistent.filterData(search, page, size);
	}
}