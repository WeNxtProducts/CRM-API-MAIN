/*
 - Version Number 0.0.1
*/

package com.vi.base.modules.ljmemailparams;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LjmEmailParamServiceConfig {

	@Bean
	public LjmEmailParamPersistent ljmEmailParamPersistence() {
		return new LjmEmailParamJPAAdapter();
	}

	@Bean
	public LjmEmailParamService ljmEmailParamService() {
		return new LjmEmailParamServiceImpl(ljmEmailParamPersistence());
	}
}
