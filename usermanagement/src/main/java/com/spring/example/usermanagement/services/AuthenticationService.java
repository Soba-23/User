package com.spring.example.usermanagement.services;

import com.spring.example.usermanagement.dto.JwtAuthenticationResponse;
import com.spring.example.usermanagement.dto.RefreshTokenRequest;
import com.spring.example.usermanagement.dto.SignInRequest;
import com.spring.example.usermanagement.dto.SignUpRequest;
import com.spring.example.usermanagement.entity.User;

public interface AuthenticationService {

	User signup(SignUpRequest signUpRequest);
	
	JwtAuthenticationResponse signin(SignInRequest signinRequest);
	
	JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
