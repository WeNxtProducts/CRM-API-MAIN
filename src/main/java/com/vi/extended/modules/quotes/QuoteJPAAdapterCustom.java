/*
 - Version Number 0.0.1
*/

package com.vi.extended.modules.quotes;

import com.vi.base.modules.quotes.QuoteMapper;
import com.vi.model.dao.QuoteDAO;
import com.vi.model.dto.QuoteDTO;
import com.vi.model.dto.QuoteDTOCustom;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

public class QuoteJPAAdapterCustom implements QuotePersistentCustom {
	@Autowired
	QuoteRepositoryCustom quoteRepositoryCustom;

	@Override
	public List<QuoteDTOCustom> fetchAll() {
		var quoteDAOList = quoteRepositoryCustom.findAll();
		return QuoteMapperCustom.INSTANCE.quoteDAOListToQuoteDTOListCustom(quoteDAOList);
	}

	@Override
	public QuoteDTOCustom get(Long id) {
		var quoteDAO = quoteRepositoryCustom.findById(id);
		if (quoteDAO.isPresent()) {
			return QuoteMapperCustom.INSTANCE.quoteDAOToQuoteDTOCustom(quoteDAO.get());
		}
		return null;
	}

	@Override
	public QuoteDTOCustom create(QuoteDTOCustom quoteDTOCustom) {
		var quoteDAO = QuoteMapperCustom.INSTANCE.quoteDTOCustomToQuoteDAO(quoteDTOCustom);
		QuoteDAO newQuote = quoteRepositoryCustom.save(quoteDAO);
		return QuoteMapperCustom.INSTANCE.quoteDAOToQuoteDTOCustom(newQuote);
	}

	//updated
	@Override
	public QuoteDTOCustom update(QuoteDTOCustom quoteDTOCustom) {
		var quoteDAO = quoteRepositoryCustom.findById(quoteDTOCustom.getQuoteSeqNo())
				 .orElseThrow(() -> new EntityNotFoundException("Record not found with id: " + quoteDTOCustom.getQuoteSeqNo()));
		var oldData = QuoteMapperCustom.INSTANCE.quoteDAOToQuoteDTOCustom(quoteDAO);
		QuoteMapperCustom.INSTANCE.assignValuesCustom(quoteDTOCustom, quoteDAO);
		QuoteDAO newQuote = quoteRepositoryCustom.save(quoteDAO);
		return QuoteMapperCustom.INSTANCE.quoteDAOToQuoteDTOCustom(newQuote);
	}
	

	@Override
	public Boolean delete(Long id) {
		var quoteDAO = quoteRepositoryCustom.getById(id);
	//	quoteDAO.setDeleted(true);
		quoteRepositoryCustom.save(quoteDAO);
		return true;
	}
}
