package edu.douglaslima.spring.springsecurity.config;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import edu.douglaslima.spring.springsecurity.model.User;
import edu.douglaslima.spring.springsecurity.repository.UserRepository;

@Service
public class SecurityDatabaseService implements UserDetailsService {
	
	@Autowired
	private UserRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String username) {
		Optional<User> user = this.repository.findByUsername(username);
		if (user.isEmpty()) {
			throw new UsernameNotFoundException(username);
		}
		Set<GrantedAuthority> authorities = new HashSet<>();
		user.get().getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority("ROLE_" + role)));
		return org.springframework.security.core.userdetails.User
				.builder()
				.username(user.get().getUsername())
				.password(user.get().getPassword())
				.authorities(authorities)
				.build();
	}

}
