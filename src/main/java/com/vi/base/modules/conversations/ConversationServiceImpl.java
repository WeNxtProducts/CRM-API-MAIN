/*
 - Version Number 0.0.1
*/

package com.vi.base.modules.conversations;

import com.fasterxml.jackson.databind.JsonNode;
import com.vi.model.dto.ConversationDTO;
import java.util.List;

public class ConversationServiceImpl implements ConversationService {

	private ConversationPersistent conversationPersistent;

	public ConversationServiceImpl(ConversationPersistent conversationPersistent) {
		this.conversationPersistent = conversationPersistent;
	}

	@Override
	public List<ConversationDTO> fetchAll() {
		return conversationPersistent.fetchAll();
	}

	@Override
	public ConversationDTO get(Long id) {
		return conversationPersistent.get(id);
	}

	@Override
	public ConversationDTO create(ConversationDTO conversationDTO) {
		return conversationPersistent.create(conversationDTO);
	}

	@Override
	public ConversationDTO update(ConversationDTO conversationDTO) {
		return conversationPersistent.update(conversationDTO);
	}

	@Override
	public Boolean delete(Long id) {
		return conversationPersistent.delete(id);
	}

	@Override
	public List<ConversationDTO> filterData(String search) {
		return conversationPersistent.filterData(search);
	}

	@Override
	public List<ConversationDTO> filterData(JsonNode search, int page, int size) {
		return conversationPersistent.filterData(search, page, size);
	}
	
	@Override
	public List<ConversationDTO> filterData(JsonNode search) {
		return conversationPersistent.filterData(search);
	}
}