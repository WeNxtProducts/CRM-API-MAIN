/*
 - Version Number 0.0.1
*/

package com.vi.base.modules.pxt_fac_part_comms;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Pxt_fac_part_commServiceConfig {

	@Bean
	public Pxt_fac_part_commPersistent pxt_fac_part_commPersistence() {
		return new Pxt_fac_part_commJPAAdapter();
	}

	@Bean
	public Pxt_fac_part_commService pxt_fac_part_commService() {
		return new Pxt_fac_part_commServiceImpl(pxt_fac_part_commPersistence());
	}
}
