/*
 - Version Number 0.0.1
*/

package com.vi.extended.modules.enquirys;

import com.vi.base.modules.enquirys.EnquiryMapper;
import com.vi.model.dao.EnquiryDAO;
import com.vi.model.dto.EnquiryDTO;
import com.vi.model.dto.EnquiryDTOCustom;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

public class EnquiryJPAAdapterCustom implements EnquiryPersistentCustom {
	@Autowired
	EnquiryRepositoryCustom enquiryRepositoryCustom;

	@Override
	public List<EnquiryDTOCustom> fetchAll() {
		var enquiryDAOList = enquiryRepositoryCustom.findAll();
		return EnquiryMapperCustom.INSTANCE.enquiryDAOListToEnquiryDTOListCustom(enquiryDAOList);
	}

	@Override
	public EnquiryDTOCustom get(Long id) {
		var enquiryDAO = enquiryRepositoryCustom.findById(id);
		if (enquiryDAO.isPresent()) {
			return EnquiryMapperCustom.INSTANCE.enquiryDAOToEnquiryDTOCustom(enquiryDAO.get());
		}
		return null;
	}

	@Override
	public EnquiryDTOCustom create(EnquiryDTOCustom enquiryDTOCustom) {
		var enquiryDAO = EnquiryMapperCustom.INSTANCE.enquiryDTOCustomToEnquiryDAO(enquiryDTOCustom);
		EnquiryDAO newEnquiry = enquiryRepositoryCustom.save(enquiryDAO);
		return EnquiryMapperCustom.INSTANCE.enquiryDAOToEnquiryDTOCustom(newEnquiry);
	}

	//updated
	@Override
	public EnquiryDTOCustom update(EnquiryDTOCustom enquiryDTOCustom) {
		var enquiryDAO = enquiryRepositoryCustom.findById(enquiryDTOCustom.getEnqSeqNo())
				 .orElseThrow(() -> new EntityNotFoundException("Record not found with id: " + enquiryDTOCustom.getEnqSeqNo()));
		var oldData = EnquiryMapperCustom.INSTANCE.enquiryDAOToEnquiryDTOCustom(enquiryDAO);
		EnquiryMapperCustom.INSTANCE.assignValuesCustom(enquiryDTOCustom, enquiryDAO);
		EnquiryDAO newEnquiry = enquiryRepositoryCustom.save(enquiryDAO);
		return EnquiryMapperCustom.INSTANCE.enquiryDAOToEnquiryDTOCustom(newEnquiry);
	}
	

	@Override
	public Boolean delete(Long id) {
		var enquiryDAO = enquiryRepositoryCustom.getById(id);
	//	enquiryDAO.setDeleted(true);
		enquiryRepositoryCustom.save(enquiryDAO);
		return true;
	}
}
