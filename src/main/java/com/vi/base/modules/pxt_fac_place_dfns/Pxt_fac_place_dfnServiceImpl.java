/*
 - Version Number 0.0.1
*/

package com.vi.base.modules.pxt_fac_place_dfns;

import com.fasterxml.jackson.databind.JsonNode;
import com.vi.model.dto.Pxt_fac_place_dfnDTO;
import java.util.List;

public class Pxt_fac_place_dfnServiceImpl implements Pxt_fac_place_dfnService {

	private Pxt_fac_place_dfnPersistent pxt_fac_place_dfnPersistent;

	public Pxt_fac_place_dfnServiceImpl(Pxt_fac_place_dfnPersistent pxt_fac_place_dfnPersistent) {
		this.pxt_fac_place_dfnPersistent = pxt_fac_place_dfnPersistent;
	}

	@Override
	public List<Pxt_fac_place_dfnDTO> fetchAll() {
		return pxt_fac_place_dfnPersistent.fetchAll();
	}

	@Override
	public Pxt_fac_place_dfnDTO get(Long id) {
		return pxt_fac_place_dfnPersistent.get(id);
	}

	@Override
	public Pxt_fac_place_dfnDTO create(Pxt_fac_place_dfnDTO pxt_fac_place_dfnDTO) {
		return pxt_fac_place_dfnPersistent.create(pxt_fac_place_dfnDTO);
	}

	@Override
	public Pxt_fac_place_dfnDTO update(Pxt_fac_place_dfnDTO pxt_fac_place_dfnDTO) {
		return pxt_fac_place_dfnPersistent.update(pxt_fac_place_dfnDTO);
	}

	@Override
	public Boolean delete(Long id) {
		return pxt_fac_place_dfnPersistent.delete(id);
	}

	@Override
	public List<Pxt_fac_place_dfnDTO> filterData(String search) {
		return pxt_fac_place_dfnPersistent.filterData(search);
	}

	@Override
	public List<Pxt_fac_place_dfnDTO> filterData(JsonNode search) {
		return pxt_fac_place_dfnPersistent.filterData(search);
	}
}