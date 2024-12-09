package com.northstar.book_network_shop.feedback;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.northstar.book_network_shop.common.PageResponses;

import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("feedbacks")
@RequiredArgsConstructor
@Tag(name = "Feedback")
public class FeedBackController {

	private final FeedbackService service;
	
	@PostMapping
	public ResponseEntity<Integer> saveFeedback(
			@Valid @RequestBody FeedbackRequest request,
			Authentication connectedUser
			){
		
		return ResponseEntity.ok(service.save(request, connectedUser));
	}
	
	@GetMapping("/book/{book-id}")
	public ResponseEntity<PageResponses<FeedBackResponse>> findAllFeedbackByBook(
			@PathVariable("book-id") Integer bookId,
			@RequestParam(name = "page", defaultValue = "0", required = false) int page,
			@RequestParam(name = "size", defaultValue = "10", required = false) int size,
			Authentication connectedUser
			){
		
		return ResponseEntity.ok(service.findallFeedbacksByBook(bookId, page, size, connectedUser) );
	}
}


