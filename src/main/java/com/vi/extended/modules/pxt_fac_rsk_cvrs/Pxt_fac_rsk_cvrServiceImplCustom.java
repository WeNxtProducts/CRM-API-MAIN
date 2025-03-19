/*
 - Version Number 0.0.1
*/

package com.vi.extended.modules.pxt_fac_rsk_cvrs;

import com.vi.model.dto.Pxt_fac_rsk_cvrDTOCustom;
import java.util.List;

public class Pxt_fac_rsk_cvrServiceImplCustom implements Pxt_fac_rsk_cvrServiceCustom {

	private Pxt_fac_rsk_cvrPersistentCustom pxt_fac_rsk_cvrpersistentCustom;

	public Pxt_fac_rsk_cvrServiceImplCustom(Pxt_fac_rsk_cvrPersistentCustom pxt_fac_rsk_cvrpersistentCustom) {
		this.pxt_fac_rsk_cvrpersistentCustom = pxt_fac_rsk_cvrpersistentCustom;
	}

	@Override
	public List<Pxt_fac_rsk_cvrDTOCustom> fetchAll() {
		return pxt_fac_rsk_cvrpersistentCustom.fetchAll();
	}

	@Override
	public Pxt_fac_rsk_cvrDTOCustom get(Long id) {
		return pxt_fac_rsk_cvrpersistentCustom.get(id);
	}

	@Override
	public Pxt_fac_rsk_cvrDTOCustom create(Pxt_fac_rsk_cvrDTOCustom pxt_fac_rsk_cvrDTOCustom) {
		return pxt_fac_rsk_cvrpersistentCustom.create(pxt_fac_rsk_cvrDTOCustom);
	}

	@Override
	public Pxt_fac_rsk_cvrDTOCustom update(Pxt_fac_rsk_cvrDTOCustom pxt_fac_rsk_cvrDTOCustom) {
		return pxt_fac_rsk_cvrpersistentCustom.update(pxt_fac_rsk_cvrDTOCustom);
	}

	@Override
	public Boolean delete(Long id) {
		return pxt_fac_rsk_cvrpersistentCustom.delete(id);
	}
}