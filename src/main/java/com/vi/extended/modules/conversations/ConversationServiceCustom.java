/*
 - Version Number 0.0.1
*/

package com.vi.extended.modules.conversations;

import org.springframework.beans.factory.annotation.Autowired;

import com.vi.corelib.base.BaseService;
import com.vi.model.dao.ConversationDAO;
import com.vi.model.dto.ConversationDTOCustom;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

public interface ConversationServiceCustom extends BaseService<ConversationDTOCustom> {
	
//	@Autowired
//	private ConversationRepositoryCustom conversationRepositoryCustom;
//
//	@Transactional
//	public static ConversationDTOCustom updateRemainderCount(Long conversationSeqNo) {
//		var conversationDAO = conversationRepositoryCustom.findById(conversationDTOCustom.getConversationSeqNo())
//				 .orElseThrow(() -> new EntityNotFoundException("Record not found with id: " + conversationDTOCustom.getConversationSeqNo()));
//		
//	    System.out.println("Before Increment: " + conversation.getRemainderCount());
//	    conversationDTOCustom.setRemainderCount(conversationDTOCustom.getRemainderCount() + 1); // Increment count
//	    System.out.println("After Increment: " + conversationDTOCustom.getRemainderCount());
//
//	    conversationRepositoryCustom.save(conversationDTOCustom);
//	    return new ConversationDTOCustom(conversation);
//	}

}
