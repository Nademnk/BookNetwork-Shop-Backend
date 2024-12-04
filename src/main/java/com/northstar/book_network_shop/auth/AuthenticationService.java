package com.northstar.book_network_shop.auth;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.northstar.book_network_shop.email.EmailService;
import com.northstar.book_network_shop.email.EmailTemplateName;
import com.northstar.book_network_shop.role.RoleRepository;
import com.northstar.book_network_shop.user.Token;
import com.northstar.book_network_shop.user.TokenRepository;
import com.northstar.book_network_shop.user.User;
import com.northstar.book_network_shop.user.UserRepository;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

	private final RoleRepository roleRepository;
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final TokenRepository tokenRepository;
	 private final EmailService emailService;
	 
	 @Value("${application.mailing.frontend.activation-url}")
	 private String activationUrl;

	public void register( RegistrationRequest request) throws MessagingException {
		
		var userRole = roleRepository.findByName("USER")
				.orElseThrow(() -> new IllegalStateException("ROLE USER was not initiated"));
		  var user = User.builder()
				  .firstname(request.getFirstname())
				  .lastname(request.getLastname())
				  .email(request.getEmail())
	                .password(passwordEncoder.encode(request.getPassword()))
	                .accountLocked(false)
	                .enabled(false)
	                .roles(List.of(userRole))
	                .build();
		  userRepository.save(user);
	        sendValidationEmail(user);
	}

	private void sendValidationEmail(User user) throws MessagingException {
		var newToken = generateAndSaveActivationToken(user);
		
		  emailService.sendEmail(
	                user.getEmail(),
	                user.fullName(),
	                EmailTemplateName.ACTIVATE_ACCOUNT,
	                activationUrl,
	                newToken,
	                "Account activation"
	                );
		
	}

	private String generateAndSaveActivationToken(User user) {
		
		String generatedToken = generateActivationCode(6);
		 var token = Token.builder()
	                .token(generatedToken)
	                .createdAt(LocalDateTime.now())
	                .expiresAt(LocalDateTime.now().plusMinutes(15))
	                .user(user)
	                .build();
		 tokenRepository.save(token);
		
		return generatedToken;
	}

	private String generateActivationCode(int length) {
		String characters = "0123456789";
        StringBuilder codeBuilder = new StringBuilder();
        SecureRandom secureRandom = new SecureRandom();
        
        for (int i = 0; i < length; i++) {
            int randomIndex = secureRandom.nextInt(characters.length());
            codeBuilder.append(characters.charAt(randomIndex));
        }
        
		return codeBuilder.toString();
	}

}
