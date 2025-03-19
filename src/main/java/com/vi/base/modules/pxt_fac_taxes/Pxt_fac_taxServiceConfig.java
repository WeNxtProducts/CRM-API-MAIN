/*
 - Version Number 0.0.1
*/

package com.vi.base.modules.pxt_fac_taxes;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Pxt_fac_taxServiceConfig {

	@Bean
	public Pxt_fac_taxPersistent pxt_fac_taxPersistence() {
		return new Pxt_fac_taxJPAAdapter();
	}

	@Bean
	public Pxt_fac_taxService pxt_fac_taxService() {
		return new Pxt_fac_taxServiceImpl(pxt_fac_taxPersistence());
	}
}
