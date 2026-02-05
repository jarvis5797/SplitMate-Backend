package com.example.spiltmate.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.example.spiltmate.backend.entity.Expense;

@EnableJpaRepositories
public interface ExpenseRepository extends JpaRepository<Expense, Long>{
	
	
	List<Expense> findByGroupId(Long groupId);

}
