/*
 - Version Number 0.0.1
*/

package com.vi.base.modules.leads;

import com.fasterxml.jackson.databind.JsonNode;
import com.vi.model.dto.LeadDTO;
import java.util.List;

public class LeadServiceImpl implements LeadService {

	private LeadPersistent leadPersistent;

	public LeadServiceImpl(LeadPersistent leadPersistent) {
		this.leadPersistent = leadPersistent;
	}

	@Override
	public List<LeadDTO> fetchAll() {
		return leadPersistent.fetchAll();
	}

	@Override
	public LeadDTO get(Long id) {
		return leadPersistent.get(id);
	}

	@Override
	public LeadDTO create(LeadDTO leadDTO) {
		return leadPersistent.create(leadDTO);
	}

	@Override
	public LeadDTO update(LeadDTO leadDTO) {
		return leadPersistent.update(leadDTO);
	}

	@Override
	public Boolean delete(Long id) {
		return leadPersistent.delete(id);
	}

	@Override
	public List<LeadDTO> filterData(String search) {
		return leadPersistent.filterData(search);
	}

	@Override
	public List<LeadDTO> filterData(JsonNode search, int page, int size) {
		return leadPersistent.filterData(search, page, size);
	}
	
	@Override
	public List<LeadDTO> filterData(JsonNode search) {
		return leadPersistent.filterData(search);
	}
}