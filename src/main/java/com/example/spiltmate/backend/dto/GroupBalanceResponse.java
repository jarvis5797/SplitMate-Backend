package com.example.spiltmate.backend.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class GroupBalanceResponse {
	
	private Long groupId;
	
	private List<UserBalance> balances;

}
