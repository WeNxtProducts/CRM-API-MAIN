/*
 - Version Number 0.0.1
*/

package com.vi.extended.modules.pxt_fac_hdrs;

import com.vi.model.dao.Pxt_fac_hdrDAO;
import com.vi.model.dto.Pxt_fac_hdrDTOCustom;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

public class Pxt_fac_hdrJPAAdapterCustom implements Pxt_fac_hdrPersistentCustom {
	@Autowired
	Pxt_fac_hdrRepositoryCustom pxt_fac_hdrRepositoryCustom;

	@Override
	public List<Pxt_fac_hdrDTOCustom> fetchAll() {
		var pxt_fac_hdrDAOList = pxt_fac_hdrRepositoryCustom.findAll();
		return Pxt_fac_hdrMapperCustom.INSTANCE.pxt_fac_hdrDAOListToPxt_fac_hdrDTOListCustom(pxt_fac_hdrDAOList);
	}

	@Override
	public Pxt_fac_hdrDTOCustom get(Long id) {
		var pxt_fac_hdrDAO = pxt_fac_hdrRepositoryCustom.findById(id);
		if (pxt_fac_hdrDAO.isPresent()) {
			return Pxt_fac_hdrMapperCustom.INSTANCE.pxt_fac_hdrDAOToPxt_fac_hdrDTOCustom(pxt_fac_hdrDAO.get());
		}
		return null;
	}

	@Override
	public Pxt_fac_hdrDTOCustom create(Pxt_fac_hdrDTOCustom pxt_fac_hdrDTOCustom) {
		var pxt_fac_hdrDAO = Pxt_fac_hdrMapperCustom.INSTANCE.pxt_fac_hdrDTOCustomToPxt_fac_hdrDAO(pxt_fac_hdrDTOCustom);
		Pxt_fac_hdrDAO newPxt_fac_hdr = pxt_fac_hdrRepositoryCustom.save(pxt_fac_hdrDAO);
		return Pxt_fac_hdrMapperCustom.INSTANCE.pxt_fac_hdrDAOToPxt_fac_hdrDTOCustom(newPxt_fac_hdr);
	}

	@Override
	public Pxt_fac_hdrDTOCustom update(Pxt_fac_hdrDTOCustom pxt_fac_hdrDTOCustom) {
		var pxt_fac_hdrDAO = pxt_fac_hdrRepositoryCustom.getById(pxt_fac_hdrDTOCustom.getFH_SYS_ID());
		Pxt_fac_hdrMapperCustom.INSTANCE.assignValuesCustom(pxt_fac_hdrDTOCustom, pxt_fac_hdrDAO);
		Pxt_fac_hdrDAO newPxt_fac_hdr = pxt_fac_hdrRepositoryCustom.save(pxt_fac_hdrDAO);
		return Pxt_fac_hdrMapperCustom.INSTANCE.pxt_fac_hdrDAOToPxt_fac_hdrDTOCustom(newPxt_fac_hdr);
	}

	@Override
	public Boolean delete(Long id) {
		var pxt_fac_hdrDAO = pxt_fac_hdrRepositoryCustom.getById(id);
	//	pxt_fac_hdrDAO.setDeleted(true);
		pxt_fac_hdrRepositoryCustom.save(pxt_fac_hdrDAO);
		return true;
	}
}