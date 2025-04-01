/*
 - Version Number 0.0.1
*/

package com.vi.base.modules.activitys;

import jakarta.persistence.EntityNotFoundException;

import com.fasterxml.jackson.databind.JsonNode;
import com.vi.model.dao.ActivityDAO;
import com.vi.model.dto.ActivityDTO;
import com.vi.model.dto.ActivityDTO;
import com.vi.model.dto.ActivityDTO;

import com.vi.base.modules.activitys.ActivityMapper;
import com.vi.base.modules.activitys.ActivityMapper;
import com.vi.base.modules.activitys.ActivityMapper;
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


public class ActivityJPAAdapter implements ActivityPersistent {
	@Autowired
	ActivityRepository activityRepository;

	@Override
	public List<ActivityDTO> fetchAll() {
		var activityDAOList = activityRepository.findAll();
		return ActivityMapper.INSTANCE.activityDAOListToActivityDTOList(activityDAOList);
	}

	@Override
	public ActivityDTO get(Long id) {
		var activityDAO = activityRepository.findById(id);
		if(activityDAO.isPresent()) {
			return ActivityMapper.INSTANCE.activityDAOToActivityDTO(activityDAO.get());
		}
		return null;
	}

  @SneakyThrows
	@Override
	public ActivityDTO create(ActivityDTO activityDTO) {
		var activityDAO = ActivityMapper.INSTANCE.activityDTOToActivityDAO(activityDTO);
		var newActivity = activityRepository.save(activityDAO);
		var newData = ActivityMapper.INSTANCE.activityDAOToActivityDTO(newActivity);
		
		return newData;
	}

	  @Override
		public ActivityDTO update(ActivityDTO activityDTO) {
			
		var activityDAO = activityRepository.findById(activityDTO.getActivitySeqNo())
	    .orElseThrow(() -> new EntityNotFoundException("Record not found with id: " + activityDTO.getActivitySeqNo()));
		var oldData = ActivityMapper.INSTANCE.activityDAOToActivityDTO(activityDAO);
		ActivityMapper.INSTANCE.assignValues(activityDTO, activityDAO);
		var newActivity = activityRepository.save(activityDAO);
		var newData = ActivityMapper.INSTANCE.activityDAOToActivityDTO(newActivity); 
		
		return newData;   
}

	  @SneakyThrows
		@Override
		public Boolean delete(Long id) {
		    var activityDAO = activityRepository.findById(id)
		                    .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

		    activityDAO.setDeleted(true); // Set deleted flag to true
		    activityRepository.save(activityDAO); // Persist the change

		    return true; 
		}

	@Override
	public List<ActivityDTO> filterData(String search) {
		Specification<ActivityDAO> result = new FilterSpecificationsBuilder<ActivityDAO>().with(search).build();
		return ActivityMapper.INSTANCE.activityDAOListToActivityDTOList(activityRepository.findAll(result));
	}

	@Override
	public List<ActivityDTO> filterData(JsonNode search, int page, int size) {
		Pageable pageable = PageRequest.of(page, size, Sort.by("activitySeqNo").descending());
		Specification<ActivityDAO> result = new FilterSpecificationsBuilder<ActivityDAO>().with(search).build();
		return ActivityMapper.INSTANCE.activityDAOListToActivityDTOList(activityRepository.findAll(result, pageable).getContent());
	}
}
