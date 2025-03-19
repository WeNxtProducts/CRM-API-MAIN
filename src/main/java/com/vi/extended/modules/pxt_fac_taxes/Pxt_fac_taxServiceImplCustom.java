/*
 - Version Number 0.0.1
*/

package com.vi.extended.modules.pxt_fac_taxes;

import com.vi.model.dto.Pxt_fac_taxDTOCustom;
import java.util.List;

public class Pxt_fac_taxServiceImplCustom implements Pxt_fac_taxServiceCustom {

	private Pxt_fac_taxPersistentCustom pxt_fac_taxpersistentCustom;

	public Pxt_fac_taxServiceImplCustom(Pxt_fac_taxPersistentCustom pxt_fac_taxpersistentCustom) {
		this.pxt_fac_taxpersistentCustom = pxt_fac_taxpersistentCustom;
	}

	@Override
	public List<Pxt_fac_taxDTOCustom> fetchAll() {
		return pxt_fac_taxpersistentCustom.fetchAll();
	}

	@Override
	public Pxt_fac_taxDTOCustom get(Long id) {
		return pxt_fac_taxpersistentCustom.get(id);
	}

	@Override
	public Pxt_fac_taxDTOCustom create(Pxt_fac_taxDTOCustom pxt_fac_taxDTOCustom) {
		return pxt_fac_taxpersistentCustom.create(pxt_fac_taxDTOCustom);
	}

	@Override
	public Pxt_fac_taxDTOCustom update(Pxt_fac_taxDTOCustom pxt_fac_taxDTOCustom) {
		return pxt_fac_taxpersistentCustom.update(pxt_fac_taxDTOCustom);
	}

	@Override
	public Boolean delete(Long id) {
		return pxt_fac_taxpersistentCustom.delete(id);
	}
}