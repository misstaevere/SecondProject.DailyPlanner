package com.qa.account.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
	
	@Bean
	public ModelMapper mapper() { // convert between Entities and DTOs
		return new ModelMapper();
	}

}
