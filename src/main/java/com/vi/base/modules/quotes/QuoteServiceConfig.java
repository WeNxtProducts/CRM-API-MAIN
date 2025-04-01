/*
 - Version Number 0.0.1
*/

package com.vi.base.modules.quotes;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuoteServiceConfig {

	@Bean
	public QuotePersistent quotePersistence() {
		return new QuoteJPAAdapter();
	}

	@Bean
	public QuoteService quoteService() {
		return new QuoteServiceImpl(quotePersistence());
	}
}
