/*
 - Version Number 0.0.1
*/

package com.vi.base.modules.activitylogs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ActivityLogServiceConfig {

	@Bean
	public ActivityLogPersistent activityLogPersistence() {
		return new ActivityLogJPAAdapter();
	}

	@Bean
	public ActivityLogService activityLogService() {
		return new ActivityLogServiceImpl(activityLogPersistence());
	}
}
