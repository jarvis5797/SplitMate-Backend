package com.example.spiltmate.backend.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GroupExpenseResponse {
	
	private Long expesneId;
	
	private Long paidBy;
	
	private String paidByUserName;
	
	private Double amount;
	
	private LocalDateTime createdAt;
	
	private LocalDateTime updatedAt;

}
