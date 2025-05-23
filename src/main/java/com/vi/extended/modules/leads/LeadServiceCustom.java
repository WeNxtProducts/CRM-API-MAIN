/*
 - Version Number 0.0.1
*/

package com.vi.extended.modules.leads;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.vi.corelib.base.BaseService;
import com.vi.model.dao.LeadDAO;
import com.vi.model.dto.LeadDTOCustom;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

public interface LeadServiceCustom extends BaseService<LeadDTOCustom> {
	
	 
}
