/*
 - Version Number 0.0.1
*/

package com.vi.extended.modules.pxt_fac_hdrs;

import com.vi.model.dto.Pxt_fac_hdrDTOCustom;
import java.util.List;

public class Pxt_fac_hdrServiceImplCustom implements Pxt_fac_hdrServiceCustom {

	private Pxt_fac_hdrPersistentCustom pxt_fac_hdrpersistentCustom;

	public Pxt_fac_hdrServiceImplCustom(Pxt_fac_hdrPersistentCustom pxt_fac_hdrpersistentCustom) {
		this.pxt_fac_hdrpersistentCustom = pxt_fac_hdrpersistentCustom;
	}

	@Override
	public List<Pxt_fac_hdrDTOCustom> fetchAll() {
		return pxt_fac_hdrpersistentCustom.fetchAll();
	}

	@Override
	public Pxt_fac_hdrDTOCustom get(Long id) {
		return pxt_fac_hdrpersistentCustom.get(id);
	}

	@Override
	public Pxt_fac_hdrDTOCustom create(Pxt_fac_hdrDTOCustom pxt_fac_hdrDTOCustom) {
		return pxt_fac_hdrpersistentCustom.create(pxt_fac_hdrDTOCustom);
	}

	@Override
	public Pxt_fac_hdrDTOCustom update(Pxt_fac_hdrDTOCustom pxt_fac_hdrDTOCustom) {
		return pxt_fac_hdrpersistentCustom.update(pxt_fac_hdrDTOCustom);
	}

	@Override
	public Boolean delete(Long id) {
		return pxt_fac_hdrpersistentCustom.delete(id);
	}
}