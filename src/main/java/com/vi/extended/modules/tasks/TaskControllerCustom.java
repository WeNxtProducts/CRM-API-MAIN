package com.vi.extended.modules.tasks;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.vi.base.modules.tasks.TaskRepository;
import com.vi.base.modules.tasks.TaskService;
import com.vi.model.dto.TaskDTOCustom;
import com.vi.model.dto.SummaryDTO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/Task")
@Slf4j
public class TaskControllerCustom {

    @Autowired
    private Environment env;

    @Autowired
    private TaskServiceCustom taskServiceCustom;  // Ensure it's Autowired

    @Autowired
    private TaskService taskService;
    
    @Autowired
    private TaskRepository taskRepository;

    @PersistenceContext
    private EntityManager em;   

    @PostMapping("/Filter")
    public ResponseEntity<?> filter(
            @RequestBody JsonNode json, 
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "1000") int size) {

        try {
            ObjectNode mutableJson = json.deepCopy();
            mutableJson.remove("page");
            mutableJson.remove("size");
            mutableJson.put("deleted","false");

            log.info("Filter request received with JSON: {}", mutableJson.toString());

            return ResponseEntity.ok().body(taskService.filterData(mutableJson, page, size));
        } catch (Exception ex) {
            log.error("Error filtering tasks", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", ex.getMessage()));
        }
    }
    
    @GetMapping("/Stats")
    public ResponseEntity<?> getBrokerCode() {
        String sql = "SELECT * FROM wn_task_summary";
        List<Object[]> queryResult = em.createNativeQuery(sql).getResultList();
        
        List<SummaryDTO> result = queryResult.stream()
                .map(row -> new SummaryDTO(
                        ((Number) row[0]).longValue(),  // userSeqNo
                        ((Number) row[1]).longValue(),  // totalCount
                        (BigDecimal) row[2],  // newlyAdded
                        (BigDecimal) row[3],  // onPriority
                        (BigDecimal) row[4],  // completed
                        (String) row[5]       // monthTasks (JSON String)
                ))
                .toList();

        return ResponseEntity.ok().body(result);
    }

    
    
    
   
   
}