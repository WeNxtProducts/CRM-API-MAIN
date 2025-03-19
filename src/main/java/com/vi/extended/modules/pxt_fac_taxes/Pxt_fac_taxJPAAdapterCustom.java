/*
 - Version Number 0.0.1
*/

package com.vi.extended.modules.pxt_fac_taxes;

import com.vi.model.dao.Pxt_fac_taxDAO;
import com.vi.model.dto.Pxt_fac_taxDTOCustom;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

public class Pxt_fac_taxJPAAdapterCustom implements Pxt_fac_taxPersistentCustom {
	@Autowired
	Pxt_fac_taxRepositoryCustom pxt_fac_taxRepositoryCustom;

	@Override
	public List<Pxt_fac_taxDTOCustom> fetchAll() {
		var pxt_fac_taxDAOList = pxt_fac_taxRepositoryCustom.findAll();
		return Pxt_fac_taxMapperCustom.INSTANCE.pxt_fac_taxDAOListToPxt_fac_taxDTOListCustom(pxt_fac_taxDAOList);
	}

	@Override
	public Pxt_fac_taxDTOCustom get(Long id) {
		var pxt_fac_taxDAO = pxt_fac_taxRepositoryCustom.findById(id);
		if (pxt_fac_taxDAO.isPresent()) {
			return Pxt_fac_taxMapperCustom.INSTANCE.pxt_fac_taxDAOToPxt_fac_taxDTOCustom(pxt_fac_taxDAO.get());
		}
		return null;
	}

	@Override
	public Pxt_fac_taxDTOCustom create(Pxt_fac_taxDTOCustom pxt_fac_taxDTOCustom) {
		var pxt_fac_taxDAO = Pxt_fac_taxMapperCustom.INSTANCE.pxt_fac_taxDTOCustomToPxt_fac_taxDAO(pxt_fac_taxDTOCustom);
		Pxt_fac_taxDAO newPxt_fac_tax = pxt_fac_taxRepositoryCustom.save(pxt_fac_taxDAO);
		return Pxt_fac_taxMapperCustom.INSTANCE.pxt_fac_taxDAOToPxt_fac_taxDTOCustom(newPxt_fac_tax);
	}

	@Override
	public Pxt_fac_taxDTOCustom update(Pxt_fac_taxDTOCustom pxt_fac_taxDTOCustom) {
		var pxt_fac_taxDAO = pxt_fac_taxRepositoryCustom.getById(pxt_fac_taxDTOCustom.getFtSysId());
		Pxt_fac_taxMapperCustom.INSTANCE.assignValuesCustom(pxt_fac_taxDTOCustom, pxt_fac_taxDAO);
		Pxt_fac_taxDAO newPxt_fac_tax = pxt_fac_taxRepositoryCustom.save(pxt_fac_taxDAO);
		return Pxt_fac_taxMapperCustom.INSTANCE.pxt_fac_taxDAOToPxt_fac_taxDTOCustom(newPxt_fac_tax);
	}

	@Override
	public Boolean delete(Long id) {
		var pxt_fac_taxDAO = pxt_fac_taxRepositoryCustom.getById(id);
	//	pxt_fac_taxDAO.setDeleted(true);
		pxt_fac_taxRepositoryCustom.save(pxt_fac_taxDAO);
		return true;
	}
}