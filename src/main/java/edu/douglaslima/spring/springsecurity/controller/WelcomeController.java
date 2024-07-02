package edu.douglaslima.spring.springsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest-api")
public class WelcomeController {

	@GetMapping("/welcome")
	public String welcome() {
		return "Welcome to my Spring Boot Web API!";
	}
	
	@GetMapping("/user")
	public String user() {
		return "Authorized user";
	}
	
	@GetMapping("/admin")
	public String admin() {
		return "Authorized administrator";
	}

	@GetMapping("/sobre")
	public String sobre() {
		return "Informações sobre esta API REST:" +
				"<ul>" +
					"<li>Autor: Douglas Lima</li>" +
					"<li>Desde: 01/07/2024</li>" +
					"<li>Link do repositório: <a href='https://github.com/douglaslima-pro/java-spring-security'>https://github.com/douglaslima-pro/java-spring-security</a></li>" +
				"</ul>";
	}
	
}
