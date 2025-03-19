/*
 - Version Number 0.0.1
*/

package com.vi.base.modules.pxt_fac_hdrs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Pxt_fac_hdrServiceConfig {

	@Bean
	public Pxt_fac_hdrPersistent pxt_fac_hdrPersistence() {
		return new Pxt_fac_hdrJPAAdapter();
	}

	@Bean
	public Pxt_fac_hdrService pxt_fac_hdrService() {
		return new Pxt_fac_hdrServiceImpl(pxt_fac_hdrPersistence());
	}
}
