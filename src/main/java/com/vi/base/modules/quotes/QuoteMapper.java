/*
 - Version Number 0.0.1
*/

package com.vi.base.modules.quotes;

import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import com.vi.model.dao.QuoteDAO;
import com.vi.model.dto.QuoteDTO;

@Mapper(componentModel = "spring")
public interface QuoteMapper {

	QuoteMapper INSTANCE = Mappers.getMapper(QuoteMapper.class);

	QuoteDTO quoteDAOToQuoteDTO(QuoteDAO quoteDAO);

	QuoteDAO quoteDTOToQuoteDAO(QuoteDTO quoteDTO);

	List<QuoteDTO> quoteDAOListToQuoteDTOList(List<QuoteDAO> quoteDAOList);

	List<QuoteDAO> quoteDTOListToQuoteDAOList(List<QuoteDTO> quoteDTOList);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	void assignValues(QuoteDTO dto, @MappingTarget QuoteDAO entity);
}