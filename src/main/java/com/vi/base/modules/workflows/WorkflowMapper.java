/*
 - Version Number 0.0.1
*/

package com.vi.base.modules.workflows;

import com.vi.model.dao.WorkflowDAO;
import com.vi.model.dto.WorkflowDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WorkflowMapper {

	WorkflowMapper INSTANCE = Mappers.getMapper(WorkflowMapper.class);

	WorkflowDTO workflowDAOToWorkflowDTO(WorkflowDAO workflowDAO);

	WorkflowDAO workflowDTOToWorkflowDAO(WorkflowDTO workflowDTO);

	List<WorkflowDTO> workflowDAOListToWorkflowDTOList(List<WorkflowDAO> workflowDAOList);

	List<WorkflowDAO> workflowDTOListToWorkflowDAOList(List<WorkflowDTO> workflowDTOList);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	void assignValues(WorkflowDTO dto, @MappingTarget WorkflowDAO entity);
}