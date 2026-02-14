package com.example.spiltmate.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.spiltmate.backend.dto.AddExpenseRequest;
import com.example.spiltmate.backend.dto.Response;
import com.example.spiltmate.backend.service.ExpenseService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/expense")
@RequiredArgsConstructor
public class ExpenseController {
	
	private final ExpenseService expenseService;
	
	@PostMapping
	public ResponseEntity<?> addExpense(@RequestBody AddExpenseRequest request){
		return new ResponseEntity<>(Response.builder()
				.data(expenseService.addExpense(request))
				.httpStatus(HttpStatus.CREATED)
				.build(), 
				HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<?> getGroupExpenses(@RequestParam Long groupId){
		return new ResponseEntity<>(Response.builder()
				.data(expenseService.getAllGroupExpense(groupId))
				.httpStatus(HttpStatus.OK)
				.build(), 
				HttpStatus.OK
			);
	}

}
