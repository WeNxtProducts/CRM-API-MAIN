/*
 - Version Number 0.0.1
*/

package com.vi.base.modules.pxt_fac_parts;

import com.fasterxml.jackson.databind.JsonNode;
import com.vi.model.dto.Pxt_fac_partDTO;
import java.util.List;

public class Pxt_fac_partServiceImpl implements Pxt_fac_partService {

	private Pxt_fac_partPersistent pxt_fac_partPersistent;

	public Pxt_fac_partServiceImpl(Pxt_fac_partPersistent pxt_fac_partPersistent) {
		this.pxt_fac_partPersistent = pxt_fac_partPersistent;
	}

	@Override
	public List<Pxt_fac_partDTO> fetchAll() {
		return pxt_fac_partPersistent.fetchAll();
	}

	@Override
	public Pxt_fac_partDTO get(Long id) {
		return pxt_fac_partPersistent.get(id);
	}

	@Override
	public Pxt_fac_partDTO create(Pxt_fac_partDTO pxt_fac_partDTO) {
		return pxt_fac_partPersistent.create(pxt_fac_partDTO);
	}

	@Override
	public Pxt_fac_partDTO update(Pxt_fac_partDTO pxt_fac_partDTO) {
		return pxt_fac_partPersistent.update(pxt_fac_partDTO);
	}

	@Override
	public Boolean delete(Long id) {
		return pxt_fac_partPersistent.delete(id);
	}

	@Override
	public List<Pxt_fac_partDTO> filterData(String search) {
		return pxt_fac_partPersistent.filterData(search);
	}

	@Override
	public List<Pxt_fac_partDTO> filterData(JsonNode search) {
		return pxt_fac_partPersistent.filterData(search);
	}
}