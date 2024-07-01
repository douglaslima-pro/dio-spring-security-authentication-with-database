package edu.douglaslima.spring.springsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	@Bean
	public InMemoryUserDetailsManager userDetailsService() {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		UserDetails user = User.builder()
				.username("user")
				.password("{bcrypt}" + encoder.encode("123456"))
				.roles("USER")
				.build();
		UserDetails admin = User.builder()
				.username("admin")
				.password("{bcrypt}" + encoder.encode("123456"))
				.roles("USER", "ADMIN")
				.build();
		InMemoryUserDetailsManager inMemoryUsersManager = new InMemoryUserDetailsManager();
		inMemoryUsersManager.createUser(user);
		inMemoryUsersManager.createUser(admin);
		return inMemoryUsersManager;
	}
	
}
