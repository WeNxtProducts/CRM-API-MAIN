/*
 - Version Number 0.0.1
*/

package com.vi.extended.modules.tasks;

import com.vi.model.dao.TaskDAO;
import com.vi.model.dto.TaskDTOCustom;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapperCustom {

	TaskMapperCustom INSTANCE = Mappers.getMapper(TaskMapperCustom.class);

	TaskDTOCustom taskDAOToTaskDTOCustom(TaskDAO taskDAO);

	TaskDAO taskDTOCustomToTaskDAO(TaskDTOCustom taskDTOCustom);

	List<TaskDTOCustom> taskDAOListToTaskDTOListCustom(List<TaskDAO> taskDAOList);

	List<TaskDAO> taskDTOListCustomToTaskDAOList(List<TaskDTOCustom> taskDTOCustomList);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	void assignValuesCustom(TaskDTOCustom dto, @MappingTarget TaskDAO entity);

}
