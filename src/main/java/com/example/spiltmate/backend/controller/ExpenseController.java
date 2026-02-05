package com.example.spiltmate.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.spiltmate.backend.dto.AddExpenseRequest;
import com.example.spiltmate.backend.service.ExpenseService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/expense")
@RequiredArgsConstructor
public class ExpenseController {
	
	private final ExpenseService expenseService;
	
	@PostMapping
	public ResponseEntity<?> addExpense(@RequestBody AddExpenseRequest request){
		return new ResponseEntity<>(expenseService.addExpense(request), HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<?> getGroupExpenses(@RequestParam Long groupId){
		return new ResponseEntity<>(expenseService.getAllGroupExpense(groupId), HttpStatus.OK);
	}

}
