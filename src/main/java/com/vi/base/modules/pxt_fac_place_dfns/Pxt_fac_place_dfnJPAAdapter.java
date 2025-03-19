/*
 - Version Number 0.0.1
*/

package com.vi.base.modules.pxt_fac_place_dfns;

import com.fasterxml.jackson.databind.JsonNode;
import com.vi.model.dao.Pxt_fac_place_dfnDAO;
import com.vi.model.dto.Pxt_fac_place_dfnDTO;
//import com.vi.corelib.events.EventPublisher;
import com.vi.corelib.filter.FilterSpecificationsBuilder;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import java.util.List;
import java.io.IOException;
import org.json.simple.parser.ParseException;


public class Pxt_fac_place_dfnJPAAdapter implements Pxt_fac_place_dfnPersistent {
	@Autowired
	Pxt_fac_place_dfnRepository pxt_fac_place_dfnRepository;

	@Override
	public List<Pxt_fac_place_dfnDTO> fetchAll() {
		var pxt_fac_place_dfnDAOList = pxt_fac_place_dfnRepository.findAll();
		return Pxt_fac_place_dfnMapper.INSTANCE.pxt_fac_place_dfnDAOListToPxt_fac_place_dfnDTOList(pxt_fac_place_dfnDAOList);
	}

	@Override
	public Pxt_fac_place_dfnDTO get(Long id) {
		var pxt_fac_place_dfnDAO = pxt_fac_place_dfnRepository.findById(id);
		if(pxt_fac_place_dfnDAO.isPresent()) {
			return Pxt_fac_place_dfnMapper.INSTANCE.pxt_fac_place_dfnDAOToPxt_fac_place_dfnDTO(pxt_fac_place_dfnDAO.get());
		}
		return null;
	}

  @SneakyThrows
	@Override
	public Pxt_fac_place_dfnDTO create(Pxt_fac_place_dfnDTO pxt_fac_place_dfnDTO) {
		var pxt_fac_place_dfnDAO = Pxt_fac_place_dfnMapper.INSTANCE.pxt_fac_place_dfnDTOToPxt_fac_place_dfnDAO(pxt_fac_place_dfnDTO);
		var newPxt_fac_place_dfn = pxt_fac_place_dfnRepository.save(pxt_fac_place_dfnDAO);
		var newData = Pxt_fac_place_dfnMapper.INSTANCE.pxt_fac_place_dfnDAOToPxt_fac_place_dfnDTO(newPxt_fac_place_dfn);
		
		
		return newData;
	}

	@Override
 		public Pxt_fac_place_dfnDTO update(Pxt_fac_place_dfnDTO pxt_fac_place_dfnDTO) {
		var pxt_fac_place_dfnDAO = pxt_fac_place_dfnRepository.getById(pxt_fac_place_dfnDTO.getFPD_SYS_ID());
		var oldData = Pxt_fac_place_dfnMapper.INSTANCE.pxt_fac_place_dfnDAOToPxt_fac_place_dfnDTO(pxt_fac_place_dfnDAO);
		Pxt_fac_place_dfnMapper.INSTANCE.assignValues(pxt_fac_place_dfnDTO, pxt_fac_place_dfnDAO);
		var newPxt_fac_place_dfn = pxt_fac_place_dfnRepository.save(pxt_fac_place_dfnDAO);
		var newData = Pxt_fac_place_dfnMapper.INSTANCE.pxt_fac_place_dfnDAOToPxt_fac_place_dfnDTO(newPxt_fac_place_dfn); 
		
		return newData;   
	}

	@SneakyThrows
	@Override
	public Boolean delete(Long id) {
		var pxt_fac_place_dfnDAO = pxt_fac_place_dfnRepository.getById(id);
		var oldData = Pxt_fac_place_dfnMapper.INSTANCE.pxt_fac_place_dfnDAOToPxt_fac_place_dfnDTO(pxt_fac_place_dfnDAO);
		// Pxt_fac_place_dfnDAO.setDeleted(true);
		var newData = pxt_fac_place_dfnRepository.save(pxt_fac_place_dfnDAO);
		
		return true;
	}

	@Override
	public List<Pxt_fac_place_dfnDTO> filterData(String search) {
		Specification<Pxt_fac_place_dfnDAO> result = new FilterSpecificationsBuilder<Pxt_fac_place_dfnDAO>().with(search).build();
		return Pxt_fac_place_dfnMapper.INSTANCE.pxt_fac_place_dfnDAOListToPxt_fac_place_dfnDTOList(pxt_fac_place_dfnRepository.findAll(result));
	}

	@Override
	public List<Pxt_fac_place_dfnDTO> filterData(JsonNode search) {
    Specification<Pxt_fac_place_dfnDAO> result = new FilterSpecificationsBuilder<Pxt_fac_place_dfnDAO>().with(search).build();
		return Pxt_fac_place_dfnMapper.INSTANCE.pxt_fac_place_dfnDAOListToPxt_fac_place_dfnDTOList(pxt_fac_place_dfnRepository.findAll(result));
	}
}
