/*
 - Version Number 0.0.1
*/

package com.vi.extended.modules.conversations;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vi.model.dto.ConversationDTOCustom;

public class ConversationServiceImplCustom implements ConversationServiceCustom {

	private ConversationPersistentCustom conversationpersistentCustom;

	public ConversationServiceImplCustom(ConversationPersistentCustom conversationpersistentCustom) {
		this.conversationpersistentCustom = conversationpersistentCustom;
	}

	@Override
	public List<ConversationDTOCustom> fetchAll() {
		return conversationpersistentCustom.fetchAll();
	}

	@Override
	public ConversationDTOCustom get(Long id) {
		return conversationpersistentCustom.get(id);
	}

	@Override
	public ConversationDTOCustom create(ConversationDTOCustom conversationDTOCustom) {
		return conversationpersistentCustom.create(conversationDTOCustom);
	}

	@Override
	public ConversationDTOCustom update(ConversationDTOCustom conversationDTOCustom) {
		return conversationpersistentCustom.update(conversationDTOCustom);
	}

	@Override
	public Boolean delete(Long id) {
		return conversationpersistentCustom.delete(id);
	}
}