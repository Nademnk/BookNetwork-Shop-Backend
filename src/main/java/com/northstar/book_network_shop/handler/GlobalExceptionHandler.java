package com.northstar.book_network_shop.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.LockedException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	
	public ResponseEntity<ExceptionResponse> handlerException(LockedException exp){
		
	}
	
}
