/*
 - Version Number 0.0.1
*/

package com.vi.extended.modules.leads;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.vi.model.dto.LeadDTOCustom;

public class LeadServiceImplCustom implements LeadServiceCustom {

	private LeadPersistentCustom leadpersistentCustom;

	public LeadServiceImplCustom(LeadPersistentCustom leadpersistentCustom) {
		this.leadpersistentCustom = leadpersistentCustom;
	}

	@Override
	public List<LeadDTOCustom> fetchAll() {
		return leadpersistentCustom.fetchAll();
	}

	@Override
	public LeadDTOCustom get(Long id) {
		return leadpersistentCustom.get(id);
	}

	@Override
	public LeadDTOCustom create(LeadDTOCustom leadDTOCustom) {
		return leadpersistentCustom.create(leadDTOCustom);
	}

	@Override
	public LeadDTOCustom update(LeadDTOCustom leadDTOCustom) {
		return leadpersistentCustom.update(leadDTOCustom);
	}

	@Override
	public Boolean delete(Long id) {
		return leadpersistentCustom.delete(id);
	}
		
}