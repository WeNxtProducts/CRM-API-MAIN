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
	
//	@Autowired
//	private EnquiryRepositoryCustom enquiryRepositoryCustom;
//
//	@Transactional
//	public static EnquiryDTOCustom updateRemainderCount(Long enquirySeqNo) {
//		var enquiryDAO = enquiryRepositoryCustom.findById(enquiryDTOCustom.getEnquirySeqNo())
//				 .orElseThrow(() -> new EntityNotFoundException("Record not found with id: " + enquiryDTOCustom.getEnquirySeqNo()));
//		
//	    System.out.println("Before Increment: " + enquiry.getRemainderCount());
//	    enquiryDTOCustom.setRemainderCount(enquiryDTOCustom.getRemainderCount() + 1); // Increment count
//	    System.out.println("After Increment: " + enquiryDTOCustom.getRemainderCount());
//
//	    enquiryRepositoryCustom.save(enquiryDTOCustom);
//	    return new EnquiryDTOCustom(enquiry);
//	}

}
