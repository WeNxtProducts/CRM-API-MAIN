///*
// - Version Number 0.0.1
//*/
//
//package com.vi.extended.modules.users;
//
//import com.vi.model.dao.Pxt_fac_partDAO;
//import com.vi.model.dto.Pxt_fac_partDTOCustom;
//import org.springframework.beans.factory.annotation.Autowired;
//import java.util.List;
//
//public class Pxt_fac_partJPAAdapterCustom implements Pxt_fac_partPersistentCustom {
//	@Autowired
//	Pxt_fac_partRepositoryCustom pxt_fac_partRepositoryCustom;
//
//	@Override
//	public List<Pxt_fac_partDTOCustom> fetchAll() {
//		var pxt_fac_partDAOList = pxt_fac_partRepositoryCustom.findAll();
//		return Pxt_fac_partMapperCustom.INSTANCE.pxt_fac_partDAOListToPxt_fac_partDTOListCustom(pxt_fac_partDAOList);
//	}
//
//	@Override
//	public Pxt_fac_partDTOCustom get(Long id) {
//		var pxt_fac_partDAO = pxt_fac_partRepositoryCustom.findById(id);
//		if (pxt_fac_partDAO.isPresent()) {
//			return Pxt_fac_partMapperCustom.INSTANCE.pxt_fac_partDAOToPxt_fac_partDTOCustom(pxt_fac_partDAO.get());
//		}
//		return null;
//	}
//
//	@Override
//	public Pxt_fac_partDTOCustom create(Pxt_fac_partDTOCustom pxt_fac_partDTOCustom) {
//		var pxt_fac_partDAO = Pxt_fac_partMapperCustom.INSTANCE.pxt_fac_partDTOCustomToPxt_fac_partDAO(pxt_fac_partDTOCustom);
//		Pxt_fac_partDAO newPxt_fac_part = pxt_fac_partRepositoryCustom.save(pxt_fac_partDAO);
//		return Pxt_fac_partMapperCustom.INSTANCE.pxt_fac_partDAOToPxt_fac_partDTOCustom(newPxt_fac_part);
//	}
//
//	@Override
//	public Pxt_fac_partDTOCustom update(Pxt_fac_partDTOCustom pxt_fac_partDTOCustom) {
//		var pxt_fac_partDAO = pxt_fac_partRepositoryCustom.getById(pxt_fac_partDTOCustom.getFpSysId());
//		Pxt_fac_partMapperCustom.INSTANCE.assignValuesCustom(pxt_fac_partDTOCustom, pxt_fac_partDAO);
//		Pxt_fac_partDAO newPxt_fac_part = pxt_fac_partRepositoryCustom.save(pxt_fac_partDAO);
//		return Pxt_fac_partMapperCustom.INSTANCE.pxt_fac_partDAOToPxt_fac_partDTOCustom(newPxt_fac_part);
//	}
//
//	@Override
//	public Boolean delete(Long id) {
//		var pxt_fac_partDAO = pxt_fac_partRepositoryCustom.getById(id);
//	//	pxt_fac_partDAO.setDeleted(true);
//		pxt_fac_partRepositoryCustom.save(pxt_fac_partDAO);
//		return true;
//	}
//}
package com.vi.extended.modules.activitys;

