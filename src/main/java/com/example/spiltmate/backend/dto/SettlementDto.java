package com.example.spiltmate.backend.dto;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class SettlementDto {
	
	private Long fromUserId;
	
	private String fromUserName;
	
	private Long toUserId;
	
	private String toUserName;
	
	private BigDecimal amount;

}
