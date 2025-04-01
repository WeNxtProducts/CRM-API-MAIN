/*
 - Version Number 0.0.1
*/

package com.vi.extended.modules.activitylogs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ActivityLogServiceConfigCustom {

	@Bean
	public ActivityLogPersistentCustom activityLogPersistenceCustom() {
		return new ActivityLogJPAAdapterCustom();
	}

	@Bean
	public ActivityLogServiceCustom activityLogServiceCustom() {
		return new ActivityLogServiceImplCustom(activityLogPersistenceCustom());
	}
}
