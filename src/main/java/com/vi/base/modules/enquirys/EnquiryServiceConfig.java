/*
 - Version Number 0.0.1
*/

package com.vi.base.modules.enquirys;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EnquiryServiceConfig {

	@Bean
	public EnquiryPersistent enquiryPersistence() {
		return new EnquiryJPAAdapter();
	}

	@Bean
	public EnquiryService enquiryService() {
		return new EnquiryServiceImpl(enquiryPersistence());
	}
}
