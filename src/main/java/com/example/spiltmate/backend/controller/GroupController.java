package com.example.spiltmate.backend.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.spiltmate.backend.dto.CreateGroupRequest;
import com.example.spiltmate.backend.service.GroupService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/group")
@RequiredArgsConstructor
public class GroupController {
	
	private final GroupService groupService;
	
	@PostMapping
	public ResponseEntity<?> createGroup(@RequestBody CreateGroupRequest request) throws Exception{
		return new ResponseEntity<>(groupService.createGroup(request), HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<?> getAllGroups(@RequestParam Long userId){
		return new ResponseEntity<>(groupService.getAllGroups(userId), HttpStatus.OK);
	}
	

}
