package com.spring.example.usermanagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.example.usermanagement.entity.Role;
import com.spring.example.usermanagement.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	
	Optional<User> findByEmail(String email);
	
	User findByRole(Role role);
}
