/*
 - Version Number 0.0.1
*/

package com.vi.base.modules.tasks;

import com.vi.model.dao.TaskDAO;
import com.vi.model.dto.TaskDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {

	TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

	TaskDTO taskDAOToTaskDTO(TaskDAO taskDAO);

	TaskDAO taskDTOToTaskDAO(TaskDTO taskDTO);

	List<TaskDTO> taskDAOListToTaskDTOList(List<TaskDAO> taskDAOList);

	List<TaskDAO> taskDTOListToTaskDAOList(List<TaskDTO> taskDTOList);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	void assignValues(TaskDTO dto, @MappingTarget TaskDAO entity);
}