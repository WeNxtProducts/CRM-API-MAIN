/*
 - Version Number 0.0.1
*/

package com.vi.base.modules.tasks;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TaskServiceConfig {

	@Bean
	public TaskPersistent taskPersistence() {
		return new TaskJPAAdapter();
	}

	@Bean
	public TaskService taskService() {
		return new TaskServiceImpl(taskPersistence());
	}
}
