/*
 - Version Number 0.0.1
*/

package com.vi.extended.modules.conversations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConversationServiceConfigCustom {

	@Bean
	public ConversationPersistentCustom conversationPersistenceCustom() {
		return new ConversationJPAAdapterCustom();
	}

	@Bean
	public ConversationServiceCustom conversationServiceCustom() {
		return new ConversationServiceImplCustom(conversationPersistenceCustom());
	}
}
