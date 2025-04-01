/*
 - Version Number 0.0.1
*/

package com.vi.extended.modules.activitys;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vi.model.dto.ActivityDTOCustom;

public class ActivityServiceImplCustom implements ActivityServiceCustom {

	private ActivityPersistentCustom activitypersistentCustom;

	public ActivityServiceImplCustom(ActivityPersistentCustom activitypersistentCustom) {
		this.activitypersistentCustom = activitypersistentCustom;
	}

	@Override
	public List<ActivityDTOCustom> fetchAll() {
		return activitypersistentCustom.fetchAll();
	}

	@Override
	public ActivityDTOCustom get(Long id) {
		return activitypersistentCustom.get(id);
	}

	@Override
	public ActivityDTOCustom create(ActivityDTOCustom activityDTOCustom) {
		return activitypersistentCustom.create(activityDTOCustom);
	}

	@Override
	public ActivityDTOCustom update(ActivityDTOCustom activityDTOCustom) {
		return activitypersistentCustom.update(activityDTOCustom);
	}

	@Override
	public Boolean delete(Long id) {
		return activitypersistentCustom.delete(id);
	}
}