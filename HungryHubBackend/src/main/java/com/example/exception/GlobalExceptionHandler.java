package com.example.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	

	@ExceptionHandler(UserException.class)
	public ResponseEntity<ExceptionFromat> userException(UserException ex , WebRequest request){
		return new ResponseEntity<ExceptionFromat>(new ExceptionFromat(LocalDateTime.now(),ex.getMessage(),request.getDescription(false)),HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<ExceptionFromat> handlerNotFoundException(NoHandlerFoundException ex ,WebRequest request){
		ExceptionFromat ef = new ExceptionFromat(LocalDateTime.now(),ex.getMessage(),request.getDescription(false));
		return new ResponseEntity<ExceptionFromat>(ef,HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ExceptionFromat> globalException(Exception ex , WebRequest request){
		return new ResponseEntity<ExceptionFromat>(new ExceptionFromat(LocalDateTime.now(),ex.getMessage(),request.getDescription(false)),HttpStatus.BAD_REQUEST);
	}

}
