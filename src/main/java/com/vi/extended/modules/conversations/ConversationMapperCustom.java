/*
 - Version Number 0.0.1
*/

package com.vi.extended.modules.conversations;

import com.vi.model.dao.ConversationDAO;
import com.vi.model.dto.ConversationDTOCustom;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ConversationMapperCustom {

	ConversationMapperCustom INSTANCE = Mappers.getMapper(ConversationMapperCustom.class);

	ConversationDTOCustom conversationDAOToConversationDTOCustom(ConversationDAO conversationDAO);

	ConversationDAO conversationDTOCustomToConversationDAO(ConversationDTOCustom conversationDTOCustom);

	List<ConversationDTOCustom> conversationDAOListToConversationDTOListCustom(List<ConversationDAO> conversationDAOList);

	List<ConversationDAO> conversationDTOListCustomToConversationDAOList(List<ConversationDTOCustom> conversationDTOCustomList);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	void assignValuesCustom(ConversationDTOCustom dto, @MappingTarget ConversationDAO entity);

}
