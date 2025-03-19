/*
 - Version Number 0.0.1
*/

package com.vi.extended.modules.pxt_fac_taxes;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Pxt_fac_taxServiceConfigCustom {

	@Bean
	public Pxt_fac_taxPersistentCustom pxt_fac_taxPersistenceCustom() {
		return new Pxt_fac_taxJPAAdapterCustom();
	}

	@Bean
	public Pxt_fac_taxServiceCustom pxt_fac_taxServiceCustom() {
		return new Pxt_fac_taxServiceImplCustom(pxt_fac_taxPersistenceCustom());
	}
}