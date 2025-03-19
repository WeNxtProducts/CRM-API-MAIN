/*
 - Version Number 0.0.1
*/

package com.vi.base.modules.pxt_uw_css;

import com.fasterxml.jackson.databind.JsonNode;
import com.vi.model.dao.Pxt_uw_csDAO;
import com.vi.model.dto.Pxt_uw_csDTO;

import jakarta.persistence.EntityNotFoundException;

//import com.vi.corelib.events.EventPublisher;
import com.vi.corelib.filter.FilterSpecificationsBuilder;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import java.util.List;
import java.io.IOException;
import org.json.simple.parser.ParseException;


public class Pxt_uw_csJPAAdapter implements Pxt_uw_csPersistent {
	@Autowired
	Pxt_uw_csRepository pxt_uw_csRepository;

	@Override
	public List<Pxt_uw_csDTO> fetchAll() {
		var pxt_uw_csDAOList = pxt_uw_csRepository.findAll();
		return Pxt_uw_csMapper.INSTANCE.pxt_uw_csDAOListToPxt_uw_csDTOList(pxt_uw_csDAOList);
	}

	@Override
	public Pxt_uw_csDTO get(Long id) {
		var pxt_uw_csDAO = pxt_uw_csRepository.findById(id);
		if(pxt_uw_csDAO.isPresent()) {
			return Pxt_uw_csMapper.INSTANCE.pxt_uw_csDAOToPxt_uw_csDTO(pxt_uw_csDAO.get());
		}
		return null;
	}

  @SneakyThrows
	@Override
	public Pxt_uw_csDTO create(Pxt_uw_csDTO pxt_uw_csDTO) {
		var pxt_uw_csDAO = Pxt_uw_csMapper.INSTANCE.pxt_uw_csDTOToPxt_uw_csDAO(pxt_uw_csDTO);
		var newPxt_uw_cs = pxt_uw_csRepository.save(pxt_uw_csDAO);
		var newData = Pxt_uw_csMapper.INSTANCE.pxt_uw_csDAOToPxt_uw_csDTO(newPxt_uw_cs);
		
		
		return newData;
	}

	@Override
 		public Pxt_uw_csDTO update(Pxt_uw_csDTO pxt_uw_csDTO) {
			
		 var pxt_uw_csDAO = pxt_uw_csRepository.findById(pxt_uw_csDTO.getUcsSysId())
        .orElseThrow(() -> new EntityNotFoundException("Record not found with id: " + pxt_uw_csDTO.getUcsSysId()));
		var oldData = Pxt_uw_csMapper.INSTANCE.pxt_uw_csDAOToPxt_uw_csDTO(pxt_uw_csDAO);
		Pxt_uw_csMapper.INSTANCE.assignValues(pxt_uw_csDTO, pxt_uw_csDAO);
		var newPxt_uw_cs = pxt_uw_csRepository.save(pxt_uw_csDAO);
		var newData = Pxt_uw_csMapper.INSTANCE.pxt_uw_csDAOToPxt_uw_csDTO(newPxt_uw_cs); 
		
		return newData;   
	}

	@SneakyThrows
	@Override
	public Boolean delete(Long id) {
		var pxt_uw_csDAO = pxt_uw_csRepository.getById(id);
		var oldData = Pxt_uw_csMapper.INSTANCE.pxt_uw_csDAOToPxt_uw_csDTO(pxt_uw_csDAO);
		// Pxt_uw_csDAO.setDeleted(true);
		var newData = pxt_uw_csRepository.save(pxt_uw_csDAO);
		
		return true;
	}

	@Override
	public List<Pxt_uw_csDTO> filterData(String search) {
		Specification<Pxt_uw_csDAO> result = new FilterSpecificationsBuilder<Pxt_uw_csDAO>().with(search).build();
		return Pxt_uw_csMapper.INSTANCE.pxt_uw_csDAOListToPxt_uw_csDTOList(pxt_uw_csRepository.findAll(result));
	}

	@Override
	public List<Pxt_uw_csDTO> filterData(JsonNode search) {
    Specification<Pxt_uw_csDAO> result = new FilterSpecificationsBuilder<Pxt_uw_csDAO>().with(search).build();
		return Pxt_uw_csMapper.INSTANCE.pxt_uw_csDAOListToPxt_uw_csDTOList(pxt_uw_csRepository.findAll(result));
	}
}
