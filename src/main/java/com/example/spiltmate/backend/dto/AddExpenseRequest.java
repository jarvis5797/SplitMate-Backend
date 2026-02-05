package com.example.spiltmate.backend.dto;

import java.util.List;


import lombok.Data;

@Data
public class AddExpenseRequest {
	
	private String description;
	
	private Double amount;
	
	private Long paidBy;
	
	private Long groupId;
	
	private List<SplitDetail> splitDetails;

}
