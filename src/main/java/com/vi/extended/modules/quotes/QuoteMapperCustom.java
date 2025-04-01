/*
 - Version Number 0.0.1
*/

package com.vi.extended.modules.quotes;

import com.vi.model.dao.QuoteDAO;
import com.vi.model.dto.QuoteDTOCustom;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import java.util.List;

@Mapper(componentModel = "spring")
public interface QuoteMapperCustom {

	QuoteMapperCustom INSTANCE = Mappers.getMapper(QuoteMapperCustom.class);

	QuoteDTOCustom quoteDAOToQuoteDTOCustom(QuoteDAO quoteDAO);

	QuoteDAO quoteDTOCustomToQuoteDAO(QuoteDTOCustom quoteDTOCustom);

	List<QuoteDTOCustom> quoteDAOListToQuoteDTOListCustom(List<QuoteDAO> quoteDAOList);

	List<QuoteDAO> quoteDTOListCustomToQuoteDAOList(List<QuoteDTOCustom> quoteDTOCustomList);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	void assignValuesCustom(QuoteDTOCustom dto, @MappingTarget QuoteDAO entity);

}
