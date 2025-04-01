/*
 - Version Number 0.0.1
*/

package com.vi.extended.modules.leads;

import com.vi.base.modules.leads.LeadMapper;
import com.vi.model.dao.LeadDAO;
import com.vi.model.dto.LeadDTO;
import com.vi.model.dto.LeadDTOCustom;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

public class LeadJPAAdapterCustom implements LeadPersistentCustom {
	@Autowired
	LeadRepositoryCustom leadRepositoryCustom;

	@Override
	public List<LeadDTOCustom> fetchAll() {
		var leadDAOList = leadRepositoryCustom.findAll();
		return LeadMapperCustom.INSTANCE.leadDAOListToLeadDTOListCustom(leadDAOList);
	}

	@Override
	public LeadDTOCustom get(Long id) {
		var leadDAO = leadRepositoryCustom.findById(id);
		if (leadDAO.isPresent()) {
			return LeadMapperCustom.INSTANCE.leadDAOToLeadDTOCustom(leadDAO.get());
		}
		return null;
	}

	@Override
	public LeadDTOCustom create(LeadDTOCustom leadDTOCustom) {
		var leadDAO = LeadMapperCustom.INSTANCE.leadDTOCustomToLeadDAO(leadDTOCustom);
		LeadDAO newLead = leadRepositoryCustom.save(leadDAO);
		return LeadMapperCustom.INSTANCE.leadDAOToLeadDTOCustom(newLead);
	}

	//updated
	@Override
	public LeadDTOCustom update(LeadDTOCustom leadDTOCustom) {
		var leadDAO = leadRepositoryCustom.findById(leadDTOCustom.getLeadSeqNo())
				 .orElseThrow(() -> new EntityNotFoundException("Record not found with id: " + leadDTOCustom.getLeadSeqNo()));
		var oldData = LeadMapperCustom.INSTANCE.leadDAOToLeadDTOCustom(leadDAO);
		LeadMapperCustom.INSTANCE.assignValuesCustom(leadDTOCustom, leadDAO);
		LeadDAO newLead = leadRepositoryCustom.save(leadDAO);
		return LeadMapperCustom.INSTANCE.leadDAOToLeadDTOCustom(newLead);
	}
	

	@Override
	public Boolean delete(Long id) {
		var leadDAO = leadRepositoryCustom.getById(id);
	//	leadDAO.setDeleted(true);
		leadRepositoryCustom.save(leadDAO);
		return true;
	}
}
