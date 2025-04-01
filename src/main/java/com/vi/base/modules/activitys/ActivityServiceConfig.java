/*
 - Version Number 0.0.1
*/

package com.vi.base.modules.activitys;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ActivityServiceConfig {

	@Bean
	public ActivityPersistent activityPersistence() {
		return new ActivityJPAAdapter();
	}

	@Bean
	public ActivityService activityService() {
		return new ActivityServiceImpl(activityPersistence());
	}
}
