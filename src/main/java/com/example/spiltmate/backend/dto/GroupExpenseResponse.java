package com.example.spiltmate.backend.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GroupExpenseResponse {
	
	private Long expesneId;
	
	private Long paidBy;
	
	private String paidByUserName;
	
	private BigDecimal amount;
	
	private LocalDateTime createdAt;
	
	private LocalDateTime updatedAt;

}
