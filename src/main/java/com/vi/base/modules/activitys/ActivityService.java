/*
 - Version Number 0.0.1
*/

package com.vi.base.modules.activitys;

import java.util.Date;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.vi.corelib.filter.FilterService;
import com.vi.model.dto.ActivityDTO;

public interface ActivityService extends FilterService<ActivityDTO> {


	ResponseEntity<Map<String, Object>> getCalendarData(Date startDate, Date endDate, Long userSeqNo);

}
