/*
 - Version Number 0.0.1
*/

package com.vi.extended.modules.enquirys;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vi.model.dto.EnquiryDTOCustom;

public class EnquiryServiceImplCustom implements EnquiryServiceCustom {

	private EnquiryPersistentCustom enquirypersistentCustom;

	public EnquiryServiceImplCustom(EnquiryPersistentCustom enquirypersistentCustom) {
		this.enquirypersistentCustom = enquirypersistentCustom;
	}

	@Override
	public List<EnquiryDTOCustom> fetchAll() {
		return enquirypersistentCustom.fetchAll();
	}

	@Override
	public EnquiryDTOCustom get(Long id) {
		return enquirypersistentCustom.get(id);
	}

	@Override
	public EnquiryDTOCustom create(EnquiryDTOCustom enquiryDTOCustom) {
		return enquirypersistentCustom.create(enquiryDTOCustom);
	}

	@Override
	public EnquiryDTOCustom update(EnquiryDTOCustom enquiryDTOCustom) {
		return enquirypersistentCustom.update(enquiryDTOCustom);
	}

	@Override
	public Boolean delete(Long id) {
		return enquirypersistentCustom.delete(id);
	}
}