/*
 - Version Number 0.0.1
*/

package com.vi.extended.modules.pxt_fac_place_dfns;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Pxt_fac_place_dfnServiceConfigCustom {

	@Bean
	public Pxt_fac_place_dfnPersistentCustom pxt_fac_place_dfnPersistenceCustom() {
		return new Pxt_fac_place_dfnJPAAdapterCustom();
	}

	@Bean
	public Pxt_fac_place_dfnServiceCustom pxt_fac_place_dfnServiceCustom() {
		return new Pxt_fac_place_dfnServiceImplCustom(pxt_fac_place_dfnPersistenceCustom());
	}
}