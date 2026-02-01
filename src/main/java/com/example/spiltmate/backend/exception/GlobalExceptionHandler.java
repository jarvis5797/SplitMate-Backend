package com.example.spiltmate.backend.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	public ResponseEntity<ErrorResponse> handleException(Exception ex, WebRequest request){
		
		ErrorResponse response = errorResponseBuilder(ex, request);
		response.setErrorCode("INTERNAL_SERVER_ERROR");
		
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(DuplicateResourceException.class)
	public ResponseEntity<ErrorResponse> handleDuplicate( DuplicateResourceException ex , WebRequest request){
		
		ErrorResponse response = errorResponseBuilder(ex, request);
		response.setErrorCode("DUPLICATE");
		
		return new ResponseEntity<>(response, HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(InvalidTokenException.class)
	public ResponseEntity<ErrorResponse> hadnleInvalidToken(InvalidTokenException ex, WebRequest request){
		
		ErrorResponse response = errorResponseBuilder(ex, request);
		response.setErrorCode("UNAUTHORIZED");
		
		return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
	}
	
	public ResponseEntity<ErrorResponse> handleResourceNotFound(ResourceNotFoundException ex, WebRequest request){
		
		ErrorResponse response = errorResponseBuilder(ex, request);
		response.setErrorCode("NOT_FOUND");
		
		return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
	}
	
	private ErrorResponse errorResponseBuilder(Exception ex, WebRequest request) {
		
		return ErrorResponse.builder()
				.timestamp(LocalDateTime.now())
				.message(ex.getMessage())
				.path(request.getDescription(false))
				.build();
	}
}
