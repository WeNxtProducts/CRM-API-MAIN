/*
 - Version Number 0.0.1
*/

package com.vi.extended.modules.leads;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LeadServiceConfigCustom {

	@Bean
	public LeadPersistentCustom leadPersistenceCustom() {
		return new LeadJPAAdapterCustom();
	}

	@Bean
	public LeadServiceCustom leadServiceCustom() {
		return new LeadServiceImplCustom(leadPersistenceCustom());
	}
}
