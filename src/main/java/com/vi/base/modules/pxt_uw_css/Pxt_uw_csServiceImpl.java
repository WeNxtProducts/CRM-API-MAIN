/*
 - Version Number 0.0.1
*/

package com.vi.base.modules.pxt_uw_css;

import com.fasterxml.jackson.databind.JsonNode;
import com.vi.model.dto.Pxt_uw_csDTO;
import java.util.List;

public class Pxt_uw_csServiceImpl implements Pxt_uw_csService {

	private Pxt_uw_csPersistent pxt_uw_csPersistent;

	public Pxt_uw_csServiceImpl(Pxt_uw_csPersistent pxt_uw_csPersistent) {
		this.pxt_uw_csPersistent = pxt_uw_csPersistent;
	}

	@Override
	public List<Pxt_uw_csDTO> fetchAll() {
		return pxt_uw_csPersistent.fetchAll();
	}

	@Override
	public Pxt_uw_csDTO get(Long id) {
		return pxt_uw_csPersistent.get(id);
	}

	@Override
	public Pxt_uw_csDTO create(Pxt_uw_csDTO pxt_uw_csDTO) {
		return pxt_uw_csPersistent.create(pxt_uw_csDTO);
	}

	@Override
	public Pxt_uw_csDTO update(Pxt_uw_csDTO pxt_uw_csDTO) {
		return pxt_uw_csPersistent.update(pxt_uw_csDTO);
	}

	@Override
	public Boolean delete(Long id) {
		return pxt_uw_csPersistent.delete(id);
	}

	@Override
	public List<Pxt_uw_csDTO> filterData(String search) {
		return pxt_uw_csPersistent.filterData(search);
	}

	@Override
	public List<Pxt_uw_csDTO> filterData(JsonNode search) {
		return pxt_uw_csPersistent.filterData(search);
	}
}