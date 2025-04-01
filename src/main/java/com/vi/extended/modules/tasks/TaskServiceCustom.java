/*
 - Version Number 0.0.1
*/

package com.vi.extended.modules.tasks;

import org.springframework.beans.factory.annotation.Autowired;

import com.vi.corelib.base.BaseService;
import com.vi.model.dao.TaskDAO;
import com.vi.model.dto.TaskDTOCustom;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

public interface TaskServiceCustom extends BaseService<TaskDTOCustom> {

}
