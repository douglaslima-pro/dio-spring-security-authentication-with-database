package edu.douglaslima.spring.springsecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	
	@Autowired
	private SecurityDatabaseService securityService;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Autowired
	public void globalUserDetails(AuthenticationManagerBuilder authManager) throws Exception {
		authManager.userDetailsService(this.securityService).passwordEncoder(this.encoder);
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests(authManager -> authManager
				.requestMatchers("/rest-api/sobre").permitAll()
				.requestMatchers("/rest-api/welcome").permitAll()
				.requestMatchers("/rest-api/user").authenticated()
				.requestMatchers("/rest-api/admin").hasRole("ADMIN")
				.anyRequest().authenticated()
			)
			.formLogin(Customizer.withDefaults())
			.httpBasic(Customizer.withDefaults());
		return http.build();
	}
	
}
