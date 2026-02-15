package com.example.spiltmate.backend.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class UserBalance {
	
	private Long userId;
	
	private String userName;
	
	private BigDecimal balance;

}
