/*
 - Version Number 0.0.1
*/

package com.vi.base.modules.ljmemailtemplates;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LjmEmailTemplateServiceConfig {

	@Bean
	public LjmEmailTemplatePersistent ljmEmailTemplatePersistence() {
		return new LjmEmailTemplateJPAAdapter();
	}

	@Bean
	public LjmEmailTemplateService ljmEmailTemplateService() {
		return new LjmEmailTemplateServiceImpl(ljmEmailTemplatePersistence());
	}
}
