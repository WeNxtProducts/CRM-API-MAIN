/*
 - Version Number 0.0.1
*/

package com.vi.base.modules.pxt_fac_hdrs;

import com.fasterxml.jackson.databind.JsonNode;
import com.vi.model.dao.Pxt_fac_hdrDAO;
import com.vi.model.dto.Pxt_fac_hdrDTO;
//import com.vi.corelib.events.EventPublisher;
import com.vi.corelib.filter.FilterSpecificationsBuilder;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import java.util.List;
import java.io.IOException;
import org.json.simple.parser.ParseException;


public class Pxt_fac_hdrJPAAdapter implements Pxt_fac_hdrPersistent {
	@Autowired
	Pxt_fac_hdrRepository pxt_fac_hdrRepository;

	@Override
	public List<Pxt_fac_hdrDTO> fetchAll() {
		var pxt_fac_hdrDAOList = pxt_fac_hdrRepository.findAll();
		return Pxt_fac_hdrMapper.INSTANCE.pxt_fac_hdrDAOListToPxt_fac_hdrDTOList(pxt_fac_hdrDAOList);
	}

	@Override
	public Pxt_fac_hdrDTO get(Long id) {
		var pxt_fac_hdrDAO = pxt_fac_hdrRepository.findById(id);
		if(pxt_fac_hdrDAO.isPresent()) {
			return Pxt_fac_hdrMapper.INSTANCE.pxt_fac_hdrDAOToPxt_fac_hdrDTO(pxt_fac_hdrDAO.get());
		}
		return null;
	}

  @SneakyThrows
	@Override
	public Pxt_fac_hdrDTO create(Pxt_fac_hdrDTO pxt_fac_hdrDTO) {
		var pxt_fac_hdrDAO = Pxt_fac_hdrMapper.INSTANCE.pxt_fac_hdrDTOToPxt_fac_hdrDAO(pxt_fac_hdrDTO);
		var newPxt_fac_hdr = pxt_fac_hdrRepository.save(pxt_fac_hdrDAO);
		var newData = Pxt_fac_hdrMapper.INSTANCE.pxt_fac_hdrDAOToPxt_fac_hdrDTO(newPxt_fac_hdr);
		
		
		return newData;
	}

	@Override
 		public Pxt_fac_hdrDTO update(Pxt_fac_hdrDTO pxt_fac_hdrDTO) {
		var pxt_fac_hdrDAO = pxt_fac_hdrRepository.getById(pxt_fac_hdrDTO.getFH_SYS_ID());
		var oldData = Pxt_fac_hdrMapper.INSTANCE.pxt_fac_hdrDAOToPxt_fac_hdrDTO(pxt_fac_hdrDAO);
		Pxt_fac_hdrMapper.INSTANCE.assignValues(pxt_fac_hdrDTO, pxt_fac_hdrDAO);
		var newPxt_fac_hdr = pxt_fac_hdrRepository.save(pxt_fac_hdrDAO);
		var newData = Pxt_fac_hdrMapper.INSTANCE.pxt_fac_hdrDAOToPxt_fac_hdrDTO(newPxt_fac_hdr); 
		
		return newData;   
	}

	@SneakyThrows
	@Override
	public Boolean delete(Long id) {
		var pxt_fac_hdrDAO = pxt_fac_hdrRepository.getById(id);
		var oldData = Pxt_fac_hdrMapper.INSTANCE.pxt_fac_hdrDAOToPxt_fac_hdrDTO(pxt_fac_hdrDAO);
		// Pxt_fac_hdrDAO.setDeleted(true);
		var newData = pxt_fac_hdrRepository.save(pxt_fac_hdrDAO);
		
		return true;
	}

	@Override
	public List<Pxt_fac_hdrDTO> filterData(String search) {
		Specification<Pxt_fac_hdrDAO> result = new FilterSpecificationsBuilder<Pxt_fac_hdrDAO>().with(search).build();
		return Pxt_fac_hdrMapper.INSTANCE.pxt_fac_hdrDAOListToPxt_fac_hdrDTOList(pxt_fac_hdrRepository.findAll(result));
	}

	@Override
	public List<Pxt_fac_hdrDTO> filterData(JsonNode search) {
    Specification<Pxt_fac_hdrDAO> result = new FilterSpecificationsBuilder<Pxt_fac_hdrDAO>().with(search).build();
		return Pxt_fac_hdrMapper.INSTANCE.pxt_fac_hdrDAOListToPxt_fac_hdrDTOList(pxt_fac_hdrRepository.findAll(result));
	}
}
