/*
 - Version Number 0.0.1
*/

package com.vi.base.modules.conversations;

import com.fasterxml.jackson.databind.JsonNode;
import com.vi.model.dao.ConversationDAO;
import com.vi.model.dto.ConversationDTO;
import com.vi.model.dto.ConversationDTO;

import jakarta.persistence.EntityNotFoundException;

//import com.vi.corelib.events.EventPublisher;
import com.vi.corelib.filter.FilterSpecificationsBuilder;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import java.util.List;
import java.io.IOException;
import org.json.simple.parser.ParseException;


public class ConversationJPAAdapter implements ConversationPersistent {
	@Autowired
	ConversationRepository conversationRepository;

	@Override
	public List<ConversationDTO> fetchAll() {
		var conversationDAOList = conversationRepository.findAll();
		return ConversationMapper.INSTANCE.conversationDAOListToConversationDTOList(conversationDAOList);
	}

	@Override
	public ConversationDTO get(Long id) {
		var conversationDAO = conversationRepository.findById(id);
		if(conversationDAO.isPresent()) {
			return ConversationMapper.INSTANCE.conversationDAOToConversationDTO(conversationDAO.get());
		}
		return null;
	}

  @SneakyThrows
	@Override
	public ConversationDTO create(ConversationDTO conversationDTO) {
		var conversationDAO = ConversationMapper.INSTANCE.conversationDTOToConversationDAO(conversationDTO);
		var newConversation = conversationRepository.save(conversationDAO);
		var newData = ConversationMapper.INSTANCE.conversationDAOToConversationDTO(newConversation);
		
		
		return newData;
	}

	@Override
 		public ConversationDTO update(ConversationDTO conversationDTO) {
			
		 var conversationDAO = conversationRepository.findById(conversationDTO.getConversationSeqNo())
        .orElseThrow(() -> new EntityNotFoundException("Record not found with id: " + conversationDTO.getConversationSeqNo()));
		var oldData = ConversationMapper.INSTANCE.conversationDAOToConversationDTO(conversationDAO);
		ConversationMapper.INSTANCE.assignValues(conversationDTO, conversationDAO);
		var newConversation = conversationRepository.save(conversationDAO);
		var newData = ConversationMapper.INSTANCE.conversationDAOToConversationDTO(newConversation); 
		
		return newData;   
	}
	
	@SneakyThrows
	@Override
	public Boolean delete(Long id) {
	    var conversationDAO = conversationRepository.findById(id)
	                    .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

	    conversationDAO.setDeleted(true); // Set deleted flag to true
	    conversationRepository.save(conversationDAO); // Persist the change

	    return true; 
	}

	@Override
	public List<ConversationDTO> filterData(String search) {
		Specification<ConversationDAO> result = new FilterSpecificationsBuilder<ConversationDAO>().with(search).build();
		return ConversationMapper.INSTANCE.conversationDAOListToConversationDTOList(conversationRepository.findAll(result));
	}

	@Override
	public List<ConversationDTO> filterData(JsonNode search, int page, int size) {
		Pageable pageable = PageRequest.of(page, size, Sort.by("conversationSeqNo").ascending());
		Specification<ConversationDAO> result = new FilterSpecificationsBuilder<ConversationDAO>().with(search).build();
		return ConversationMapper.INSTANCE.conversationDAOListToConversationDTOList(conversationRepository.findAll(result, pageable).getContent());
	}
}
