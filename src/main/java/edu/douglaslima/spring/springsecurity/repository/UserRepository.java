package edu.douglaslima.spring.springsecurity.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import edu.douglaslima.spring.springsecurity.model.User;

@Component
public interface UserRepository extends JpaRepository<User, Integer>{

	@Query("SELECT e FROM User e JOIN FETCH e.roles WHERE e.username = (:username)")
	public Optional<User> findByUsername(@Param("username") String username);
	
}
