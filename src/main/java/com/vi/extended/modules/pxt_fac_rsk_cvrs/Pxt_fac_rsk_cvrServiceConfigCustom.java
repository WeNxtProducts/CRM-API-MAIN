/*
 - Version Number 0.0.1
*/

package com.vi.extended.modules.pxt_fac_rsk_cvrs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Pxt_fac_rsk_cvrServiceConfigCustom {

	@Bean
	public Pxt_fac_rsk_cvrPersistentCustom pxt_fac_rsk_cvrPersistenceCustom() {
		return new Pxt_fac_rsk_cvrJPAAdapterCustom();
	}

	@Bean
	public Pxt_fac_rsk_cvrServiceCustom pxt_fac_rsk_cvrServiceCustom() {
		return new Pxt_fac_rsk_cvrServiceImplCustom(pxt_fac_rsk_cvrPersistenceCustom());
	}
}