/*
 - Version Number 0.0.1
*/

package com.vi.extended.modules.tasks;

import com.vi.base.modules.tasks.TaskMapper;
import com.vi.model.dao.TaskDAO;
import com.vi.model.dto.TaskDTO;
import com.vi.model.dto.TaskDTOCustom;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

public class TaskJPAAdapterCustom implements TaskPersistentCustom {
	@Autowired
	TaskRepositoryCustom taskRepositoryCustom;

	@Override
	public List<TaskDTOCustom> fetchAll() {
		var taskDAOList = taskRepositoryCustom.findAll();
		return TaskMapperCustom.INSTANCE.taskDAOListToTaskDTOListCustom(taskDAOList);
	}

	@Override
	public TaskDTOCustom get(Long id) {
		var taskDAO = taskRepositoryCustom.findById(id);
		if (taskDAO.isPresent()) {
			return TaskMapperCustom.INSTANCE.taskDAOToTaskDTOCustom(taskDAO.get());
		}
		return null;
	}

	@Override
	public TaskDTOCustom create(TaskDTOCustom taskDTOCustom) {
		var taskDAO = TaskMapperCustom.INSTANCE.taskDTOCustomToTaskDAO(taskDTOCustom);
		TaskDAO newTask = taskRepositoryCustom.save(taskDAO);
		return TaskMapperCustom.INSTANCE.taskDAOToTaskDTOCustom(newTask);
	}

	//updated
	@Override
	public TaskDTOCustom update(TaskDTOCustom taskDTOCustom) {
		var taskDAO = taskRepositoryCustom.findById(taskDTOCustom.getTaskSeqNo())
				 .orElseThrow(() -> new EntityNotFoundException("Record not found with id: " + taskDTOCustom.getTaskSeqNo()));
		var oldData = TaskMapperCustom.INSTANCE.taskDAOToTaskDTOCustom(taskDAO);
		TaskMapperCustom.INSTANCE.assignValuesCustom(taskDTOCustom, taskDAO);
		TaskDAO newTask = taskRepositoryCustom.save(taskDAO);
		return TaskMapperCustom.INSTANCE.taskDAOToTaskDTOCustom(newTask);
	}
	

	@Override
	public Boolean delete(Long id) {
		var taskDAO = taskRepositoryCustom.getById(id);
	//	taskDAO.setDeleted(true);
		taskRepositoryCustom.save(taskDAO);
		return true;
	}
}
