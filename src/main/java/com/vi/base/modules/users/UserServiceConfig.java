/*
 - Version Number 0.0.1
*/

package com.vi.base.modules.users;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserServiceConfig {

	@Bean
	public UserPersistent userPersistence() {
		return new UserJPAAdapter();
	}

	@Bean
	public UserService userService() {
		return new UserServiceImpl(userPersistence());
	}
}
