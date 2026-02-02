package com.example.spiltmate.backend.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
	
	private Long id;
	
	private String username;
	
	private String email;
	
	private String token;
	
	private LocalDateTime createdAt;
	
	private LocalDateTime updatedat;

}
