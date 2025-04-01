/*
 - Version Number 0.0.1
*/

package com.vi.extended.modules.fileattributes;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FileAttributesServiceConfigCustom {

	@Bean
	public FileAttributesPersistentCustom fileAttributesPersistenceCustom() {
		return new FileAttributesJPAAdapterCustom();
	}

	@Bean
	public FileAttributesServiceCustom fileAttributesServiceCustom() {
		return new FileAttributesServiceImplCustom();
	}
}
