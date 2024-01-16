package com.spring.example.usermanagement.services.impl;

import java.util.HashMap;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.example.usermanagement.dto.JwtAuthenticationResponse;
import com.spring.example.usermanagement.dto.RefreshTokenRequest;
import com.spring.example.usermanagement.dto.SignInRequest;
import com.spring.example.usermanagement.dto.SignUpRequest;
import com.spring.example.usermanagement.entity.Role;
import com.spring.example.usermanagement.entity.User;
import com.spring.example.usermanagement.repository.UserRepository;
import com.spring.example.usermanagement.services.AuthenticationService;
import com.spring.example.usermanagement.services.JWTService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

	
	private final UserRepository userRepository;
	
	private final PasswordEncoder passwordEncoder;
	
	private final AuthenticationManager authenticationManager;
	
	private final JWTService jwtService;
	
	public User signup(SignUpRequest signUpRequest) {
		User user = new User();
		
		user.setEmail(signUpRequest.getEmail());
		user.setFirstname(signUpRequest.getFirstName());
		user.setSecondname(signUpRequest.getLastName());
		user.setRole(Role.USER);
		user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
		
		return userRepository.save(user);
	}
	
	public JwtAuthenticationResponse signin(SignInRequest signinRequest) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signinRequest.getEmail(), 
				signinRequest.getPassword()));
		
		var user = userRepository.findByEmail(signinRequest.getEmail()).orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
	    var jwt = jwtService.generateToken(user);
	    var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);
	    
	    JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
	    
	    jwtAuthenticationResponse.setToken(jwt);
	    jwtAuthenticationResponse.setRefreshToken(refreshToken);
	    
	    return jwtAuthenticationResponse;
	}
	
	public JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
		String userEmail = jwtService.extractUserName(refreshTokenRequest.getToken());
		User user = userRepository.findByEmail(userEmail).orElseThrow();
		if(jwtService.isTokenValid(refreshTokenRequest.getToken(), user)) {
			var jwt = jwtService.generateToken(user);
			
			JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
		    
		    jwtAuthenticationResponse.setToken(jwt);
		    jwtAuthenticationResponse.setRefreshToken(refreshTokenRequest.getToken());
		    
		    return jwtAuthenticationResponse;
		}
		return null;
	}
}
