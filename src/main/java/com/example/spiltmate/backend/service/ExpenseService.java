package com.example.spiltmate.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.spiltmate.backend.dto.AddExpenseRequest;
import com.example.spiltmate.backend.dto.ExpenseResponse;
import com.example.spiltmate.backend.dto.ExpenseSplitResponse;
import com.example.spiltmate.backend.dto.GroupExpenseResponse;
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
	
	public ExpenseResponse addExpense(AddExpenseRequest request) {
		
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
		
		Expense savedExpense =  expenseRepository.save(expense);
		
		return ExpenseResponse.builder()
				.expenseId(savedExpense.getId())
				.paidBy(savedExpense.getPaidBy().getId())
				.paidByUserName(savedExpense.getPaidBy().getUsername())
				.amount(savedExpense.getAmount())
				.createdAt(savedExpense.getCreatedAt())
				.updatedAt(savedExpense.getUpdatedAt())
				.splits(savedExpense.getSplits().stream()
						.map(split-> ExpenseSplitResponse.builder()
								.splitId(split.getId())
								.userId(split.getUser().getId())
								.userName(split.getUser().getUsername())
								.shareAmount(split.getShareAmount())
								.createdAt(split.getCreatedAt())
								.updatedAt(split.getUpdatedAt())
								.build())
						.collect(Collectors.toList()))
				.build();
			
	}
	
	public List<GroupExpenseResponse> getAllGroupExpense(Long groupId) {
		List<Expense> expenses = expenseRepository.findByGroupId(groupId);
		return expenses.stream()
				.map(expense -> GroupExpenseResponse.builder()
						.amount(expense.getAmount())
						.expesneId(expense.getId())
						.paidBy(expense.getPaidBy().getId())
						.paidByUserName(expense.getPaidBy().getUsername())
						.createdAt(expense.getCreatedAt())
						.updatedAt(expense.getUpdatedAt())
						.build())
				.collect(Collectors.toList()
			);
	}

}
