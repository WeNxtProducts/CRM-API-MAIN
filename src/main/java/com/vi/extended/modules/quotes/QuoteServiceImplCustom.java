/*
 - Version Number 0.0.1
*/

package com.vi.extended.modules.quotes;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vi.model.dto.QuoteDTOCustom;

public class QuoteServiceImplCustom implements QuoteServiceCustom {

	private QuotePersistentCustom quotepersistentCustom;

	public QuoteServiceImplCustom(QuotePersistentCustom quotepersistentCustom) {
		this.quotepersistentCustom = quotepersistentCustom;
	}

	@Override
	public List<QuoteDTOCustom> fetchAll() {
		return quotepersistentCustom.fetchAll();
	}

	@Override
	public QuoteDTOCustom get(Long id) {
		return quotepersistentCustom.get(id);
	}

	@Override
	public QuoteDTOCustom create(QuoteDTOCustom quoteDTOCustom) {
		return quotepersistentCustom.create(quoteDTOCustom);
	}

	@Override
	public QuoteDTOCustom update(QuoteDTOCustom quoteDTOCustom) {
		return quotepersistentCustom.update(quoteDTOCustom);
	}

	@Override
	public Boolean delete(Long id) {
		return quotepersistentCustom.delete(id);
	}
}