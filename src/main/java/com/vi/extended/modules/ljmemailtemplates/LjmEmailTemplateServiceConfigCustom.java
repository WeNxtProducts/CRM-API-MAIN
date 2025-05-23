/*
 - Version Number 0.0.1
*/

package com.vi.extended.modules.ljmemailtemplates;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LjmEmailTemplateServiceConfigCustom {

	@Bean
	public LjmEmailTemplatePersistentCustom ljmEmailTemplatePersistenceCustom() {
		return new LjmEmailTemplateJPAAdapterCustom();
	}

	@Bean
	public LjmEmailTemplateServiceCustom ljmEmailTemplateServiceCustom() {
		return new LjmEmailTemplateServiceImplCustom(ljmEmailTemplatePersistenceCustom());
	}
}
