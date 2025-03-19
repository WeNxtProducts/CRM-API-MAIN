/*
 - Version Number 0.0.1
*/

package com.vi.extended.modules.pxt_fac_hdrs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Pxt_fac_hdrServiceConfigCustom {

	@Bean
	public Pxt_fac_hdrPersistentCustom pxt_fac_hdrPersistenceCustom() {
		return new Pxt_fac_hdrJPAAdapterCustom();
	}

	@Bean
	public Pxt_fac_hdrServiceCustom pxt_fac_hdrServiceCustom() {
		return new Pxt_fac_hdrServiceImplCustom(pxt_fac_hdrPersistenceCustom());
	}
}