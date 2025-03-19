/*
 - Version Number 0.0.1
*/

package com.vi.base.modules.pxt_fac_rsk_cvrs;

import com.fasterxml.jackson.databind.JsonNode;
import com.vi.model.dao.Pxt_fac_rsk_cvrDAO;
import com.vi.model.dto.Pxt_fac_rsk_cvrDTO;
//import com.vi.corelib.events.EventPublisher;
import com.vi.corelib.filter.FilterSpecificationsBuilder;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import java.util.List;
import java.io.IOException;
import org.json.simple.parser.ParseException;


public class Pxt_fac_rsk_cvrJPAAdapter implements Pxt_fac_rsk_cvrPersistent {
	@Autowired
	Pxt_fac_rsk_cvrRepository pxt_fac_rsk_cvrRepository;

	@Override
	public List<Pxt_fac_rsk_cvrDTO> fetchAll() {
		var pxt_fac_rsk_cvrDAOList = pxt_fac_rsk_cvrRepository.findAll();
		return Pxt_fac_rsk_cvrMapper.INSTANCE.pxt_fac_rsk_cvrDAOListToPxt_fac_rsk_cvrDTOList(pxt_fac_rsk_cvrDAOList);
	}

	@Override
	public Pxt_fac_rsk_cvrDTO get(Long id) {
		var pxt_fac_rsk_cvrDAO = pxt_fac_rsk_cvrRepository.findById(id);
		if(pxt_fac_rsk_cvrDAO.isPresent()) {
			return Pxt_fac_rsk_cvrMapper.INSTANCE.pxt_fac_rsk_cvrDAOToPxt_fac_rsk_cvrDTO(pxt_fac_rsk_cvrDAO.get());
		}
		return null;
	}

  @SneakyThrows
	@Override
	public Pxt_fac_rsk_cvrDTO create(Pxt_fac_rsk_cvrDTO pxt_fac_rsk_cvrDTO) {
		var pxt_fac_rsk_cvrDAO = Pxt_fac_rsk_cvrMapper.INSTANCE.pxt_fac_rsk_cvrDTOToPxt_fac_rsk_cvrDAO(pxt_fac_rsk_cvrDTO);
		var newPxt_fac_rsk_cvr = pxt_fac_rsk_cvrRepository.save(pxt_fac_rsk_cvrDAO);
		var newData = Pxt_fac_rsk_cvrMapper.INSTANCE.pxt_fac_rsk_cvrDAOToPxt_fac_rsk_cvrDTO(newPxt_fac_rsk_cvr);
		
		
		return newData;
	}

	@Override
 		public Pxt_fac_rsk_cvrDTO update(Pxt_fac_rsk_cvrDTO pxt_fac_rsk_cvrDTO) {
		var pxt_fac_rsk_cvrDAO = pxt_fac_rsk_cvrRepository.getById(pxt_fac_rsk_cvrDTO.getFRC_SYS_ID());
		var oldData = Pxt_fac_rsk_cvrMapper.INSTANCE.pxt_fac_rsk_cvrDAOToPxt_fac_rsk_cvrDTO(pxt_fac_rsk_cvrDAO);
		Pxt_fac_rsk_cvrMapper.INSTANCE.assignValues(pxt_fac_rsk_cvrDTO, pxt_fac_rsk_cvrDAO);
		var newPxt_fac_rsk_cvr = pxt_fac_rsk_cvrRepository.save(pxt_fac_rsk_cvrDAO);
		var newData = Pxt_fac_rsk_cvrMapper.INSTANCE.pxt_fac_rsk_cvrDAOToPxt_fac_rsk_cvrDTO(newPxt_fac_rsk_cvr); 
		
		return newData;   
	}

	@SneakyThrows
	@Override
	public Boolean delete(Long id) {
		var pxt_fac_rsk_cvrDAO = pxt_fac_rsk_cvrRepository.getById(id);
		var oldData = Pxt_fac_rsk_cvrMapper.INSTANCE.pxt_fac_rsk_cvrDAOToPxt_fac_rsk_cvrDTO(pxt_fac_rsk_cvrDAO);
		// Pxt_fac_rsk_cvrDAO.setDeleted(true);
		var newData = pxt_fac_rsk_cvrRepository.save(pxt_fac_rsk_cvrDAO);
		
		return true;
	}

	@Override
	public List<Pxt_fac_rsk_cvrDTO> filterData(String search) {
		Specification<Pxt_fac_rsk_cvrDAO> result = new FilterSpecificationsBuilder<Pxt_fac_rsk_cvrDAO>().with(search).build();
		return Pxt_fac_rsk_cvrMapper.INSTANCE.pxt_fac_rsk_cvrDAOListToPxt_fac_rsk_cvrDTOList(pxt_fac_rsk_cvrRepository.findAll(result));
	}

	@Override
	public List<Pxt_fac_rsk_cvrDTO> filterData(JsonNode search) {
    Specification<Pxt_fac_rsk_cvrDAO> result = new FilterSpecificationsBuilder<Pxt_fac_rsk_cvrDAO>().with(search).build();
		return Pxt_fac_rsk_cvrMapper.INSTANCE.pxt_fac_rsk_cvrDAOListToPxt_fac_rsk_cvrDTOList(pxt_fac_rsk_cvrRepository.findAll(result));
	}
}
