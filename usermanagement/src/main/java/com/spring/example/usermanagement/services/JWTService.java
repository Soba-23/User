package com.spring.example.usermanagement.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;

import com.spring.example.usermanagement.entity.User;

public interface JWTService {

	String extractUserName(String token);
	
	String generateToken(UserDetails userDetails);
	
	boolean isTokenValid(String token, UserDetails userDetails);

	String generateRefreshToken(Map<String, Object> extraClaims, UserDetails userDetails);
}
