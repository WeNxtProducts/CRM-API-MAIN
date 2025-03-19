/*
 - Version Number 0.0.1
*/

package com.vi.base.modules.pxt_fac_part_comms;

import com.fasterxml.jackson.databind.JsonNode;
import com.vi.model.dao.Pxt_fac_part_commDAO;
import com.vi.model.dto.Pxt_fac_part_commDTO;

import jakarta.persistence.EntityNotFoundException;

//import com.vi.corelib.events.EventPublisher;
import com.vi.corelib.filter.FilterSpecificationsBuilder;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import java.util.List;
import java.io.IOException;
import org.json.simple.parser.ParseException;


public class Pxt_fac_part_commJPAAdapter implements Pxt_fac_part_commPersistent {
	@Autowired
	Pxt_fac_part_commRepository pxt_fac_part_commRepository;

	@Override
	public List<Pxt_fac_part_commDTO> fetchAll() {
		var pxt_fac_part_commDAOList = pxt_fac_part_commRepository.findAll();
		return Pxt_fac_part_commMapper.INSTANCE.pxt_fac_part_commDAOListToPxt_fac_part_commDTOList(pxt_fac_part_commDAOList);
	}

	@Override
	public Pxt_fac_part_commDTO get(Long id) {
		var pxt_fac_part_commDAO = pxt_fac_part_commRepository.findById(id);
		if(pxt_fac_part_commDAO.isPresent()) {
			return Pxt_fac_part_commMapper.INSTANCE.pxt_fac_part_commDAOToPxt_fac_part_commDTO(pxt_fac_part_commDAO.get());
		}
		return null;
	}

  @SneakyThrows
	@Override
	public Pxt_fac_part_commDTO create(Pxt_fac_part_commDTO pxt_fac_part_commDTO) {
		var pxt_fac_part_commDAO = Pxt_fac_part_commMapper.INSTANCE.pxt_fac_part_commDTOToPxt_fac_part_commDAO(pxt_fac_part_commDTO);
		var newPxt_fac_part_comm = pxt_fac_part_commRepository.save(pxt_fac_part_commDAO);
		var newData = Pxt_fac_part_commMapper.INSTANCE.pxt_fac_part_commDAOToPxt_fac_part_commDTO(newPxt_fac_part_comm);
		
		
		return newData;
	}

	@Override
 		public Pxt_fac_part_commDTO update(Pxt_fac_part_commDTO pxt_fac_part_commDTO) {
			
		 var pxt_fac_part_commDAO = pxt_fac_part_commRepository.findById(pxt_fac_part_commDTO.getFpcSysId())
        .orElseThrow(() -> new EntityNotFoundException("Record not found with id: " + pxt_fac_part_commDTO.getFpcSysId()));
		var oldData = Pxt_fac_part_commMapper.INSTANCE.pxt_fac_part_commDAOToPxt_fac_part_commDTO(pxt_fac_part_commDAO);
		Pxt_fac_part_commMapper.INSTANCE.assignValues(pxt_fac_part_commDTO, pxt_fac_part_commDAO);
		var newPxt_fac_part_comm = pxt_fac_part_commRepository.save(pxt_fac_part_commDAO);
		var newData = Pxt_fac_part_commMapper.INSTANCE.pxt_fac_part_commDAOToPxt_fac_part_commDTO(newPxt_fac_part_comm); 
		
		return newData;   
	}

	@SneakyThrows
	@Override
	public Boolean delete(Long id) {
		var pxt_fac_part_commDAO = pxt_fac_part_commRepository.getById(id);
		var oldData = Pxt_fac_part_commMapper.INSTANCE.pxt_fac_part_commDAOToPxt_fac_part_commDTO(pxt_fac_part_commDAO);
		// Pxt_fac_part_commDAO.setDeleted(true);
		var newData = pxt_fac_part_commRepository.save(pxt_fac_part_commDAO);
		
		return true;
	}

	@Override
	public List<Pxt_fac_part_commDTO> filterData(String search) {
		Specification<Pxt_fac_part_commDAO> result = new FilterSpecificationsBuilder<Pxt_fac_part_commDAO>().with(search).build();
		return Pxt_fac_part_commMapper.INSTANCE.pxt_fac_part_commDAOListToPxt_fac_part_commDTOList(pxt_fac_part_commRepository.findAll(result));
	}

	@Override
	public List<Pxt_fac_part_commDTO> filterData(JsonNode search) {
    Specification<Pxt_fac_part_commDAO> result = new FilterSpecificationsBuilder<Pxt_fac_part_commDAO>().with(search).build();
		return Pxt_fac_part_commMapper.INSTANCE.pxt_fac_part_commDAOListToPxt_fac_part_commDTOList(pxt_fac_part_commRepository.findAll(result));
	}
}
