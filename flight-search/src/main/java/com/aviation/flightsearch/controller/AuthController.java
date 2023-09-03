package com.aviation.flightsearch.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aviation.flightsearch.dto.request.LoginRequest;
import com.aviation.flightsearch.dto.request.RefreshRequest;
import com.aviation.flightsearch.dto.request.RegisterRequest;
import com.aviation.flightsearch.dto.response.AuthResponse;
import com.aviation.flightsearch.service.AuthService;

@RestController
@RequestMapping(path = "/api/auth")
public class AuthController {
	
	private AuthService authService;
	
	public AuthController(AuthService authService) {
		this.authService = authService;
    }
	
	@PostMapping("/register")
	public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest registerRequest) {
		return authService.register(registerRequest);
	}
	
	@PostMapping("/login")
	public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
		return authService.login(loginRequest);
	}
	
	@PostMapping("/refresh")
	public ResponseEntity<AuthResponse> refresh(@RequestBody RefreshRequest refreshRequest) {
		return authService.refresh(refreshRequest);
	}

}
