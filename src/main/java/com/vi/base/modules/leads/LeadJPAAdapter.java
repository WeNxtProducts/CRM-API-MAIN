/*
 - Version Number 0.0.1
*/

package com.vi.base.modules.leads;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import com.fasterxml.jackson.databind.JsonNode;
//import com.vi.corelib.events.EventPublisher;
import com.vi.corelib.filter.FilterSpecificationsBuilder;
import com.vi.model.dao.LeadDAO;
import com.vi.model.dto.LeadDTO;

import jakarta.persistence.EntityNotFoundException;
import lombok.SneakyThrows;


public class LeadJPAAdapter implements LeadPersistent {
	@Autowired
	LeadRepository leadRepository;

	@Override
	public List<LeadDTO> fetchAll() {
	    List<LeadDAO> leadDAOList = leadRepository.findAll(Sort.by(Sort.Order.desc("leadSeqNo")));
	    return LeadMapper.INSTANCE.leadDAOListToLeadDTOList(leadDAOList);
	}

	@Override
	public LeadDTO get(Long id) {
		var leadDAO = leadRepository.findById(id);
		if(leadDAO.isPresent()) {
			return LeadMapper.INSTANCE.leadDAOToLeadDTO(leadDAO.get());
		}
		return null;
	}

  @SneakyThrows
	@Override
	public LeadDTO create(LeadDTO leadDTO) {
		var leadDAO = LeadMapper.INSTANCE.leadDTOToLeadDAO(leadDTO);
		var newLead = leadRepository.save(leadDAO);
		var newData = LeadMapper.INSTANCE.leadDAOToLeadDTO(newLead);
		
		
		return newData;
	}

	@Override
 		public LeadDTO update(LeadDTO leadDTO) {
			
		 var leadDAO = leadRepository.findById(leadDTO.getLeadSeqNo())
        .orElseThrow(() -> new EntityNotFoundException("Record not found with id: " + leadDTO.getLeadSeqNo()));
		var oldData = LeadMapper.INSTANCE.leadDAOToLeadDTO(leadDAO);
		LeadMapper.INSTANCE.assignValues(leadDTO, leadDAO);
		var newLead = leadRepository.save(leadDAO);
		var newData = LeadMapper.INSTANCE.leadDAOToLeadDTO(newLead); 
		
		return newData;   
	}

	@SneakyThrows
	@Override
	public Boolean delete(Long id) {
	    var leadDAO = leadRepository.findById(id)
	                     .orElseThrow(() -> new RuntimeException("Lead not found with id: " + id));
	    leadDAO.setDeleted(true); 
	    leadRepository.save(leadDAO); 
	    return true;  
	}

	@Override
	public List<LeadDTO> filterData(String search) {
		Specification<LeadDAO> result = new FilterSpecificationsBuilder<LeadDAO>().with(search).build();
		return LeadMapper.INSTANCE.leadDAOListToLeadDTOList(leadRepository.findAll(result));
	}
	
	@Override
	public List<LeadDTO> filterData(JsonNode search, int page, int size) {
		Pageable pageable = PageRequest.of(page, size, Sort.by("leadSeqNo").descending());
    Specification<LeadDAO> result = new FilterSpecificationsBuilder<LeadDAO>().with(search).build();
		return LeadMapper.INSTANCE.leadDAOListToLeadDTOList(leadRepository.findAll(result, pageable).getContent());
	}
	
	@Override
	public List<LeadDTO> filterData(JsonNode search) {
    Specification<LeadDAO> result = new FilterSpecificationsBuilder<LeadDAO>().with(search).build();
		return LeadMapper.INSTANCE.leadDAOListToLeadDTOList(leadRepository.findAll(result));
	}
}
