package com.northstar.book_network_shop.feedback;

import java.util.Objects;

import org.springframework.stereotype.Service;

import com.northstar.book_network_shop.book.Book;

import jakarta.validation.Valid;

@Service
public class FeedbackMapper {

	public Feedback toFeedback(@Valid FeedbackRequest request) {
		
		return Feedback.builder()
				.note(request.note())
				.book( Book.builder()
						
						.id( request.bookId())
						.archived(false)
						.shareable(false)
						.build()
						)
				.build();
	}

	public FeedBackResponse toFeedbackResponse(Feedback feedback, Integer id) {
		
		return FeedBackResponse.builder()
				.note(feedback.getNote() )
				.comment( feedback.getComment())
				.ownFeedback(Objects.equals(feedback.getCreatedBy(), id))
				.build();
	}

}
