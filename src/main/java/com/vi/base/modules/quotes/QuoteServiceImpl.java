/*
 - Version Number 0.0.1
*/

package com.vi.base.modules.quotes;

import com.fasterxml.jackson.databind.JsonNode;
import com.vi.model.dto.QuoteDTO;
import java.util.List;

public class QuoteServiceImpl implements QuoteService {

	private QuotePersistent quotePersistent;

	public QuoteServiceImpl(QuotePersistent quotePersistent) {
		this.quotePersistent = quotePersistent;
	}

	@Override
	public List<QuoteDTO> fetchAll() {
		return quotePersistent.fetchAll();
	}

	@Override
	public QuoteDTO get(Long id) {
		return quotePersistent.get(id);
	}

	@Override
	public QuoteDTO create(QuoteDTO quoteDTO) {
		return quotePersistent.create(quoteDTO);
	}

	@Override
	public QuoteDTO update(QuoteDTO quoteDTO) {
		return quotePersistent.update(quoteDTO);
	}

	@Override
	public Boolean delete(Long id) {
		return quotePersistent.delete(id);
	}

	@Override
	public List<QuoteDTO> filterData(String search) {
		return quotePersistent.filterData(search);
	}

	@Override
	public List<QuoteDTO> filterData(JsonNode search, int page, int size) {
		return quotePersistent.filterData(search, page, size);
	}
}