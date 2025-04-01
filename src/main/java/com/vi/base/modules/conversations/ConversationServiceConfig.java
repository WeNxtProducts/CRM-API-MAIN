/*
 - Version Number 0.0.1
*/

package com.vi.base.modules.conversations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConversationServiceConfig {

	@Bean
	public ConversationPersistent conversationPersistence() {
		return new ConversationJPAAdapter();
	}

	@Bean
	public ConversationService conversationService() {
		return new ConversationServiceImpl(conversationPersistence());
	}
}
