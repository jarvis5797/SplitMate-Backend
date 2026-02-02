package com.example.spiltmate.backend.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.spiltmate.backend.dto.CreateUserRequest;
import com.example.spiltmate.backend.dto.LoginRequest;
import com.example.spiltmate.backend.dto.UserResponse;
import com.example.spiltmate.backend.entity.User;
import com.example.spiltmate.backend.exception.AuthenticationException;
import com.example.spiltmate.backend.exception.DuplicateResourceException;
import com.example.spiltmate.backend.exception.ResourceNotFoundException;
import com.example.spiltmate.backend.repository.UserRepository;
import com.example.spiltmate.backend.util.JwtUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
	
	private final UserRepository userRepository;
	
	private final PasswordEncoder passwordEncoder;
	
	private final JwtUtil jwtUtil;
	
	public String addUser(CreateUserRequest request) throws Exception {
		
		User user = userRepository.findByEmail(request.getEmail()).orElse(null);
		if(user== null) {
			try {
				userRepository.save(User.builder()
						.username(request.getUsername())
						.email(request.getEmail())
						.password(passwordEncoder.encode(request.getPassword()))
						.build());
			}catch (Exception e) {
				throw new Exception("Unable to create user due to - "+ e.getMessage());
			}
		} else {
			throw new DuplicateResourceException("Email already exists!");
		}
		return "User Created";
	}
	
	public UserResponse signIn(LoginRequest request) {
		User user  = userRepository.findByEmail(request.getEmail()).orElseThrow(()-> new AuthenticationException("Email or Password is incorrect!"));
		
		if(!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
			throw new AuthenticationException("Email or Password is incorrect!");
		}
		
		String token  = jwtUtil.generateToken(user.getEmail());
		return UserResponse.builder()
				.id(user.getId())
				.username(user.getUsername())
				.email(user.getEmail())
				.createdAt(user.getCreatedAt())
				.updatedat(user.getUpdatedAt())
				.token(token)
				.build();
				
	}

}
