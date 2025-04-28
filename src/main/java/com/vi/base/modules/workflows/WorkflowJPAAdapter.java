/*
 - Version Number 0.0.1
*/

package com.vi.base.modules.workflows;

import com.fasterxml.jackson.databind.JsonNode;
import com.vi.model.dao.WorkflowDAO;
import com.vi.model.dto.WorkflowDTO;
import com.vi.model.dto.WorkflowDTO;

import jakarta.persistence.EntityNotFoundException;

import com.vi.base.modules.workflows.WorkflowMapper;
import com.vi.base.modules.workflows.WorkflowMapper;
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


public class WorkflowJPAAdapter implements WorkflowPersistent {
	@Autowired
	WorkflowRepository workflowRepository;

	@Override
	public List<WorkflowDTO> fetchAll() {
		var workflowDAOList = workflowRepository.findAll();
		return WorkflowMapper.INSTANCE.workflowDAOListToWorkflowDTOList(workflowDAOList);
	}

	@Override
	public WorkflowDTO get(Long id) {
		var workflowDAO = workflowRepository.findById(id);
		if(workflowDAO.isPresent()) {
			return WorkflowMapper.INSTANCE.workflowDAOToWorkflowDTO(workflowDAO.get());
		}
		return null;
	}

  @SneakyThrows
	@Override
	public WorkflowDTO create(WorkflowDTO workflowDTO) {
		var workflowDAO = WorkflowMapper.INSTANCE.workflowDTOToWorkflowDAO(workflowDTO);
		var newWorkflow = workflowRepository.save(workflowDAO);
		var newData = WorkflowMapper.INSTANCE.workflowDAOToWorkflowDTO(newWorkflow);
		
		
		return newData;
	}

	@Override
 		public WorkflowDTO update(WorkflowDTO workflowDTO) {
			
		 var workflowDAO = workflowRepository.findById(workflowDTO.getWorkStepId())
        .orElseThrow(() -> new EntityNotFoundException("Record not found with id: " + workflowDTO.getWorkStepId()));
		var oldData = WorkflowMapper.INSTANCE.workflowDAOToWorkflowDTO(workflowDAO);
		WorkflowMapper.INSTANCE.assignValues(workflowDTO, workflowDAO);
		var newWorkflow = workflowRepository.save(workflowDAO);
		var newData = WorkflowMapper.INSTANCE.workflowDAOToWorkflowDTO(newWorkflow); 
		
		return newData;   
	}

	@SneakyThrows
	@Override
	public Boolean delete(Long id) {
	    var workflowDAO = workflowRepository.findById(id)
	                    .orElseThrow(() -> new RuntimeException("Workflow not found with id: " + id));

	    workflowDAO.setDeleted(true); // Set deleted flag to true
	    workflowRepository.save(workflowDAO); // Persist the change

	    return true; 
	}


	@Override
	public List<WorkflowDTO> filterData(String search) {
		Specification<WorkflowDAO> result = new FilterSpecificationsBuilder<WorkflowDAO>().with(search).build();
		return WorkflowMapper.INSTANCE.workflowDAOListToWorkflowDTOList(workflowRepository.findAll(result));
	}

	@Override
	public List<WorkflowDTO> filterData(JsonNode search, int page, int size) {
		Pageable pageable = PageRequest.of(page, size, Sort.by("workStepId").descending());
    Specification<WorkflowDAO> result = new FilterSpecificationsBuilder<WorkflowDAO>().with(search).build();
		return WorkflowMapper.INSTANCE.workflowDAOListToWorkflowDTOList(workflowRepository.findAll(result, pageable).getContent());
	}
}
