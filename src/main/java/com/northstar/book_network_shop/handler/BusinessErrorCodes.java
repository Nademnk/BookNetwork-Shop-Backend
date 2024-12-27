package com.northstar.book_network_shop.handler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import org.springframework.http.HttpStatus;
import static org.springframework.http.HttpStatus.NOT_IMPLEMENTED;
import lombok.Getter;
import static org.springframework.http.HttpStatus.FORBIDDEN;


public enum BusinessErrorCodes {

	NO_CODE(0, NOT_IMPLEMENTED, "No code"),
	
	INCORRECT_CURRENT_PASSWORD(300, BAD_REQUEST, "Current password is incorect"),
	
	NEW_PASSWORD_DOES_NOT_MATCH(301, BAD_REQUEST, "The new password does not match"),
	
	ACCOUNT_DISABLED(303, FORBIDDEN, "User account is disabled"),
	
	BAD_CREDENTIALS(304, FORBIDDEN, "Login again / or password is incorrect"),
	
	ACCOUNT_LOCKED(302, FORBIDDEN, "User account is locked"),;
	
	@Getter
	private final int code;
	
	@Getter
	private final String description;
	
	@Getter
	private final HttpStatus httpStatus;
	
	private BusinessErrorCodes(int code,  HttpStatus httpStatus,String description) {
		this.code = code;
		this.description = description;
		this.httpStatus = httpStatus;
	}
	
}
