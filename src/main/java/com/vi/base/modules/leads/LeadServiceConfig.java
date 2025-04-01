/*
 - Version Number 0.0.1
*/

package com.vi.base.modules.leads;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LeadServiceConfig {

	@Bean
	public LeadPersistent leadPersistence() {
		return new LeadJPAAdapter();
	}

	@Bean
	public LeadService leadService() {
		return new LeadServiceImpl(leadPersistence());
	}
}
