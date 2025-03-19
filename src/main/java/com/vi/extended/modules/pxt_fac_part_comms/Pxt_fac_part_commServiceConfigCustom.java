/*
 - Version Number 0.0.1
*/

package com.vi.extended.modules.pxt_fac_part_comms;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Pxt_fac_part_commServiceConfigCustom {

	@Bean
	public Pxt_fac_part_commPersistentCustom pxt_fac_part_commPersistenceCustom() {
		return new Pxt_fac_part_commJPAAdapterCustom();
	}

	@Bean
	public Pxt_fac_part_commServiceCustom pxt_fac_part_commServiceCustom() {
		return new Pxt_fac_part_commServiceImplCustom(pxt_fac_part_commPersistenceCustom());
	}
}