/*
 - Version Number 0.0.1
*/

package com.vi.extended.modules.quotes;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuoteServiceConfigCustom {

	@Bean
	public QuotePersistentCustom quotePersistenceCustom() {
		return new QuoteJPAAdapterCustom();
	}

	@Bean
	public QuoteServiceCustom quoteServiceCustom() {
		return new QuoteServiceImplCustom(quotePersistenceCustom());
	}
}
