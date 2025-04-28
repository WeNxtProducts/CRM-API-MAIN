/*
 - Version Number 0.0.1
*/

package com.vi.base.modules.workflows;

import com.fasterxml.jackson.databind.JsonNode;
import com.vi.model.dto.WorkflowDTO;
import java.util.List;

public class WorkflowServiceImpl implements WorkflowService {

	private WorkflowPersistent workflowPersistent;

	public WorkflowServiceImpl(WorkflowPersistent workflowPersistent) {
		this.workflowPersistent = workflowPersistent;
	}

	@Override
	public List<WorkflowDTO> fetchAll() {
		return workflowPersistent.fetchAll();
	}

	@Override
	public WorkflowDTO get(Long id) {
		return workflowPersistent.get(id);
	}

	@Override
	public WorkflowDTO create(WorkflowDTO workflowDTO) {
		return workflowPersistent.create(workflowDTO);
	}

	@Override
	public WorkflowDTO update(WorkflowDTO workflowDTO) {
		return workflowPersistent.update(workflowDTO);
	}

	@Override
	public Boolean delete(Long id) {
		return workflowPersistent.delete(id);
	}

	@Override
	public List<WorkflowDTO> filterData(String search) {
		return workflowPersistent.filterData(search);
	}

	@Override
	public List<WorkflowDTO> filterData(JsonNode search,int page,int size) {
		return workflowPersistent.filterData(search, page, size);
	}
}