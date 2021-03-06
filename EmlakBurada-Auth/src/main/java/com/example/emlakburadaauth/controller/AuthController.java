package com.example.emlakburadaauth.controller;

import com.example.emlakburadaauth.dto.AuthRequest;
import com.example.emlakburadaauth.dto.AuthResponse;
import com.example.emlakburadaauth.service.AuthService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public final class AuthController {

	private final AuthService authService;

	// kullanici mailine ve şifresine göre login
	@PostMapping(value = "/auth")
	public ResponseEntity<AuthResponse> getToken(@RequestBody AuthRequest request) throws Exception {
		return new ResponseEntity<>(authService.getToken(request), HttpStatus.OK);
	}

}
