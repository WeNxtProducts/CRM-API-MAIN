package com.vi.security;

import com.vi.corelib.utils.UrlUtility;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
				.allowedOrigins(UrlUtility.allowedOrigins())
				.allowedMethods(UrlUtility.allowedMethods())
				.allowedHeaders(UrlUtility.allowedHeaders());
	}
}
