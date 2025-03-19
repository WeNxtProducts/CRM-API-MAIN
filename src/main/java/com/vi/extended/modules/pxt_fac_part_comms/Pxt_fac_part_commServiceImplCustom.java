/*
 - Version Number 0.0.1
*/

package com.vi.extended.modules.pxt_fac_part_comms;

import com.vi.model.dto.Pxt_fac_part_commDTOCustom;
import java.util.List;

public class Pxt_fac_part_commServiceImplCustom implements Pxt_fac_part_commServiceCustom {

	private Pxt_fac_part_commPersistentCustom pxt_fac_part_commpersistentCustom;

	public Pxt_fac_part_commServiceImplCustom(Pxt_fac_part_commPersistentCustom pxt_fac_part_commpersistentCustom) {
		this.pxt_fac_part_commpersistentCustom = pxt_fac_part_commpersistentCustom;
	}

	@Override
	public List<Pxt_fac_part_commDTOCustom> fetchAll() {
		return pxt_fac_part_commpersistentCustom.fetchAll();
	}

	@Override
	public Pxt_fac_part_commDTOCustom get(Long id) {
		return pxt_fac_part_commpersistentCustom.get(id);
	}

	@Override
	public Pxt_fac_part_commDTOCustom create(Pxt_fac_part_commDTOCustom pxt_fac_part_commDTOCustom) {
		return pxt_fac_part_commpersistentCustom.create(pxt_fac_part_commDTOCustom);
	}

	@Override
	public Pxt_fac_part_commDTOCustom update(Pxt_fac_part_commDTOCustom pxt_fac_part_commDTOCustom) {
		return pxt_fac_part_commpersistentCustom.update(pxt_fac_part_commDTOCustom);
	}

	@Override
	public Boolean delete(Long id) {
		return pxt_fac_part_commpersistentCustom.delete(id);
	}
}