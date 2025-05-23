/*
 - Version Number 0.0.1
*/

package com.vi.base.modules.activitylogs;

import jakarta.persistence.EntityNotFoundException;

import com.fasterxml.jackson.databind.JsonNode;
import com.vi.model.dao.ActivityLogDAO;
import com.vi.model.dto.ActivityLogDTO;
import com.vi.model.dto.ActivityLogDTO;
import com.vi.model.dto.ActivityLogDTO;

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


public class ActivityLogJPAAdapter implements ActivityLogPersistent {
	@Autowired
	ActivityLogRepository activityLogRepository;

	@Override
	public List<ActivityLogDTO> fetchAll() {
		var activityLogDAOList = activityLogRepository.findAll();
		return ActivityLogMapper.INSTANCE.activityLogDAOListToActivityLogDTOList(activityLogDAOList);
	}

	@Override
	public ActivityLogDTO get(Long id) {
		var activityLogDAO = activityLogRepository.findById(id);
		if(activityLogDAO.isPresent()) {
			return ActivityLogMapper.INSTANCE.activityLogDAOToActivityLogDTO(activityLogDAO.get());
		}
		return null;
	}

  @SneakyThrows
	@Override
	public ActivityLogDTO create(ActivityLogDTO activityLogDTO) {
		var activityLogDAO = ActivityLogMapper.INSTANCE.activityLogDTOToActivityLogDAO(activityLogDTO);
		var newActivityLog = activityLogRepository.save(activityLogDAO);
		var newData = ActivityLogMapper.INSTANCE.activityLogDAOToActivityLogDTO(newActivityLog);
		
		return newData;
	}

	  @Override
		public ActivityLogDTO update(ActivityLogDTO activityLogDTO) {
			
		var activityLogDAO = activityLogRepository.findById(activityLogDTO.getActivityLogSeqNo())
	    .orElseThrow(() -> new EntityNotFoundException("Record not found with id: " + activityLogDTO.getActivityLogSeqNo()));
		var oldData = ActivityLogMapper.INSTANCE.activityLogDAOToActivityLogDTO(activityLogDAO);
		ActivityLogMapper.INSTANCE.assignValues(activityLogDTO, activityLogDAO);
		var newActivityLog = activityLogRepository.save(activityLogDAO);
		var newData = ActivityLogMapper.INSTANCE.activityLogDAOToActivityLogDTO(newActivityLog); 
		
		return newData;   
}

	  @SneakyThrows
		@Override
		public Boolean delete(Long id) {
		    var activityLogDAO = activityLogRepository.findById(id)
		                    .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

		    activityLogDAO.setDeleted(true); // Set deleted flag to true
		    activityLogRepository.save(activityLogDAO); // Persist the change

		    return true; 
		}
	  

	@Override
	public List<ActivityLogDTO> filterData(String search) {
		Specification<ActivityLogDAO> result = new FilterSpecificationsBuilder<ActivityLogDAO>().with(search).build();
		return ActivityLogMapper.INSTANCE.activityLogDAOListToActivityLogDTOList(activityLogRepository.findAll(result));
	}

	@Override
	public List<ActivityLogDTO> filterData(JsonNode search, int page, int size) {
		Pageable pageable = PageRequest.of(page, size, Sort.by("activityLogSeqNo").ascending());

    Specification<ActivityLogDAO> result = new FilterSpecificationsBuilder<ActivityLogDAO>().with(search).build();
		return ActivityLogMapper.INSTANCE.activityLogDAOListToActivityLogDTOList(activityLogRepository.findAll(result, pageable).getContent());
	}
	
	@Override
	public List<ActivityLogDTO> filterData(JsonNode search) {

    Specification<ActivityLogDAO> result = new FilterSpecificationsBuilder<ActivityLogDAO>().with(search).build();
		return ActivityLogMapper.INSTANCE.activityLogDAOListToActivityLogDTOList(activityLogRepository.findAll(result));
	}
}
