package edu.douglaslima.spring.springsecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests(authManager -> authManager
				.requestMatchers("/").permitAll()
				.requestMatchers("/user").hasAnyRole("USER", "ADMIN")
				.requestMatchers("/admin").hasRole("ADMIN")
				.anyRequest().authenticated()
			)
			.formLogin(Customizer.withDefaults())
			.httpBasic(Customizer.withDefaults());
		return http.build();
	}

	@Bean
	public InMemoryUserDetailsManager userDetailsService() {
		UserDetails user = User.builder()
				.username("user")
				.password(this.encoder.encode("123456"))
				.roles("USER")
				.build();
		UserDetails admin = User.builder()
				.username("admin")
				.password(this.encoder.encode("123456"))
				.roles("USER", "ADMIN")
				.build();
		InMemoryUserDetailsManager inMemoryUsersManager = new InMemoryUserDetailsManager();
		inMemoryUsersManager.createUser(user);
		inMemoryUsersManager.createUser(admin);
		return inMemoryUsersManager;
	}
	
}
