package com.example.spiltmate.backend.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "expense_groups")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Group {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "created_by")
	private User createdBy;
	
	@ManyToMany
	@JoinTable(
				name = "expense_group_members",
				joinColumns = @JoinColumn(name = "group_id"),
				inverseJoinColumns = @JoinColumn(name = "user_id")
			)
	private Set<User> members = new HashSet<>();
	
	@CreationTimestamp
	private LocalDateTime createdAt;
	
	@UpdateTimestamp
	private LocalDateTime updatedAt;
	

}
