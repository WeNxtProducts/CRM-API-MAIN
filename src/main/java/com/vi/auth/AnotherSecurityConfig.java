package com.vi.auth;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AnotherSecurityConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public FilterRegistrationBean<CustomAuthTokenFilter> loggingFilter(RestTemplate restTemplate) {
        FilterRegistrationBean<CustomAuthTokenFilter> registrationBean = new FilterRegistrationBean<>();

        CustomAuthTokenFilter customFilter = new CustomAuthTokenFilter(restTemplate);
        registrationBean.setFilter(customFilter);
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }
}
