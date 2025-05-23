/*
 - Version Number 0.0.1
*/

package com.vi.base.modules.enquirys;

import com.fasterxml.jackson.databind.JsonNode;
import com.vi.model.dto.EnquiryDTO;
import java.util.List;

public class EnquiryServiceImpl implements EnquiryService {

	private EnquiryPersistent enquiryPersistent;

	public EnquiryServiceImpl(EnquiryPersistent enquiryPersistent) {
		this.enquiryPersistent = enquiryPersistent;
	}

	@Override
	public List<EnquiryDTO> fetchAll() {
		return enquiryPersistent.fetchAll();
	}

	@Override
	public EnquiryDTO get(Long id) {
		return enquiryPersistent.get(id);
	}

	@Override
	public EnquiryDTO create(EnquiryDTO enquiryDTO) {
		return enquiryPersistent.create(enquiryDTO);
	}

	@Override
	public EnquiryDTO update(EnquiryDTO enquiryDTO) {
		return enquiryPersistent.update(enquiryDTO);
	}

	@Override
	public Boolean delete(Long id) {
		return enquiryPersistent.delete(id);
	}

	@Override
	public List<EnquiryDTO> filterData(String search) {
		return enquiryPersistent.filterData(search);
	}

	@Override
	public List<EnquiryDTO> filterData(JsonNode search, int page, int size) {
		return enquiryPersistent.filterData(search, page, size);
	}
	
	@Override
	public List<EnquiryDTO> filterData(JsonNode search) {
		return enquiryPersistent.filterData(search);
	}
}