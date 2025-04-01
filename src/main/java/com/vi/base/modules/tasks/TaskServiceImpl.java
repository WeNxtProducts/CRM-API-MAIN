/*
 - Version Number 0.0.1
*/

package com.vi.base.modules.tasks;

import com.fasterxml.jackson.databind.JsonNode;
import com.vi.model.dto.TaskDTO;
import java.util.List;

public class TaskServiceImpl implements TaskService {

	private TaskPersistent leadPersistent;

	public TaskServiceImpl(TaskPersistent leadPersistent) {
		this.leadPersistent = leadPersistent;
	}

	@Override
	public List<TaskDTO> fetchAll() {
		return leadPersistent.fetchAll();
	}

	@Override
	public TaskDTO get(Long id) {
		return leadPersistent.get(id);
	}

	@Override
	public TaskDTO create(TaskDTO leadDTO) {
		return leadPersistent.create(leadDTO);
	}

	@Override
	public TaskDTO update(TaskDTO leadDTO) {
		return leadPersistent.update(leadDTO);
	}

	@Override
	public Boolean delete(Long id) {
		return leadPersistent.delete(id);
	}

	@Override
	public List<TaskDTO> filterData(String search) {
		return leadPersistent.filterData(search);
	}

	@Override
	public List<TaskDTO> filterData(JsonNode search,int page,int size) {
		return leadPersistent.filterData(search, page, size);
	}
}