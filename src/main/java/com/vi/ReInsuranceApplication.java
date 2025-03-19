package com.vi;

import com.vi.security.authorization.EnableAuthFilter;
import com.vi.corelib.exceptions.EnableApiException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.IOException;


@EnableAuthFilter
@EnableApiException
@SpringBootApplication
public class ReInsuranceApplication {
	public static void main(String[] args) throws IOException {
		SpringApplication.run(ReInsuranceApplication.class, args);

	}

}




