/*
 - Version Number 0.0.1
*/

package com.vi.extended.modules.conversations;

import com.vi.base.modules.conversations.ConversationMapper;
import com.vi.model.dao.ConversationDAO;
import com.vi.model.dto.ConversationDTO;
import com.vi.model.dto.ConversationDTOCustom;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

public class ConversationJPAAdapterCustom implements ConversationPersistentCustom {
	@Autowired
	ConversationRepositoryCustom conversationRepositoryCustom;

	@Override
	public List<ConversationDTOCustom> fetchAll() {
		var conversationDAOList = conversationRepositoryCustom.findAll();
		return ConversationMapperCustom.INSTANCE.conversationDAOListToConversationDTOListCustom(conversationDAOList);
	}

	@Override
	public ConversationDTOCustom get(Long id) {
		var conversationDAO = conversationRepositoryCustom.findById(id);
		if (conversationDAO.isPresent()) {
			return ConversationMapperCustom.INSTANCE.conversationDAOToConversationDTOCustom(conversationDAO.get());
		}
		return null;
	}

	@Override
	public ConversationDTOCustom create(ConversationDTOCustom conversationDTOCustom) {
		var conversationDAO = ConversationMapperCustom.INSTANCE.conversationDTOCustomToConversationDAO(conversationDTOCustom);
		ConversationDAO newConversation = conversationRepositoryCustom.save(conversationDAO);
		return ConversationMapperCustom.INSTANCE.conversationDAOToConversationDTOCustom(newConversation);
	}

	//updated
	@Override
	public ConversationDTOCustom update(ConversationDTOCustom conversationDTOCustom) {
		var conversationDAO = conversationRepositoryCustom.findById(conversationDTOCustom.getConversationSeqNo())
				 .orElseThrow(() -> new EntityNotFoundException("Record not found with id: " + conversationDTOCustom.getConversationSeqNo()));
		var oldData = ConversationMapperCustom.INSTANCE.conversationDAOToConversationDTOCustom(conversationDAO);
		ConversationMapperCustom.INSTANCE.assignValuesCustom(conversationDTOCustom, conversationDAO);
		ConversationDAO newConversation = conversationRepositoryCustom.save(conversationDAO);
		return ConversationMapperCustom.INSTANCE.conversationDAOToConversationDTOCustom(newConversation);
	}
	

	@Override
	public Boolean delete(Long id) {
		var conversationDAO = conversationRepositoryCustom.getById(id);
	//	conversationDAO.setDeleted(true);
		conversationRepositoryCustom.save(conversationDAO);
		return true;
	}
}
