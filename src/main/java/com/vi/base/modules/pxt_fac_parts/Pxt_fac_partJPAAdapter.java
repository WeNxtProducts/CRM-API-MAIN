/*
 - Version Number 0.0.1
*/

package com.vi.base.modules.pxt_fac_parts;

import com.fasterxml.jackson.databind.JsonNode;
import com.vi.model.dao.Pxt_fac_partDAO;
import com.vi.model.dto.Pxt_fac_partDTO;
import com.vi.model.dto.Pxt_fac_partDTO;

import jakarta.persistence.EntityNotFoundException;

import com.vi.base.modules.pxt_fac_parts.Pxt_fac_partMapper;
import com.vi.base.modules.pxt_fac_parts.Pxt_fac_partMapper;
//import com.vi.corelib.events.EventPublisher;
import com.vi.corelib.filter.FilterSpecificationsBuilder;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import java.util.List;
import java.io.IOException;
import org.json.simple.parser.ParseException;


public class Pxt_fac_partJPAAdapter implements Pxt_fac_partPersistent {
	@Autowired
	Pxt_fac_partRepository pxt_fac_partRepository;

	@Override
	public List<Pxt_fac_partDTO> fetchAll() {
		var pxt_fac_partDAOList = pxt_fac_partRepository.findAll();
		return Pxt_fac_partMapper.INSTANCE.pxt_fac_partDAOListToPxt_fac_partDTOList(pxt_fac_partDAOList);
	}

	@Override
	public Pxt_fac_partDTO get(Long id) {
		var pxt_fac_partDAO = pxt_fac_partRepository.findById(id);
		if(pxt_fac_partDAO.isPresent()) {
			return Pxt_fac_partMapper.INSTANCE.pxt_fac_partDAOToPxt_fac_partDTO(pxt_fac_partDAO.get());
		}
		return null;
	}

  @SneakyThrows
	@Override
	public Pxt_fac_partDTO create(Pxt_fac_partDTO pxt_fac_partDTO) {
		var pxt_fac_partDAO = Pxt_fac_partMapper.INSTANCE.pxt_fac_partDTOToPxt_fac_partDAO(pxt_fac_partDTO);
		var newPxt_fac_part = pxt_fac_partRepository.save(pxt_fac_partDAO);
		var newData = Pxt_fac_partMapper.INSTANCE.pxt_fac_partDAOToPxt_fac_partDTO(newPxt_fac_part);
		
		
		return newData;
	}

	@Override
 		public Pxt_fac_partDTO update(Pxt_fac_partDTO pxt_fac_partDTO) {
			
		 var pxt_fac_partDAO = pxt_fac_partRepository.findById(pxt_fac_partDTO.getFpSysId())
        .orElseThrow(() -> new EntityNotFoundException("Record not found with id: " + pxt_fac_partDTO.getFpSysId()));
		var oldData = Pxt_fac_partMapper.INSTANCE.pxt_fac_partDAOToPxt_fac_partDTO(pxt_fac_partDAO);
		Pxt_fac_partMapper.INSTANCE.assignValues(pxt_fac_partDTO, pxt_fac_partDAO);
		var newPxt_fac_part = pxt_fac_partRepository.save(pxt_fac_partDAO);
		var newData = Pxt_fac_partMapper.INSTANCE.pxt_fac_partDAOToPxt_fac_partDTO(newPxt_fac_part); 
		
		return newData;   
	}

	@SneakyThrows
	@Override
	public Boolean delete(Long id) {
		var pxt_fac_partDAO = pxt_fac_partRepository.getById(id);
		var oldData = Pxt_fac_partMapper.INSTANCE.pxt_fac_partDAOToPxt_fac_partDTO(pxt_fac_partDAO);
		// Pxt_fac_partDAO.setDeleted(true);
		var newData = pxt_fac_partRepository.save(pxt_fac_partDAO);
		
		return true;
	}

	@Override
	public List<Pxt_fac_partDTO> filterData(String search) {
		Specification<Pxt_fac_partDAO> result = new FilterSpecificationsBuilder<Pxt_fac_partDAO>().with(search).build();
		return Pxt_fac_partMapper.INSTANCE.pxt_fac_partDAOListToPxt_fac_partDTOList(pxt_fac_partRepository.findAll(result));
	}

	@Override
	public List<Pxt_fac_partDTO> filterData(JsonNode search) {
    Specification<Pxt_fac_partDAO> result = new FilterSpecificationsBuilder<Pxt_fac_partDAO>().with(search).build();
		return Pxt_fac_partMapper.INSTANCE.pxt_fac_partDAOListToPxt_fac_partDTOList(pxt_fac_partRepository.findAll(result));
	}
}
