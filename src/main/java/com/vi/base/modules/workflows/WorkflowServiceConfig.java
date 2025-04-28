/*
 - Version Number 0.0.1
*/

package com.vi.base.modules.workflows;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WorkflowServiceConfig {

	@Bean
	public WorkflowPersistent workflowPersistence() {
		return new WorkflowJPAAdapter();
	}

	@Bean
	public WorkflowService workflowService() {
		return new WorkflowServiceImpl(workflowPersistence());
	}
}
