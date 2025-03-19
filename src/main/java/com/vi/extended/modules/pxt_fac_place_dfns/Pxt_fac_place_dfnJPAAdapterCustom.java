/*
 - Version Number 0.0.1
*/

package com.vi.extended.modules.pxt_fac_place_dfns;

import com.vi.model.dao.Pxt_fac_place_dfnDAO;
import com.vi.model.dto.Pxt_fac_place_dfnDTOCustom;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

public class Pxt_fac_place_dfnJPAAdapterCustom implements Pxt_fac_place_dfnPersistentCustom {
	@Autowired
	Pxt_fac_place_dfnRepositoryCustom pxt_fac_place_dfnRepositoryCustom;

	@Override
	public List<Pxt_fac_place_dfnDTOCustom> fetchAll() {
		var pxt_fac_place_dfnDAOList = pxt_fac_place_dfnRepositoryCustom.findAll();
		return Pxt_fac_place_dfnMapperCustom.INSTANCE.pxt_fac_place_dfnDAOListToPxt_fac_place_dfnDTOListCustom(pxt_fac_place_dfnDAOList);
	}

	@Override
	public Pxt_fac_place_dfnDTOCustom get(Long id) {
		var pxt_fac_place_dfnDAO = pxt_fac_place_dfnRepositoryCustom.findById(id);
		if (pxt_fac_place_dfnDAO.isPresent()) {
			return Pxt_fac_place_dfnMapperCustom.INSTANCE.pxt_fac_place_dfnDAOToPxt_fac_place_dfnDTOCustom(pxt_fac_place_dfnDAO.get());
		}
		return null;
	}

	@Override
	public Pxt_fac_place_dfnDTOCustom create(Pxt_fac_place_dfnDTOCustom pxt_fac_place_dfnDTOCustom) {
		var pxt_fac_place_dfnDAO = Pxt_fac_place_dfnMapperCustom.INSTANCE.pxt_fac_place_dfnDTOCustomToPxt_fac_place_dfnDAO(pxt_fac_place_dfnDTOCustom);
		Pxt_fac_place_dfnDAO newPxt_fac_place_dfn = pxt_fac_place_dfnRepositoryCustom.save(pxt_fac_place_dfnDAO);
		return Pxt_fac_place_dfnMapperCustom.INSTANCE.pxt_fac_place_dfnDAOToPxt_fac_place_dfnDTOCustom(newPxt_fac_place_dfn);
	}

	@Override
	public Pxt_fac_place_dfnDTOCustom update(Pxt_fac_place_dfnDTOCustom pxt_fac_place_dfnDTOCustom) {
		var pxt_fac_place_dfnDAO = pxt_fac_place_dfnRepositoryCustom.getById(pxt_fac_place_dfnDTOCustom.getFPD_SYS_ID());
		Pxt_fac_place_dfnMapperCustom.INSTANCE.assignValuesCustom(pxt_fac_place_dfnDTOCustom, pxt_fac_place_dfnDAO);
		Pxt_fac_place_dfnDAO newPxt_fac_place_dfn = pxt_fac_place_dfnRepositoryCustom.save(pxt_fac_place_dfnDAO);
		return Pxt_fac_place_dfnMapperCustom.INSTANCE.pxt_fac_place_dfnDAOToPxt_fac_place_dfnDTOCustom(newPxt_fac_place_dfn);
	}

	@Override
	public Boolean delete(Long id) {
		var pxt_fac_place_dfnDAO = pxt_fac_place_dfnRepositoryCustom.getById(id);
	//	pxt_fac_place_dfnDAO.setDeleted(true);
		pxt_fac_place_dfnRepositoryCustom.save(pxt_fac_place_dfnDAO);
		return true;
	}
}