/*
 - Version Number 0.0.1
*/

package com.vi.extended.modules.pxt_fac_part_comms;

import com.vi.model.dao.Pxt_fac_part_commDAO;
import com.vi.model.dto.Pxt_fac_part_commDTOCustom;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

public class Pxt_fac_part_commJPAAdapterCustom implements Pxt_fac_part_commPersistentCustom {
	@Autowired
	Pxt_fac_part_commRepositoryCustom pxt_fac_part_commRepositoryCustom;

	@Override
	public List<Pxt_fac_part_commDTOCustom> fetchAll() {
		var pxt_fac_part_commDAOList = pxt_fac_part_commRepositoryCustom.findAll();
		return Pxt_fac_part_commMapperCustom.INSTANCE.pxt_fac_part_commDAOListToPxt_fac_part_commDTOListCustom(pxt_fac_part_commDAOList);
	}

	@Override
	public Pxt_fac_part_commDTOCustom get(Long id) {
		var pxt_fac_part_commDAO = pxt_fac_part_commRepositoryCustom.findById(id);
		if (pxt_fac_part_commDAO.isPresent()) {
			return Pxt_fac_part_commMapperCustom.INSTANCE.pxt_fac_part_commDAOToPxt_fac_part_commDTOCustom(pxt_fac_part_commDAO.get());
		}
		return null;
	}

	@Override
	public Pxt_fac_part_commDTOCustom create(Pxt_fac_part_commDTOCustom pxt_fac_part_commDTOCustom) {
		var pxt_fac_part_commDAO = Pxt_fac_part_commMapperCustom.INSTANCE.pxt_fac_part_commDTOCustomToPxt_fac_part_commDAO(pxt_fac_part_commDTOCustom);
		Pxt_fac_part_commDAO newPxt_fac_part_comm = pxt_fac_part_commRepositoryCustom.save(pxt_fac_part_commDAO);
		return Pxt_fac_part_commMapperCustom.INSTANCE.pxt_fac_part_commDAOToPxt_fac_part_commDTOCustom(newPxt_fac_part_comm);
	}

	@Override
	public Pxt_fac_part_commDTOCustom update(Pxt_fac_part_commDTOCustom pxt_fac_part_commDTOCustom) {
		var pxt_fac_part_commDAO = pxt_fac_part_commRepositoryCustom.getById(pxt_fac_part_commDTOCustom.getFpcSysId());
		Pxt_fac_part_commMapperCustom.INSTANCE.assignValuesCustom(pxt_fac_part_commDTOCustom, pxt_fac_part_commDAO);
		Pxt_fac_part_commDAO newPxt_fac_part_comm = pxt_fac_part_commRepositoryCustom.save(pxt_fac_part_commDAO);
		return Pxt_fac_part_commMapperCustom.INSTANCE.pxt_fac_part_commDAOToPxt_fac_part_commDTOCustom(newPxt_fac_part_comm);
	}

	@Override
	public Boolean delete(Long id) {
		var pxt_fac_part_commDAO = pxt_fac_part_commRepositoryCustom.getById(id);
	//	pxt_fac_part_commDAO.setDeleted(true);
		pxt_fac_part_commRepositoryCustom.save(pxt_fac_part_commDAO);
		return true;
	}
}