/*
 - Version Number 0.0.1
*/

package com.vi.extended.modules.pxt_fac_rsk_cvrs;

import com.vi.model.dao.Pxt_fac_rsk_cvrDAO;
import com.vi.model.dto.Pxt_fac_rsk_cvrDTOCustom;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

public class Pxt_fac_rsk_cvrJPAAdapterCustom implements Pxt_fac_rsk_cvrPersistentCustom {
	@Autowired
	Pxt_fac_rsk_cvrRepositoryCustom pxt_fac_rsk_cvrRepositoryCustom;

	@Override
	public List<Pxt_fac_rsk_cvrDTOCustom> fetchAll() {
		var pxt_fac_rsk_cvrDAOList = pxt_fac_rsk_cvrRepositoryCustom.findAll();
		return Pxt_fac_rsk_cvrMapperCustom.INSTANCE.pxt_fac_rsk_cvrDAOListToPxt_fac_rsk_cvrDTOListCustom(pxt_fac_rsk_cvrDAOList);
	}

	@Override
	public Pxt_fac_rsk_cvrDTOCustom get(Long id) {
		var pxt_fac_rsk_cvrDAO = pxt_fac_rsk_cvrRepositoryCustom.findById(id);
		if (pxt_fac_rsk_cvrDAO.isPresent()) {
			return Pxt_fac_rsk_cvrMapperCustom.INSTANCE.pxt_fac_rsk_cvrDAOToPxt_fac_rsk_cvrDTOCustom(pxt_fac_rsk_cvrDAO.get());
		}
		return null;
	}

	@Override
	public Pxt_fac_rsk_cvrDTOCustom create(Pxt_fac_rsk_cvrDTOCustom pxt_fac_rsk_cvrDTOCustom) {
		var pxt_fac_rsk_cvrDAO = Pxt_fac_rsk_cvrMapperCustom.INSTANCE.pxt_fac_rsk_cvrDTOCustomToPxt_fac_rsk_cvrDAO(pxt_fac_rsk_cvrDTOCustom);
		Pxt_fac_rsk_cvrDAO newPxt_fac_rsk_cvr = pxt_fac_rsk_cvrRepositoryCustom.save(pxt_fac_rsk_cvrDAO);
		return Pxt_fac_rsk_cvrMapperCustom.INSTANCE.pxt_fac_rsk_cvrDAOToPxt_fac_rsk_cvrDTOCustom(newPxt_fac_rsk_cvr);
	}

	@Override
	public Pxt_fac_rsk_cvrDTOCustom update(Pxt_fac_rsk_cvrDTOCustom pxt_fac_rsk_cvrDTOCustom) {
		var pxt_fac_rsk_cvrDAO = pxt_fac_rsk_cvrRepositoryCustom.getById(pxt_fac_rsk_cvrDTOCustom.getFRC_SYS_ID());
		Pxt_fac_rsk_cvrMapperCustom.INSTANCE.assignValuesCustom(pxt_fac_rsk_cvrDTOCustom, pxt_fac_rsk_cvrDAO);
		Pxt_fac_rsk_cvrDAO newPxt_fac_rsk_cvr = pxt_fac_rsk_cvrRepositoryCustom.save(pxt_fac_rsk_cvrDAO);
		return Pxt_fac_rsk_cvrMapperCustom.INSTANCE.pxt_fac_rsk_cvrDAOToPxt_fac_rsk_cvrDTOCustom(newPxt_fac_rsk_cvr);
	}

	@Override
	public Boolean delete(Long id) {
		var pxt_fac_rsk_cvrDAO = pxt_fac_rsk_cvrRepositoryCustom.getById(id);
	//	pxt_fac_rsk_cvrDAO.setDeleted(true);
		pxt_fac_rsk_cvrRepositoryCustom.save(pxt_fac_rsk_cvrDAO);
		return true;
	}
}