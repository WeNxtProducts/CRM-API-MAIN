/*
 - Version Number 0.0.1
*/

package com.vi.base.modules.pxt_fac_parts;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Pxt_fac_partServiceConfig {

	@Bean
	public Pxt_fac_partPersistent pxt_fac_partPersistence() {
		return new Pxt_fac_partJPAAdapter();
	}

	@Bean
	public Pxt_fac_partService pxt_fac_partService() {
		return new Pxt_fac_partServiceImpl(pxt_fac_partPersistence());
	}
}
