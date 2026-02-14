package com.example.spiltmate.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spiltmate.backend.dto.Response;
import com.example.spiltmate.backend.service.BalanceService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/balance")
@RequiredArgsConstructor
public class BalanceController {
	
	private final BalanceService balanceService;
	
	@GetMapping("{groupId}")
	public ResponseEntity<?> getGroupBalance(@PathVariable Long groupId){
		return new ResponseEntity<>(Response.builder()
				.data(balanceService.calculateGroupBalance(groupId))
				.httpStatus(HttpStatus.OK)
				.build(),
				HttpStatus.OK
			);
	}

}
