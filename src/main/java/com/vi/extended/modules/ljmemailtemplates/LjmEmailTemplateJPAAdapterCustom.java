/*
 - Version Number 0.0.1
*/

package com.vi.extended.modules.ljmemailtemplates;

import com.vi.model.dao.LjmEmailTemplateDAO;
import com.vi.model.dto.LjmEmailTemplateDTO;
import com.vi.model.dto.LjmEmailTemplateDTOCustom;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

public class LjmEmailTemplateJPAAdapterCustom implements LjmEmailTemplatePersistentCustom {
	@Autowired
	LjmEmailTemplateRepositoryCustom ljmEmailTemplateRepositoryCustom;

	@Override
	public List<LjmEmailTemplateDTOCustom> fetchAll() {
		var ljmEmailTemplateDAOList = ljmEmailTemplateRepositoryCustom.findAll();
		return LjmEmailTemplateMapperCustom.INSTANCE.ljmEmailTemplateDAOListToLjmEmailTemplateDTOListCustom(ljmEmailTemplateDAOList);
	}

	@Override
	public LjmEmailTemplateDTOCustom get(Long id) {
		var ljmEmailTemplateDAO = ljmEmailTemplateRepositoryCustom.findById(id);
		if (ljmEmailTemplateDAO.isPresent()) {
			return LjmEmailTemplateMapperCustom.INSTANCE.ljmEmailTemplateDAOToLjmEmailTemplateDTOCustom(ljmEmailTemplateDAO.get());
		}
		return null;
	}

	@Override
	public LjmEmailTemplateDTOCustom create(LjmEmailTemplateDTOCustom ljmEmailTemplateDTOCustom) {
		var ljmEmailTemplateDAO = LjmEmailTemplateMapperCustom.INSTANCE.ljmEmailTemplateDTOCustomToLjmEmailTemplateDAO(ljmEmailTemplateDTOCustom);
		LjmEmailTemplateDAO newLjmEmailTemplate = ljmEmailTemplateRepositoryCustom.save(ljmEmailTemplateDAO);
		return LjmEmailTemplateMapperCustom.INSTANCE.ljmEmailTemplateDAOToLjmEmailTemplateDTOCustom(newLjmEmailTemplate);
	}

	//updated
	@Override
	public LjmEmailTemplateDTOCustom update(LjmEmailTemplateDTOCustom ljmEmailTemplateDTOCustom) {
		var ljmEmailTemplateDAO = ljmEmailTemplateRepositoryCustom.findById(ljmEmailTemplateDTOCustom.getEtSysId())
				 .orElseThrow(() -> new EntityNotFoundException("Record not found with id: " + ljmEmailTemplateDTOCustom.getEtSysId()));
		var oldData = LjmEmailTemplateMapperCustom.INSTANCE.ljmEmailTemplateDAOToLjmEmailTemplateDTOCustom(ljmEmailTemplateDAO);
		LjmEmailTemplateMapperCustom.INSTANCE.assignValuesCustom(ljmEmailTemplateDTOCustom, ljmEmailTemplateDAO);
		LjmEmailTemplateDAO newLjmEmailTemplate = ljmEmailTemplateRepositoryCustom.save(ljmEmailTemplateDAO);
		return LjmEmailTemplateMapperCustom.INSTANCE.ljmEmailTemplateDAOToLjmEmailTemplateDTOCustom(newLjmEmailTemplate);
	}
	

	@Override
	public Boolean delete(Long id) {
		var ljmEmailTemplateDAO = ljmEmailTemplateRepositoryCustom.getById(id);
	//	ljmEmailTemplateDAO.setDeleted(true);
		ljmEmailTemplateRepositoryCustom.save(ljmEmailTemplateDAO);
		return true;
	}
}
