/*
 - Version Number 0.0.1
*/

package com.vi.base.modules.quotes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import com.fasterxml.jackson.databind.JsonNode;
//import com.vi.corelib.events.EventPublisher;
import com.vi.corelib.filter.FilterSpecificationsBuilder;
import com.vi.model.dao.QuoteDAO;
import com.vi.model.dto.QuoteDTO;

import jakarta.persistence.EntityNotFoundException;
import lombok.SneakyThrows;


public class QuoteJPAAdapter implements QuotePersistent {
	@Autowired
	QuoteRepository quoteRepository;

	@Override
	public List<QuoteDTO> fetchAll() {
	    List<QuoteDAO> quoteDAOList = quoteRepository.findAll(Sort.by(Sort.Order.desc("quoteSeqNo")));
	    return QuoteMapper.INSTANCE.quoteDAOListToQuoteDTOList(quoteDAOList);
	}

	@Override
	public QuoteDTO get(Long id) {
		var quoteDAO = quoteRepository.findById(id);
		if(quoteDAO.isPresent()) {
			return QuoteMapper.INSTANCE.quoteDAOToQuoteDTO(quoteDAO.get());
		}
		return null;
	}

  @SneakyThrows
	@Override
	public QuoteDTO create(QuoteDTO quoteDTO) {
		var quoteDAO = QuoteMapper.INSTANCE.quoteDTOToQuoteDAO(quoteDTO);
		var newQuote = quoteRepository.save(quoteDAO);
		var newData = QuoteMapper.INSTANCE.quoteDAOToQuoteDTO(newQuote);
		
		
		return newData;
	}

	@Override
 		public QuoteDTO update(QuoteDTO quoteDTO) {
			
		 var quoteDAO = quoteRepository.findById(quoteDTO.getQuoteSeqNo())
        .orElseThrow(() -> new EntityNotFoundException("Record not found with id: " + quoteDTO.getQuoteSeqNo()));
		var oldData = QuoteMapper.INSTANCE.quoteDAOToQuoteDTO(quoteDAO);
		QuoteMapper.INSTANCE.assignValues(quoteDTO, quoteDAO);
		var newQuote = quoteRepository.save(quoteDAO);
		var newData = QuoteMapper.INSTANCE.quoteDAOToQuoteDTO(newQuote); 
		
		return newData;   
	}

	@SneakyThrows
	@Override
	public Boolean delete(Long id) {
	    var quoteDAO = quoteRepository.findById(id)
	                     .orElseThrow(() -> new RuntimeException("Quote not found with id: " + id));
	    quoteDAO.setDeleted(true); 
	    quoteRepository.save(quoteDAO); 
	    return true;  
	}

	@Override
	public List<QuoteDTO> filterData(String search) {
		Specification<QuoteDAO> result = new FilterSpecificationsBuilder<QuoteDAO>().with(search).build();
		return QuoteMapper.INSTANCE.quoteDAOListToQuoteDTOList(quoteRepository.findAll(result));
	}
	
	@Override
	public List<QuoteDTO> filterData(JsonNode search, int page, int size) {
		Pageable pageable = PageRequest.of(page, size, Sort.by("quoteSeqNo").descending());
    Specification<QuoteDAO> result = new FilterSpecificationsBuilder<QuoteDAO>().with(search).build();
		return QuoteMapper.INSTANCE.quoteDAOListToQuoteDTOList(quoteRepository.findAll(result, pageable).getContent());
	}
}
