/*
 - Version Number 0.0.1
*/

package com.vi.extended.modules.tasks;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vi.model.dto.TaskDTOCustom;

public class TaskServiceImplCustom implements TaskServiceCustom {

	private TaskPersistentCustom taskpersistentCustom;

	public TaskServiceImplCustom(TaskPersistentCustom taskpersistentCustom) {
		this.taskpersistentCustom = taskpersistentCustom;
	}

	@Override
	public List<TaskDTOCustom> fetchAll() {
		return taskpersistentCustom.fetchAll();
	}

	@Override
	public TaskDTOCustom get(Long id) {
		return taskpersistentCustom.get(id);
	}

	@Override
	public TaskDTOCustom create(TaskDTOCustom taskDTOCustom) {
		return taskpersistentCustom.create(taskDTOCustom);
	}

	@Override
	public TaskDTOCustom update(TaskDTOCustom taskDTOCustom) {
		return taskpersistentCustom.update(taskDTOCustom);
	}

	@Override
	public Boolean delete(Long id) {
		return taskpersistentCustom.delete(id);
	}
}