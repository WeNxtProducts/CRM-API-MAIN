/*
 - Version Number 0.0.1
*/

package com.vi.extended.modules.users;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserServiceConfigCustom {

	@Bean
	public UserPersistentCustom userPersistenceCustom() {
		return new UserJPAAdapterCustom();
	}

	@Bean
	public UserServiceCustom userServiceCustom() {
		return new UserServiceImplCustom(userPersistenceCustom());
	}
}
