package com.example.spiltmate.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import com.example.spiltmate.backend.entity.Group;

@EnableJpaRepositories
public interface GroupRepository extends JpaRepository<Group, Long> {
	
	@Query(value = "select e.* from expense_groups e join expense_group_members em on e.id = em.group_id where em.user_id = :userId", nativeQuery=true)
	List<Group> findByUserId(@Param("userId") Long userId);

}
