package edu.douglaslima.spring.springsecurity.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class BeansFactory {
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
