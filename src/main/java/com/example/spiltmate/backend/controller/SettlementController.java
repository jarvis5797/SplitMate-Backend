package com.example.spiltmate.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spiltmate.backend.dto.GroupBalanceResponse;
import com.example.spiltmate.backend.dto.Response;
import com.example.spiltmate.backend.service.BalanceService;
import com.example.spiltmate.backend.service.SettlementService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/settlements")
@RequiredArgsConstructor
public class SettlementController {
	
	private final SettlementService settlementService;
	
	private final BalanceService balanceService;
	
	@GetMapping("/group/{groupId}")
	public ResponseEntity<?> getSettlement(@PathVariable Long groupId){
		
		GroupBalanceResponse balance = balanceService.calculateGroupBalance(groupId);
		
		return new ResponseEntity<>(Response.builder()
					.data(settlementService.settleBalances(balance.getBalances()))
					.httpStatus(HttpStatus.OK)
					.build() , HttpStatus.OK
				);
	}

}
