/*
 - Version Number 0.0.1
*/

package com.vi.base.modules.pxt_fac_rsk_cvrs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Pxt_fac_rsk_cvrServiceConfig {

	@Bean
	public Pxt_fac_rsk_cvrPersistent pxt_fac_rsk_cvrPersistence() {
		return new Pxt_fac_rsk_cvrJPAAdapter();
	}

	@Bean
	public Pxt_fac_rsk_cvrService pxt_fac_rsk_cvrService() {
		return new Pxt_fac_rsk_cvrServiceImpl(pxt_fac_rsk_cvrPersistence());
	}
}
