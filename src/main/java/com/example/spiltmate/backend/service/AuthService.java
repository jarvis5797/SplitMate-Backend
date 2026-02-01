package com.example.spiltmate.backend.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.spiltmate.backend.dto.CreateUserRequest;
import com.example.spiltmate.backend.entity.User;
import com.example.spiltmate.backend.exception.DuplicateResourceException;
import com.example.spiltmate.backend.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
	
	private final UserRepository userRepository;
	
	private final PasswordEncoder passwordEncoder;
	
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

}
