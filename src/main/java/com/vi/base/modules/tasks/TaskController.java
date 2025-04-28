/*
 - Version Number s0.0.1
*/

package com.vi.base.modules.tasks;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vi.base.modules.activitylogs.ActivityLogService;
import com.vi.model.dto.ActivityLogDTO;
import com.vi.model.dto.TaskDTO;

//import com.vi.corelib.TaskInfo;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/Task")
@Slf4j
public class TaskController {

	@Autowired
	TaskService taskService;
	
	@Autowired
	ActivityLogService activityLogService;

	@GetMapping("/all")
	public ResponseEntity<List<TaskDTO>> getAll(
			@Nullable @RequestParam HashMap<String, String> json, 
			@RequestParam(value = "page", defaultValue = "0") int page,
	        @RequestParam(value = "size", defaultValue = "1000") int size) {
		    json.remove("page");
		    json.remove("size");
		    json.put("deleted","false");
		JsonNode jsonRequest = new ObjectMapper().convertValue(json, JsonNode.class);
		  System.out.println("Request JSON: " + jsonRequest);
		  System.out.println("Page: " + page + ", Size: " + size);	
		
		return ResponseEntity.ok().body(taskService.filterData(jsonRequest, page, size));
	}

	@GetMapping("/{id}")
	public ResponseEntity<TaskDTO> getOne(@PathVariable Long id) {
		var taskDTO = taskService.get(id);
		return ResponseEntity.ok().body(taskDTO);
	}

	@PostMapping("/create")
	public ResponseEntity<TaskDTO> create( @RequestBody TaskDTO taskDTO) {
		
		var taskTaskDTO = taskService.create(taskDTO);
		
		ActivityLogUtil.createActivityLog("LEAD_CREATED","Task created: " + taskTaskDTO.getTaskName(),activityLogService);
		
		return ResponseEntity.ok().body(taskTaskDTO);
	}


	@PutMapping("/update")
	public ResponseEntity<TaskDTO> update( @RequestBody TaskDTO taskDTO) {
		
		var taskTaskDTO = taskService.update(taskDTO);
		
		return ResponseEntity.ok().body(taskTaskDTO);
	}

	@GetMapping("/filter")
	public ResponseEntity<List<TaskDTO>> filterData(@RequestParam(value = "search") String search) {
		
		return ResponseEntity.ok().body(taskService.filterData(search));
	}

	@GetMapping("/filter2")
	public ResponseEntity<List<TaskDTO>> filterData2(
			@RequestParam HashMap<String, Object> json,
			@RequestParam(value = "page", defaultValue = "0") int page,
	        @RequestParam(value = "size", defaultValue = "1000") int size) {
		    json.remove("page");
		    json.remove("size");
	
		JsonNode jsonRequest = new ObjectMapper().convertValue(json, JsonNode.class);

		  System.out.println("Request JSON: " + jsonRequest);
		  System.out.println("Page: " + page + ", Size: " + size);	
		return ResponseEntity.ok().body(taskService.filterData(jsonRequest, page, size));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deleteOne(@PathVariable Long id) {
		return ResponseEntity.ok().body(taskService.delete(id));
	}
	
	public class ActivityLogUtil {
	    public static void createActivityLog(String taskType,String taskName, ActivityLogService activityLogService) {
	        ActivityLogDTO activityLogDTO = new ActivityLogDTO();
	        activityLogDTO.setActivityLogDate(new Date(System.currentTimeMillis()));
	        activityLogDTO.setActivityLogType(taskType);
	        activityLogDTO.setActivityLogDescription(taskName);
	        activityLogService.create(activityLogDTO);
	    }
	}
}
