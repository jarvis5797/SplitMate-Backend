package com.example.spiltmate.backend.service;

import java.util.HashSet;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.spiltmate.backend.dto.CreateGroupRequest;
import com.example.spiltmate.backend.dto.Response;
import com.example.spiltmate.backend.entity.Group;
import com.example.spiltmate.backend.entity.User;
import com.example.spiltmate.backend.exception.ResourceNotFoundException;
import com.example.spiltmate.backend.repository.GroupRepository;
import com.example.spiltmate.backend.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GroupService {
	
	private final UserRepository userRepository;
	
	private final GroupRepository groupRepository;
	
	public Response createGroup(CreateGroupRequest request) throws Exception {
		User user = userRepository.findById(request.getCreatedById()).orElseThrow(()-> new ResourceNotFoundException("user does not exits!"));
		
		try {
			Group group  = Group.builder()
								.createdBy(user)
								.name(request.getName())
								.members(new HashSet<User>()).build();
			group.getMembers().add(user);
			groupRepository.save(group);
										
		}catch (Exception e) {
			throw new Exception("Unable to create group due to "+e.getMessage());
		}
		
		return Response.builder()
				.data("Group Created")
				.httpStatus(HttpStatus.CREATED)
				.build();
	}
	
	public Response getAllGroups(Long userId) {
		return  Response.builder()
				.data(groupRepository.findByUserId(userId))
				.httpStatus(HttpStatus.OK)
				.build();
	}

}
