package com.northstar.book_network_shop.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
@Tag(name ="Authentication")
public class AuthenticationController {

	
	
	
	
	@PostMapping("/register")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public ResponseEntity<?> register(
			
			@RequestBody @Valid RegistrationRequest request
			
			
			) throws MessagingException{
		service.register(request);
		return ResponseEntity.accepted().build();
	}
	
	@PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody @Valid AuthenticationRequest request
    ){
		return ResponseEntity.ok(service.authenticate(request));
	}
	
	@GetMapping("/activate-account")
	public void confirm (
			
			@RequestParam String token
			
			) throws MessagingException {
		
		service.activateAccount(token);
	}
	
	
	
}
