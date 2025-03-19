/*
 - Version Number 0.0.1
*/

package com.vi.base.modules.pxt_fac_place_dfns;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Pxt_fac_place_dfnServiceConfig {

	@Bean
	public Pxt_fac_place_dfnPersistent pxt_fac_place_dfnPersistence() {
		return new Pxt_fac_place_dfnJPAAdapter();
	}

	@Bean
	public Pxt_fac_place_dfnService pxt_fac_place_dfnService() {
		return new Pxt_fac_place_dfnServiceImpl(pxt_fac_place_dfnPersistence());
	}
}
