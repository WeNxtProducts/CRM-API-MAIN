/*
 - Version Number 0.0.1
*/

package com.vi.extended.modules.pxt_fac_place_dfns;

import com.vi.model.dto.Pxt_fac_place_dfnDTOCustom;
import java.util.List;

public class Pxt_fac_place_dfnServiceImplCustom implements Pxt_fac_place_dfnServiceCustom {

	private Pxt_fac_place_dfnPersistentCustom pxt_fac_place_dfnpersistentCustom;

	public Pxt_fac_place_dfnServiceImplCustom(Pxt_fac_place_dfnPersistentCustom pxt_fac_place_dfnpersistentCustom) {
		this.pxt_fac_place_dfnpersistentCustom = pxt_fac_place_dfnpersistentCustom;
	}

	@Override
	public List<Pxt_fac_place_dfnDTOCustom> fetchAll() {
		return pxt_fac_place_dfnpersistentCustom.fetchAll();
	}

	@Override
	public Pxt_fac_place_dfnDTOCustom get(Long id) {
		return pxt_fac_place_dfnpersistentCustom.get(id);
	}

	@Override
	public Pxt_fac_place_dfnDTOCustom create(Pxt_fac_place_dfnDTOCustom pxt_fac_place_dfnDTOCustom) {
		return pxt_fac_place_dfnpersistentCustom.create(pxt_fac_place_dfnDTOCustom);
	}

	@Override
	public Pxt_fac_place_dfnDTOCustom update(Pxt_fac_place_dfnDTOCustom pxt_fac_place_dfnDTOCustom) {
		return pxt_fac_place_dfnpersistentCustom.update(pxt_fac_place_dfnDTOCustom);
	}

	@Override
	public Boolean delete(Long id) {
		return pxt_fac_place_dfnpersistentCustom.delete(id);
	}
}