/*
 - Version Number 0.0.1
*/

package com.vi.base.modules.enquirys;

import jakarta.persistence.EntityNotFoundException;

import com.fasterxml.jackson.databind.JsonNode;
import com.vi.model.dao.EnquiryDAO;
import com.vi.model.dto.EnquiryDTO;
import com.vi.model.dto.EnquiryDTO;
import com.vi.model.dto.EnquiryDTO;

import com.vi.base.modules.enquirys.EnquiryMapper;
import com.vi.base.modules.enquirys.EnquiryMapper;
import com.vi.base.modules.enquirys.EnquiryMapper;
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


public class EnquiryJPAAdapter implements EnquiryPersistent {
	@Autowired
	EnquiryRepository enquiryRepository;

	@Override
	public List<EnquiryDTO> fetchAll() {
		var enquiryDAOList = enquiryRepository.findAll();
		return EnquiryMapper.INSTANCE.enquiryDAOListToEnquiryDTOList(enquiryDAOList);
	}

	@Override
	public EnquiryDTO get(Long id) {
		var enquiryDAO = enquiryRepository.findById(id);
		if(enquiryDAO.isPresent()) {
			return EnquiryMapper.INSTANCE.enquiryDAOToEnquiryDTO(enquiryDAO.get());
		}
		return null;
	}

  @SneakyThrows
	@Override
	public EnquiryDTO create(EnquiryDTO enquiryDTO) {
		var enquiryDAO = EnquiryMapper.INSTANCE.enquiryDTOToEnquiryDAO(enquiryDTO);
		var newEnquiry = enquiryRepository.save(enquiryDAO);
		var newData = EnquiryMapper.INSTANCE.enquiryDAOToEnquiryDTO(newEnquiry);
		
		return newData;
	}

	  @Override
		public EnquiryDTO update(EnquiryDTO enquiryDTO) {
			
		var enquiryDAO = enquiryRepository.findById(enquiryDTO.getEnqSeqNo())
	    .orElseThrow(() -> new EntityNotFoundException("Record not found with id: " + enquiryDTO.getEnqSeqNo()));
		var oldData = EnquiryMapper.INSTANCE.enquiryDAOToEnquiryDTO(enquiryDAO);
		EnquiryMapper.INSTANCE.assignValues(enquiryDTO, enquiryDAO);
		var newEnquiry = enquiryRepository.save(enquiryDAO);
		var newData = EnquiryMapper.INSTANCE.enquiryDAOToEnquiryDTO(newEnquiry); 
		
		return newData;   
}
	  
	  @SneakyThrows
		@Override
		public Boolean delete(Long id) {
		    var enquiryDAO = enquiryRepository.findById(id)
		                    .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

		    enquiryDAO.setDeleted(true); // Set deleted flag to true
		    enquiryRepository.save(enquiryDAO); // Persist the change

		    return true; 
		}

	@Override
	public List<EnquiryDTO> filterData(String search) {
		Specification<EnquiryDAO> result = new FilterSpecificationsBuilder<EnquiryDAO>().with(search).build();
		return EnquiryMapper.INSTANCE.enquiryDAOListToEnquiryDTOList(enquiryRepository.findAll(result));
	}

	@Override
	public List<EnquiryDTO> filterData(JsonNode search, int page, int size) {
		Pageable pageable = PageRequest.of(page, size, Sort.by("enqSeqNo").descending());

    Specification<EnquiryDAO> result = new FilterSpecificationsBuilder<EnquiryDAO>().with(search).build();
		return EnquiryMapper.INSTANCE.enquiryDAOListToEnquiryDTOList(enquiryRepository.findAll(result, pageable).getContent());
	}
}
