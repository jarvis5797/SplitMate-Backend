package com.example.spiltmate.backend.service;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.spiltmate.backend.dto.AddExpenseRequest;
import com.example.spiltmate.backend.dto.Response;
import com.example.spiltmate.backend.dto.SplitDetail;
import com.example.spiltmate.backend.entity.Expense;
import com.example.spiltmate.backend.entity.ExpenseSplit;
import com.example.spiltmate.backend.entity.Group;
import com.example.spiltmate.backend.entity.User;
import com.example.spiltmate.backend.exception.ResourceNotFoundException;
import com.example.spiltmate.backend.repository.ExpenseRepository;
import com.example.spiltmate.backend.repository.GroupRepository;
import com.example.spiltmate.backend.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExpenseService {
	
	private final ExpenseRepository expenseRepository;
	
	private final GroupRepository groupRepository;
	
	private final UserRepository userRepository;
	
	public Response addExpense(AddExpenseRequest request) {
		
		User user = userRepository.findById(request.getPaidBy()).orElseThrow(()-> new ResourceNotFoundException("Unable to find user!"));
		
		Group group  = groupRepository.findById(request.getGroupId()).orElseThrow(() -> new ResourceNotFoundException("Unable to find group!"));
		
		Expense expense = Expense.builder()
			.amount(request.getAmount())
			.description(request.getDescription())
			.group(group) 
			.paidBy(user)
			.splits(new ArrayList<ExpenseSplit>())
			.build();
				
		for(SplitDetail splitDetail : request.getSplitDetails()) {
			
			ExpenseSplit split = ExpenseSplit.builder()
				.shareAmount(splitDetail.getAmount())
				.user(userRepository.findById(splitDetail.getUserId()).orElseThrow(()-> new ResourceNotFoundException("Unable to find member"+ splitDetail.getUserId())))
				.expense(expense)
				.build();
				
			expense.getSplits().add(split);
		}
		
		return Response.builder()
				.data(expenseRepository.save(expense))
				.httpStatus(HttpStatus.CREATED)
				.build();
			
	}
	
	public Response getAllGroupExpense(Long groupId) {
		return Response.builder()
				.data(expenseRepository.findByGroupId(groupId))
				.httpStatus(HttpStatus.OK)
				.build();
	}

}
