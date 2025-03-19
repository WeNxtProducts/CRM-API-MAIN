/*
 - Version Number 0.0.1
*/

package com.vi.base.modules.pxt_fac_part_comms;

import com.fasterxml.jackson.databind.JsonNode;
import com.vi.model.dto.Pxt_fac_part_commDTO;
import java.util.List;

public class Pxt_fac_part_commServiceImpl implements Pxt_fac_part_commService {

	private Pxt_fac_part_commPersistent pxt_fac_part_commPersistent;

	public Pxt_fac_part_commServiceImpl(Pxt_fac_part_commPersistent pxt_fac_part_commPersistent) {
		this.pxt_fac_part_commPersistent = pxt_fac_part_commPersistent;
	}

	@Override
	public List<Pxt_fac_part_commDTO> fetchAll() {
		return pxt_fac_part_commPersistent.fetchAll();
	}

	@Override
	public Pxt_fac_part_commDTO get(Long id) {
		return pxt_fac_part_commPersistent.get(id);
	}

	@Override
	public Pxt_fac_part_commDTO create(Pxt_fac_part_commDTO pxt_fac_part_commDTO) {
		return pxt_fac_part_commPersistent.create(pxt_fac_part_commDTO);
	}

	@Override
	public Pxt_fac_part_commDTO update(Pxt_fac_part_commDTO pxt_fac_part_commDTO) {
		return pxt_fac_part_commPersistent.update(pxt_fac_part_commDTO);
	}

	@Override
	public Boolean delete(Long id) {
		return pxt_fac_part_commPersistent.delete(id);
	}

	@Override
	public List<Pxt_fac_part_commDTO> filterData(String search) {
		return pxt_fac_part_commPersistent.filterData(search);
	}

	@Override
	public List<Pxt_fac_part_commDTO> filterData(JsonNode search) {
		return pxt_fac_part_commPersistent.filterData(search);
	}
}