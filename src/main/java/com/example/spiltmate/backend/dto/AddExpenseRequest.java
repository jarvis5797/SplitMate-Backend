package com.example.spiltmate.backend.dto;

import java.math.BigDecimal;
import java.util.List;


import lombok.Data;

@Data
public class AddExpenseRequest {
	
	private String description;
	
	private BigDecimal amount;
	
	private Long paidBy;
	
	private Long groupId;
	
	private List<SplitDetail> splitDetails;

}
