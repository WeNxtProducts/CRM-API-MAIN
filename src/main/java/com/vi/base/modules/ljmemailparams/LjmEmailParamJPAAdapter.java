/*
 - Version Number 0.0.1
*/

package com.vi.base.modules.ljmemailparams;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import com.fasterxml.jackson.databind.JsonNode;
//import com.vi.corelib.events.EventPublisher;
import com.vi.corelib.filter.FilterSpecificationsBuilder;
import com.vi.model.dao.LjmEmailParamDAO;
import com.vi.model.dto.LjmEmailParamDTO;

import jakarta.persistence.EntityNotFoundException;
import lombok.SneakyThrows;


public class LjmEmailParamJPAAdapter implements LjmEmailParamPersistent {
	@Autowired
	LjmEmailParamRepository ljmEmailParamRepository;

	@Override
	public List<LjmEmailParamDTO> fetchAll() {
	    List<LjmEmailParamDAO> ljmEmailParamDAOList = ljmEmailParamRepository.findAll(Sort.by(Sort.Order.desc("etSysId")));
	    return LjmEmailParamMapper.INSTANCE.ljmEmailParamDAOListToLjmEmailParamDTOList(ljmEmailParamDAOList);
	}

	@Override
	public LjmEmailParamDTO get(Long id) {
		var ljmEmailParamDAO = ljmEmailParamRepository.findById(id);
		if(ljmEmailParamDAO.isPresent()) {
			return LjmEmailParamMapper.INSTANCE.ljmEmailParamDAOToLjmEmailParamDTO(ljmEmailParamDAO.get());
		}
		return null;
	}

  @SneakyThrows
	@Override
	public LjmEmailParamDTO create(LjmEmailParamDTO ljmEmailParamDTO) {
		var ljmEmailParamDAO = LjmEmailParamMapper.INSTANCE.ljmEmailParamDTOToLjmEmailParamDAO(ljmEmailParamDTO);
		var newLjmEmailParam = ljmEmailParamRepository.save(ljmEmailParamDAO);
		var newData = LjmEmailParamMapper.INSTANCE.ljmEmailParamDAOToLjmEmailParamDTO(newLjmEmailParam);
		
		
		return newData;
	}

	@Override
 		public LjmEmailParamDTO update(LjmEmailParamDTO ljmEmailParamDTO) {
			
		 var ljmEmailParamDAO = ljmEmailParamRepository.findById(ljmEmailParamDTO.getEpSysId())
        .orElseThrow(() -> new EntityNotFoundException("Record not found with id: " + ljmEmailParamDTO.getEpSysId()));
		var oldData = LjmEmailParamMapper.INSTANCE.ljmEmailParamDAOToLjmEmailParamDTO(ljmEmailParamDAO);
		LjmEmailParamMapper.INSTANCE.assignValues(ljmEmailParamDTO, ljmEmailParamDAO);
		var newLjmEmailParam = ljmEmailParamRepository.save(ljmEmailParamDAO);
		var newData = LjmEmailParamMapper.INSTANCE.ljmEmailParamDAOToLjmEmailParamDTO(newLjmEmailParam); 
		
		return newData;   
	}

	@SneakyThrows
	@Override
	public Boolean delete(Long id) {
	    var ljmEmailParamDAO = ljmEmailParamRepository.findById(id)
	                     .orElseThrow(() -> new RuntimeException("LjmEmailParam not found with id: " + id));
	    ljmEmailParamDAO.setDeleted(true); 
	    ljmEmailParamRepository.save(ljmEmailParamDAO); 
	    return true;  
	}

	@Override
	public List<LjmEmailParamDTO> filterData(String search) {
		Specification<LjmEmailParamDAO> result = new FilterSpecificationsBuilder<LjmEmailParamDAO>().with(search).build();
		return LjmEmailParamMapper.INSTANCE.ljmEmailParamDAOListToLjmEmailParamDTOList(ljmEmailParamRepository.findAll(result));
	}
	
	@Override
	public List<LjmEmailParamDTO> filterData(JsonNode search, int page, int size) {
		Pageable pageable = PageRequest.of(page, size, Sort.by("etSysId").descending());
    Specification<LjmEmailParamDAO> result = new FilterSpecificationsBuilder<LjmEmailParamDAO>().with(search).build();
		return LjmEmailParamMapper.INSTANCE.ljmEmailParamDAOListToLjmEmailParamDTOList(ljmEmailParamRepository.findAll(result, pageable).getContent());
	}
	
	@Override
	public List<LjmEmailParamDTO> filterData(JsonNode search) {
    Specification<LjmEmailParamDAO> result = new FilterSpecificationsBuilder<LjmEmailParamDAO>().with(search).build();
		return LjmEmailParamMapper.INSTANCE.ljmEmailParamDAOListToLjmEmailParamDTOList(ljmEmailParamRepository.findAll(result));
	}
}
