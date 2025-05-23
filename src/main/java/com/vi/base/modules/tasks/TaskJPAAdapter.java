/*
 - Version Number 0.0.1
*/

package com.vi.base.modules.tasks;

import com.fasterxml.jackson.databind.JsonNode;
import com.vi.model.dao.TaskDAO;
import com.vi.model.dto.TaskDTO;
import com.vi.model.dto.TaskDTO;

import jakarta.persistence.EntityNotFoundException;

import com.vi.base.modules.tasks.TaskMapper;
import com.vi.base.modules.tasks.TaskMapper;
//import com.vi.corelib.events.EventPublisher;
import com.vi.corelib.filter.FilterSpecificationsBuilder;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import java.util.List;
import java.io.IOException;
import org.json.simple.parser.ParseException;


public class TaskJPAAdapter implements TaskPersistent {
	@Autowired
	TaskRepository taskRepository;

	@Override
	public List<TaskDTO> fetchAll() {
		var taskDAOList = taskRepository.findAll();
		return TaskMapper.INSTANCE.taskDAOListToTaskDTOList(taskDAOList);
	}

	@Override
	public TaskDTO get(Long id) {
		var taskDAO = taskRepository.findById(id);
		if(taskDAO.isPresent()) {
			return TaskMapper.INSTANCE.taskDAOToTaskDTO(taskDAO.get());
		}
		return null;
	}

  @SneakyThrows
	@Override
	public TaskDTO create(TaskDTO taskDTO) {
		var taskDAO = TaskMapper.INSTANCE.taskDTOToTaskDAO(taskDTO);
		var newTask = taskRepository.save(taskDAO);
		var newData = TaskMapper.INSTANCE.taskDAOToTaskDTO(newTask);
		
		
		return newData;
	}

	@Override
 		public TaskDTO update(TaskDTO taskDTO) {
			
		 var taskDAO = taskRepository.findById(taskDTO.getTaskSeqNo())
        .orElseThrow(() -> new EntityNotFoundException("Record not found with id: " + taskDTO.getTaskSeqNo()));
		var oldData = TaskMapper.INSTANCE.taskDAOToTaskDTO(taskDAO);
		TaskMapper.INSTANCE.assignValues(taskDTO, taskDAO);
		var newTask = taskRepository.save(taskDAO);
		var newData = TaskMapper.INSTANCE.taskDAOToTaskDTO(newTask); 
		
		return newData;   
	}

	@SneakyThrows
	@Override
	public Boolean delete(Long id) {
	    var taskDAO = taskRepository.findById(id)
	                    .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

	    taskDAO.setDeleted(true); // Set deleted flag to true
	    taskRepository.save(taskDAO); // Persist the change

	    return true; 
	}
	
	@Override
	public List<TaskDTO> filterData(String search) {
		Specification<TaskDAO> result = new FilterSpecificationsBuilder<TaskDAO>().with(search).build();
		return TaskMapper.INSTANCE.taskDAOListToTaskDTOList(taskRepository.findAll(result));
	}

	@Override
	public List<TaskDTO> filterData(JsonNode search, int page, int size) {
		Pageable pageable = PageRequest.of(page, size, Sort.by("taskSeqNo").ascending());

    Specification<TaskDAO> result = new FilterSpecificationsBuilder<TaskDAO>().with(search).build();
		return TaskMapper.INSTANCE.taskDAOListToTaskDTOList(taskRepository.findAll(result, pageable).getContent());
	}
	
	@Override
	public List<TaskDTO> filterData(JsonNode search) {
    Specification<TaskDAO> result = new FilterSpecificationsBuilder<TaskDAO>().with(search).build();
		return TaskMapper.INSTANCE.taskDAOListToTaskDTOList(taskRepository.findAll(result));
	}
}
