package com.example.spiltmate.backend.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExpenseSplitResponse {
	
	private Long splitId;
	
	private Long userId;
	
	private String userName;
	
	private Double shareAmount;
	
	private LocalDateTime createdAt;
	
	private LocalDateTime updatedAt;

}
