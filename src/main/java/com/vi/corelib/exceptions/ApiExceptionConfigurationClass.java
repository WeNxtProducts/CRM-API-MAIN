package com.vi.corelib.exceptions;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan(basePackageClasses = ApiExceptionHandler.class)
@Configuration

public class ApiExceptionConfigurationClass {
}
