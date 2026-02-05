package com.example.spiltmate.backend.dto;

import org.springframework.http.HttpStatus;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Response {
	
	private Object data;
	
	private HttpStatus httpStatus;

}
