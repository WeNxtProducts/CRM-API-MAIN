/*
 - Version Number 0.0.1
*/

package com.vi.extended.modules.activitys;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ActivityServiceConfigCustom {

	@Bean
	public ActivityPersistentCustom activityPersistenceCustom() {
		return new ActivityJPAAdapterCustom();
	}

	@Bean
	public ActivityServiceCustom activityServiceCustom() {
		return new ActivityServiceImplCustom(activityPersistenceCustom());
	}
}
