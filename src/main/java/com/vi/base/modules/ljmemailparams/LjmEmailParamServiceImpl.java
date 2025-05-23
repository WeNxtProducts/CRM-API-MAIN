/*
 - Version Number 0.0.1
*/

package com.vi.base.modules.ljmemailparams;

import com.fasterxml.jackson.databind.JsonNode;
import com.vi.model.dto.LjmEmailParamDTO;
import java.util.List;

public class LjmEmailParamServiceImpl implements LjmEmailParamService {

	private LjmEmailParamPersistent ljmEmailParamPersistent;

	public LjmEmailParamServiceImpl(LjmEmailParamPersistent ljmEmailParamPersistent) {
		this.ljmEmailParamPersistent = ljmEmailParamPersistent;
	}

	@Override
	public List<LjmEmailParamDTO> fetchAll() {
		return ljmEmailParamPersistent.fetchAll();
	}

	@Override
	public LjmEmailParamDTO get(Long id) {
		return ljmEmailParamPersistent.get(id);
	}

	@Override
	public LjmEmailParamDTO create(LjmEmailParamDTO ljmEmailParamDTO) {
		return ljmEmailParamPersistent.create(ljmEmailParamDTO);
	}

	@Override
	public LjmEmailParamDTO update(LjmEmailParamDTO ljmEmailParamDTO) {
		return ljmEmailParamPersistent.update(ljmEmailParamDTO);
	}

	@Override
	public Boolean delete(Long id) {
		return ljmEmailParamPersistent.delete(id);
	}

	@Override
	public List<LjmEmailParamDTO> filterData(String search) {
		return ljmEmailParamPersistent.filterData(search);
	}

	@Override
	public List<LjmEmailParamDTO> filterData(JsonNode search, int page, int size) {
		return ljmEmailParamPersistent.filterData(search, page, size);
	}
	
	@Override
	public List<LjmEmailParamDTO> filterData(JsonNode search) {
		return ljmEmailParamPersistent.filterData(search);
	}
}