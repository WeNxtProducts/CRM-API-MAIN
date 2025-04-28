/*
 - Version Number 0.0.1
*/

package com.vi.base.modules.ljmemailtemplates;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import com.fasterxml.jackson.databind.JsonNode;
//import com.vi.corelib.events.EventPublisher;
import com.vi.corelib.filter.FilterSpecificationsBuilder;
import com.vi.model.dao.LjmEmailTemplateDAO;
import com.vi.model.dto.LjmEmailTemplateDTO;

import jakarta.persistence.EntityNotFoundException;
import lombok.SneakyThrows;


public class LjmEmailTemplateJPAAdapter implements LjmEmailTemplatePersistent {
	@Autowired
	LjmEmailTemplateRepository ljmEmailTemplateRepository;

	@Override
	public List<LjmEmailTemplateDTO> fetchAll() {
	    List<LjmEmailTemplateDAO> ljmEmailTemplateDAOList = ljmEmailTemplateRepository.findAll(Sort.by(Sort.Order.desc("etSysId")));
	    return LjmEmailTemplateMapper.INSTANCE.ljmEmailTemplateDAOListToLjmEmailTemplateDTOList(ljmEmailTemplateDAOList);
	}

	@Override
	public LjmEmailTemplateDTO get(Long id) {
		var ljmEmailTemplateDAO = ljmEmailTemplateRepository.findById(id);
		if(ljmEmailTemplateDAO.isPresent()) {
			return LjmEmailTemplateMapper.INSTANCE.ljmEmailTemplateDAOToLjmEmailTemplateDTO(ljmEmailTemplateDAO.get());
		}
		return null;
	}

  @SneakyThrows
	@Override
	public LjmEmailTemplateDTO create(LjmEmailTemplateDTO ljmEmailTemplateDTO) {
		var ljmEmailTemplateDAO = LjmEmailTemplateMapper.INSTANCE.ljmEmailTemplateDTOToLjmEmailTemplateDAO(ljmEmailTemplateDTO);
		var newLjmEmailTemplate = ljmEmailTemplateRepository.save(ljmEmailTemplateDAO);
		var newData = LjmEmailTemplateMapper.INSTANCE.ljmEmailTemplateDAOToLjmEmailTemplateDTO(newLjmEmailTemplate);
		
		
		return newData;
	}

	@Override
 		public LjmEmailTemplateDTO update(LjmEmailTemplateDTO ljmEmailTemplateDTO) {
			
		 var ljmEmailTemplateDAO = ljmEmailTemplateRepository.findById(ljmEmailTemplateDTO.getEtSysId())
        .orElseThrow(() -> new EntityNotFoundException("Record not found with id: " + ljmEmailTemplateDTO.getEtSysId()));
		var oldData = LjmEmailTemplateMapper.INSTANCE.ljmEmailTemplateDAOToLjmEmailTemplateDTO(ljmEmailTemplateDAO);
		LjmEmailTemplateMapper.INSTANCE.assignValues(ljmEmailTemplateDTO, ljmEmailTemplateDAO);
		var newLjmEmailTemplate = ljmEmailTemplateRepository.save(ljmEmailTemplateDAO);
		var newData = LjmEmailTemplateMapper.INSTANCE.ljmEmailTemplateDAOToLjmEmailTemplateDTO(newLjmEmailTemplate); 
		
		return newData;   
	}

	@SneakyThrows
	@Override
	public Boolean delete(Long id) {
	    var ljmEmailTemplateDAO = ljmEmailTemplateRepository.findById(id)
	                     .orElseThrow(() -> new RuntimeException("LjmEmailTemplate not found with id: " + id));
	    ljmEmailTemplateDAO.setDeleted(true); 
	    ljmEmailTemplateRepository.save(ljmEmailTemplateDAO); 
	    return true;  
	}

	@Override
	public List<LjmEmailTemplateDTO> filterData(String search) {
		Specification<LjmEmailTemplateDAO> result = new FilterSpecificationsBuilder<LjmEmailTemplateDAO>().with(search).build();
		return LjmEmailTemplateMapper.INSTANCE.ljmEmailTemplateDAOListToLjmEmailTemplateDTOList(ljmEmailTemplateRepository.findAll(result));
	}
	
	@Override
	public List<LjmEmailTemplateDTO> filterData(JsonNode search, int page, int size) {
		Pageable pageable = PageRequest.of(page, size, Sort.by("etSysId").descending());
    Specification<LjmEmailTemplateDAO> result = new FilterSpecificationsBuilder<LjmEmailTemplateDAO>().with(search).build();
		return LjmEmailTemplateMapper.INSTANCE.ljmEmailTemplateDAOListToLjmEmailTemplateDTOList(ljmEmailTemplateRepository.findAll(result, pageable).getContent());
	}
}
