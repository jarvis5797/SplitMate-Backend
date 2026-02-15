package com.example.spiltmate.backend.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class SplitDetail {
	
	private BigDecimal amount;
	
	private Long userId;

}
