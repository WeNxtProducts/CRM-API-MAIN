package com.vi.security.authorization;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan(basePackageClasses = AuthFilter.class)
@Configuration

public class AuthFilterConfigurationClass {
	
}
