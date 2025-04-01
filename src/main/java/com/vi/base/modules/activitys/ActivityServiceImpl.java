/*
 - Version Number 0.0.1
*/

package com.vi.base.modules.activitys;

import com.fasterxml.jackson.databind.JsonNode;
import com.vi.model.dto.ActivityDTO;
import java.util.List;

public class ActivityServiceImpl implements ActivityService {

	private ActivityPersistent activityPersistent;

	public ActivityServiceImpl(ActivityPersistent activityPersistent) {
		this.activityPersistent = activityPersistent;
	}

	@Override
	public List<ActivityDTO> fetchAll() {
		return activityPersistent.fetchAll();
	}

	@Override
	public ActivityDTO get(Long id) {
		return activityPersistent.get(id);
	}

	@Override
	public ActivityDTO create(ActivityDTO activityDTO) {
		return activityPersistent.create(activityDTO);
	}

	@Override
	public ActivityDTO update(ActivityDTO activityDTO) {
		return activityPersistent.update(activityDTO);
	}

	@Override
	public Boolean delete(Long id) {
		return activityPersistent.delete(id);
	}

	@Override
	public List<ActivityDTO> filterData(String search) {
		return activityPersistent.filterData(search);
	}

	@Override
	public List<ActivityDTO> filterData(JsonNode search, int page, int size) {
		return activityPersistent.filterData(search, page, size);
	}
}