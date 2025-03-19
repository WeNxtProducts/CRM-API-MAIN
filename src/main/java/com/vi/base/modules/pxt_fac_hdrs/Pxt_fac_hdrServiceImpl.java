/*
 - Version Number 0.0.1
*/

package com.vi.base.modules.pxt_fac_hdrs;

import com.fasterxml.jackson.databind.JsonNode;
import com.vi.model.dto.Pxt_fac_hdrDTO;
import java.util.List;

public class Pxt_fac_hdrServiceImpl implements Pxt_fac_hdrService {

	private Pxt_fac_hdrPersistent pxt_fac_hdrPersistent;

	public Pxt_fac_hdrServiceImpl(Pxt_fac_hdrPersistent pxt_fac_hdrPersistent) {
		this.pxt_fac_hdrPersistent = pxt_fac_hdrPersistent;
	}

	@Override
	public List<Pxt_fac_hdrDTO> fetchAll() {
		return pxt_fac_hdrPersistent.fetchAll();
	}

	@Override
	public Pxt_fac_hdrDTO get(Long id) {
		return pxt_fac_hdrPersistent.get(id);
	}

	@Override
	public Pxt_fac_hdrDTO create(Pxt_fac_hdrDTO pxt_fac_hdrDTO) {
		return pxt_fac_hdrPersistent.create(pxt_fac_hdrDTO);
	}

	@Override
	public Pxt_fac_hdrDTO update(Pxt_fac_hdrDTO pxt_fac_hdrDTO) {
		return pxt_fac_hdrPersistent.update(pxt_fac_hdrDTO);
	}

	@Override
	public Boolean delete(Long id) {
		return pxt_fac_hdrPersistent.delete(id);
	}

	@Override
	public List<Pxt_fac_hdrDTO> filterData(String search) {
		return pxt_fac_hdrPersistent.filterData(search);
	}

	@Override
	public List<Pxt_fac_hdrDTO> filterData(JsonNode search) {
		return pxt_fac_hdrPersistent.filterData(search);
	}
}