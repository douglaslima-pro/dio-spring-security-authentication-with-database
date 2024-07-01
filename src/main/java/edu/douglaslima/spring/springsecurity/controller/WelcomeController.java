package edu.douglaslima.spring.springsecurity.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableMethodSecurity(prePostEnabled = true)
public class WelcomeController {

	@GetMapping
	public String welcome() {
		return "Welcome to my Spring Boot Web API!";
	}
	
	@GetMapping("/user")
	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	public String user() {
		return "Authorized user";
	}
	
	@GetMapping("/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public String admin() {
		return "Authorized administrator";
	}
	
}
