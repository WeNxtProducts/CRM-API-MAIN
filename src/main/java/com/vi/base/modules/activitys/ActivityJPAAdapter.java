/*
 - Version Number 0.0.1
*/

package com.vi.base.modules.activitys;

import jakarta.persistence.EntityNotFoundException;

import com.fasterxml.jackson.databind.JsonNode;
import com.vi.model.dao.ActivityDAO;
import com.vi.model.dao.TaskDAO;
import com.vi.model.dto.ActivityDTO;
import com.vi.model.dto.ActivityDTO;
import com.vi.model.dto.ActivityDTO;

import com.vi.base.modules.activitys.ActivityMapper;
import com.vi.base.modules.tasks.TaskRepository;
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
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import org.json.simple.parser.ParseException;


public class ActivityJPAAdapter implements ActivityPersistent {
	@Autowired
	ActivityRepository activityRepository;
	
	@Autowired
	TaskRepository taskRepository;
	

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
	
	@Override
	public ResponseEntity<Map<String, Object>> getCalendarData(Date startDate, Date endDate, Long userSeqNo) {
	    try {
	        // Fetch only activities for the specific user
	        List<ActivityDAO> activities = activityRepository
	                .findByUserSeqNoAndActivityStartDateBetween(userSeqNo, startDate, endDate);

	        List<ActivityDAO> events = new ArrayList<>();
	        List<ActivityDAO> appointments = new ArrayList<>();

	        for (ActivityDAO activity : activities) {
	            if ("EVENT".equalsIgnoreCase(activity.getActivityType())) {
	                events.add(activity);
	            } else if ("APPOINTMENT".equalsIgnoreCase(activity.getActivityType())) {
	                appointments.add(activity);
	            }
	        }

	        // Fetch only tasks for the specific user
	        List<TaskDAO> tasks = taskRepository
	                .findByUserSeqNoAndTaskDueDateBetween(userSeqNo, startDate, endDate);

	        Map<String, Object> calendarData = new HashMap<>();
	        calendarData.put("events", events);
	        calendarData.put("appointments", appointments);
	        calendarData.put("tasks", tasks);

	        return ResponseEntity.ok(createResponse("success", "Calendar data fetched successfully", calendarData));
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.badRequest()
	                .body(createResponse("error", "Failed to fetch calendar data: " + e.getMessage(), null));
	    }
	}


    // Helper method to format response
    private Map<String, Object> createResponse(String status, String message, Object data) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", status);
        response.put("message", message);
        response.put("data", data);
        return response;
    }
}


