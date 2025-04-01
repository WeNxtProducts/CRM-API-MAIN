/*
 - Version Number 0.0.1
*/

package com.vi.base.modules.conversations;

import com.vi.model.dao.ConversationDAO;
import com.vi.model.dto.ConversationDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ConversationMapper {

	ConversationMapper INSTANCE = Mappers.getMapper(ConversationMapper.class);

	ConversationDTO conversationDAOToConversationDTO(ConversationDAO conversationDAO);

	ConversationDAO conversationDTOToConversationDAO(ConversationDTO conversationDTO);

	List<ConversationDTO> conversationDAOListToConversationDTOList(List<ConversationDAO> conversationDAOList);

	List<ConversationDAO> conversationDTOListToConversationDAOList(List<ConversationDTO> conversationDTOList);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	void assignValues(ConversationDTO dto, @MappingTarget ConversationDAO entity);
}