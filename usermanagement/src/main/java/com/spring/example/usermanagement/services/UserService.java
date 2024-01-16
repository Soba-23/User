package com.spring.example.usermanagement.services;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {

	UserDetailsService userDetailsService();
}
