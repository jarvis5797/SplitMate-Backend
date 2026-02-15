package com.example.spiltmate.backend.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(
		name = "settlements",
		indexes = {
				@Index(name = "idx_from_user" , columnList = "fromUserId"),
				@Index(name = "idx_to_user", columnList = "toUserId"),
				@Index(name = "idx_group_id", columnList = "groupId")
			}
		)
@Data
public class Settlement {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private Long groupId;
	
	@Column(nullable = false)
	private Long fromUserId;
	
	@Column(nullable = false)
	private Long toUserId;
	
	@Column(nullable = false, precision = 15, scale = 2)
	private BigDecimal amount;
	
	@Column(nullable = false)
	private boolean settled = false;
	
	@CreationTimestamp
	private LocalDateTime createdAt;
	
	@UpdateTimestamp
	private LocalDateTime updatedAt;
	

}
