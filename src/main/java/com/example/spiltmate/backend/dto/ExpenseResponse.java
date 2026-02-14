package com.example.spiltmate.backend.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExpenseResponse {
	
	private Long expenseId;
	
	private Double amount;
	
	private Long paidBy;
	
	private String paidByUserName;
		
	private LocalDateTime createdAt;
	
	private LocalDateTime updatedAt;
	
	private List<ExpenseSplitResponse> splits;

}
