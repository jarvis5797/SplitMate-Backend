package com.example.spiltmate.backend.service;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.springframework.stereotype.Service;

import com.example.spiltmate.backend.dto.SettlementDto;
import com.example.spiltmate.backend.dto.UserBalance;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SettlementService {
	
	public List<SettlementDto> settleBalances(List<UserBalance> balances){
		
		Queue<UserBalance> creditors = new LinkedList<UserBalance>();
		Queue<UserBalance> debitors = new LinkedList<UserBalance>();
		
		balances.forEach(balance -> {
		    int compareAmount = balance.getBalance().compareTo(BigDecimal.ZERO);

		    if (compareAmount > 0) {
		        creditors.add(balance);
		    } else if (compareAmount < 0) {
		        debitors.add(balance);
		    }
		});

		
		List<SettlementDto> settlements = new LinkedList<SettlementDto>();
		
		while(!creditors.isEmpty() && !debitors.isEmpty()) {
			
			UserBalance creditor  = creditors.poll();
			UserBalance debitor = debitors.poll();
			
			BigDecimal amount = creditor.getBalance().min(debitor.getBalance().abs());
			
			settlements.add(SettlementDto.builder()
						.fromUserId(debitor.getUserId())
						.fromUserName(debitor.getUserName())
						.toUserId(creditor.getUserId())
						.toUserName(creditor.getUserName())
						.amount(amount)
						.build()
					);
			creditor.setBalance(creditor.getBalance().subtract(amount));
			debitor.setBalance(debitor.getBalance().add(amount));
			
			if(creditor.getBalance().signum()>0) creditors.add(creditor);
			if(debitor.getBalance().signum()<0) debitors.add(debitor);
			
		}
		
		return settlements;
 	}

}
