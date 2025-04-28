/*
 - Version Number 0.0.1
*/

package com.vi.extended.modules.enquirys;

import org.springframework.beans.factory.annotation.Autowired;

import com.vi.corelib.base.BaseService;
import com.vi.model.dao.EnquiryDAO;
import com.vi.model.dto.EnquiryDTOCustom;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

public interface EnquiryServiceCustom extends BaseService<EnquiryDTOCustom> {
	


}
