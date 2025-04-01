/*
 - Version Number 0.0.1
*/

package com.vi.extended.modules.tasks;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TaskServiceConfigCustom {

	@Bean
	public TaskPersistentCustom taskPersistenceCustom() {
		return new TaskJPAAdapterCustom();
	}

	@Bean
	public TaskServiceCustom taskServiceCustom() {
		return new TaskServiceImplCustom(taskPersistenceCustom());
	}
}
