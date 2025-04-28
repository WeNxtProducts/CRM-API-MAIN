/*
 - Version Number 0.0.1
*/

package com.vi.base.modules.ljmemailtemplates;

import com.fasterxml.jackson.databind.JsonNode;
import com.vi.model.dto.LjmEmailTemplateDTO;
import java.util.List;

public class LjmEmailTemplateServiceImpl implements LjmEmailTemplateService {

	private LjmEmailTemplatePersistent ljmEmailTemplatePersistent;

	public LjmEmailTemplateServiceImpl(LjmEmailTemplatePersistent ljmEmailTemplatePersistent) {
		this.ljmEmailTemplatePersistent = ljmEmailTemplatePersistent;
	}

	@Override
	public List<LjmEmailTemplateDTO> fetchAll() {
		return ljmEmailTemplatePersistent.fetchAll();
	}

	@Override
	public LjmEmailTemplateDTO get(Long id) {
		return ljmEmailTemplatePersistent.get(id);
	}

	@Override
	public LjmEmailTemplateDTO create(LjmEmailTemplateDTO ljmEmailTemplateDTO) {
		return ljmEmailTemplatePersistent.create(ljmEmailTemplateDTO);
	}

	@Override
	public LjmEmailTemplateDTO update(LjmEmailTemplateDTO ljmEmailTemplateDTO) {
		return ljmEmailTemplatePersistent.update(ljmEmailTemplateDTO);
	}

	@Override
	public Boolean delete(Long id) {
		return ljmEmailTemplatePersistent.delete(id);
	}

	@Override
	public List<LjmEmailTemplateDTO> filterData(String search) {
		return ljmEmailTemplatePersistent.filterData(search);
	}

	@Override
	public List<LjmEmailTemplateDTO> filterData(JsonNode search, int page, int size) {
		return ljmEmailTemplatePersistent.filterData(search, page, size);
	}
}