/*
 - Version Number 0.0.1
*/

package com.vi.base.modules.pxt_fac_taxes;

import com.fasterxml.jackson.databind.JsonNode;
import com.vi.model.dto.Pxt_fac_taxDTO;
import java.util.List;

public class Pxt_fac_taxServiceImpl implements Pxt_fac_taxService {

	private Pxt_fac_taxPersistent pxt_fac_taxPersistent;

	public Pxt_fac_taxServiceImpl(Pxt_fac_taxPersistent pxt_fac_taxPersistent) {
		this.pxt_fac_taxPersistent = pxt_fac_taxPersistent;
	}

	@Override
	public List<Pxt_fac_taxDTO> fetchAll() {
		return pxt_fac_taxPersistent.fetchAll();
	}

	@Override
	public Pxt_fac_taxDTO get(Long id) {
		return pxt_fac_taxPersistent.get(id);
	}

	@Override
	public Pxt_fac_taxDTO create(Pxt_fac_taxDTO pxt_fac_taxDTO) {
		return pxt_fac_taxPersistent.create(pxt_fac_taxDTO);
	}

	@Override
	public Pxt_fac_taxDTO update(Pxt_fac_taxDTO pxt_fac_taxDTO) {
		return pxt_fac_taxPersistent.update(pxt_fac_taxDTO);
	}

	@Override
	public Boolean delete(Long id) {
		return pxt_fac_taxPersistent.delete(id);
	}

	@Override
	public List<Pxt_fac_taxDTO> filterData(String search) {
		return pxt_fac_taxPersistent.filterData(search);
	}

	@Override
	public List<Pxt_fac_taxDTO> filterData(JsonNode search) {
		return pxt_fac_taxPersistent.filterData(search);
	}
}