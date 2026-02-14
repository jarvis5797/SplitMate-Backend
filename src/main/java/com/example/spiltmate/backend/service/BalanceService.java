package com.example.spiltmate.backend.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.spiltmate.backend.dto.GroupBalanceResponse;
import com.example.spiltmate.backend.dto.UserBalance;
import com.example.spiltmate.backend.entity.Expense;
import com.example.spiltmate.backend.entity.Group;
import com.example.spiltmate.backend.exception.ResourceNotFoundException;
import com.example.spiltmate.backend.repository.ExpenseRepository;
import com.example.spiltmate.backend.repository.GroupRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BalanceService {
	
	private final ExpenseRepository expenseRepository;
	
	private final GroupRepository groupRepository;
	
	public GroupBalanceResponse calculateGroupBalance(Long groupId) {
		
		Group group = groupRepository.findById(groupId).orElseThrow(()-> new ResourceNotFoundException("No group exists with id "+ groupId));
		
		List<Expense> expenses = expenseRepository.findByGroupId(groupId);
		
		Map<Long, Double> balanceMap = new HashMap<>();
		
		group.getMembers().forEach(member-> balanceMap.put(member.getId(), 0.0));
		
		expenses.forEach(expense-> {
			balanceMap.merge(expense.getPaidBy().getId(), 
					expense.getAmount(), 
					Double::sum
				);
			
		expense.getSplits().forEach(split->
			balanceMap.merge(split.getUser().getId(), 
					-split.getShareAmount(), 
					Double::sum
					)
				);
			
		});
		
		List<UserBalance> balances = group.getMembers().stream()
											.map(member -> UserBalance.builder()
																		.userId(member.getId())
																		.userName(member.getUsername())
																		.balance(balanceMap.get(member.getId())).build()).collect(Collectors.toList());
		
		return GroupBalanceResponse.builder()
						.balances(balances)
						.groupId(groupId).build();
				
		
	}

}
