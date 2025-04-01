/*
 - Version Number 0.0.1
*/

package com.vi.extended.modules.enquirys;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EnquiryServiceConfigCustom {

	@Bean
	public EnquiryPersistentCustom enquiryPersistenceCustom() {
		return new EnquiryJPAAdapterCustom();
	}

	@Bean
	public EnquiryServiceCustom enquiryServiceCustom() {
		return new EnquiryServiceImplCustom(enquiryPersistenceCustom());
	}
}
