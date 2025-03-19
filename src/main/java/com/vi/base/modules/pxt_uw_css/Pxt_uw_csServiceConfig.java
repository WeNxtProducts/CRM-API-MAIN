/*
 - Version Number 0.0.1
*/

package com.vi.base.modules.pxt_uw_css;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Pxt_uw_csServiceConfig {

	@Bean
	public Pxt_uw_csPersistent pxt_uw_csPersistence() {
		return new Pxt_uw_csJPAAdapter();
	}

	@Bean
	public Pxt_uw_csService pxt_uw_csService() {
		return new Pxt_uw_csServiceImpl(pxt_uw_csPersistence());
	}
}
