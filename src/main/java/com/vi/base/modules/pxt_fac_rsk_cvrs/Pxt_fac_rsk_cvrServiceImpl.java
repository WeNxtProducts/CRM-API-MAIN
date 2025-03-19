/*
 - Version Number 0.0.1
*/

package com.vi.base.modules.pxt_fac_rsk_cvrs;

import com.fasterxml.jackson.databind.JsonNode;
import com.vi.model.dto.Pxt_fac_rsk_cvrDTO;
import java.util.List;

public class Pxt_fac_rsk_cvrServiceImpl implements Pxt_fac_rsk_cvrService {

	private Pxt_fac_rsk_cvrPersistent pxt_fac_rsk_cvrPersistent;

	public Pxt_fac_rsk_cvrServiceImpl(Pxt_fac_rsk_cvrPersistent pxt_fac_rsk_cvrPersistent) {
		this.pxt_fac_rsk_cvrPersistent = pxt_fac_rsk_cvrPersistent;
	}

	@Override
	public List<Pxt_fac_rsk_cvrDTO> fetchAll() {
		return pxt_fac_rsk_cvrPersistent.fetchAll();
	}

	@Override
	public Pxt_fac_rsk_cvrDTO get(Long id) {
		return pxt_fac_rsk_cvrPersistent.get(id);
	}

	@Override
	public Pxt_fac_rsk_cvrDTO create(Pxt_fac_rsk_cvrDTO pxt_fac_rsk_cvrDTO) {
		return pxt_fac_rsk_cvrPersistent.create(pxt_fac_rsk_cvrDTO);
	}

	@Override
	public Pxt_fac_rsk_cvrDTO update(Pxt_fac_rsk_cvrDTO pxt_fac_rsk_cvrDTO) {
		return pxt_fac_rsk_cvrPersistent.update(pxt_fac_rsk_cvrDTO);
	}

	@Override
	public Boolean delete(Long id) {
		return pxt_fac_rsk_cvrPersistent.delete(id);
	}

	@Override
	public List<Pxt_fac_rsk_cvrDTO> filterData(String search) {
		return pxt_fac_rsk_cvrPersistent.filterData(search);
	}

	@Override
	public List<Pxt_fac_rsk_cvrDTO> filterData(JsonNode search) {
		return pxt_fac_rsk_cvrPersistent.filterData(search);
	}
}