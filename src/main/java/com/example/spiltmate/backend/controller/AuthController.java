package com.example.spiltmate.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spiltmate.backend.dto.CreateUserRequest;
import com.example.spiltmate.backend.dto.LoginRequest;
import com.example.spiltmate.backend.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
	
	private final AuthService authService;
	
	@PostMapping("/signUp")
	public ResponseEntity<?> signUp (@RequestBody CreateUserRequest request) throws Exception {
		return new ResponseEntity<>(authService.addUser(request), HttpStatus.CREATED);
	}
	
	@PostMapping("/logIn")
	public ResponseEntity<?> signIn(@RequestBody LoginRequest request){
		return new ResponseEntity<>(authService.signIn(request), HttpStatus.ACCEPTED);
	}
	
	

}
