package com.northstar.book_network_shop.feedback;

import java.util.List;
import java.util.Objects;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.northstar.book_network_shop.book.Book;
import com.northstar.book_network_shop.book.BookRepository;
import com.northstar.book_network_shop.common.PageResponses;
import com.northstar.book_network_shop.exception.OperationNotPermittedException;
import com.northstar.book_network_shop.user.User;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FeedbackService {
	
	private final BookRepository bookRepository;
	private final FeedbackRepository feedbackRepository;
	private final FeedbackMapper feedbackMapper;
	
	public Integer save(@Valid FeedbackRequest request, Authentication connectedUser) {
		
				Book book = bookRepository.findById(request.bookId())
                .orElseThrow(() -> new EntityNotFoundException("No book found with ID:: " + request.bookId()));
        if (book.isArchived() || !book.isShareable()) {
            throw new OperationNotPermittedException("You cannot give a feedback for an archived or not shareable book");
        }
        
        User user = ((User) connectedUser.getPrincipal());
        if (Objects.equals(book.getOwner().getId(), user.getId())) {
            throw new OperationNotPermittedException("You cannot give a feedback to your own book");
        }
        Feedback feedback = feedbackMapper.toFeedback(request);
		return feedbackRepository.save(feedback).getId();
	}

	public PageResponses<FeedBackResponse> findallFeedbacksByBook(Integer bookId, int page, int size, Authentication connectedUser) {
		Pageable pageable = PageRequest.of(page,size);
		
		 User user = ((User) connectedUser.getPrincipal());
	        Page<Feedback> feedbacks = feedbackRepository.findAllByBookId(bookId, pageable);
	        List<FeedBackResponse> feedbackResponses = feedbacks.stream()
	                .map(f -> feedbackMapper.toFeedbackResponse(f, user.getId()))
	                .toList();
	        return new PageResponses<>(
	                feedbackResponses,
	                feedbacks.getNumber(),
	                feedbacks.getSize(),
	                feedbacks.getTotalElements(),
	                feedbacks.getTotalPages(),
	                feedbacks.isFirst(),
	                feedbacks.isLast()
	        );

	}

	
	
}
