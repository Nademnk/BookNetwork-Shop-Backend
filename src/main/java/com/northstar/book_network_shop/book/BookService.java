package com.northstar.book_network_shop.book;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.northstar.book_network_shop.common.PageResponses;
import com.northstar.book_network_shop.user.User;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

import static com.northstar.book_network_shop.book.BookSpecification.withOwnerId;

@Service
@RequiredArgsConstructor
public class BookService {

	private final BookMapper bookMapper;
	private final BookRepository bookRepository;
	
	  public Integer save(BookRequest request, Authentication connectedUser) {
		  User user = ((User) connectedUser.getPrincipal());
		  Book book = bookMapper.toBook(request);
		  book.setOwner(user);
	        return bookRepository.save(book).getId();
	    }

	public BookResponse findById(Integer bookId) {
	
		return  bookRepository.findById(bookId)
                .map(bookMapper::toBookResponse)
                .orElseThrow(() -> new EntityNotFoundException("No book found with ID:: " + bookId));
	}

	public PageResponses<BookResponse> findAllBooks(int page, int size, Authentication connectedUser) {
		User user = ((User) connectedUser.getPrincipal());
		Pageable pageable= PageRequest.of(page, size,Sort.by( "createdDate").descending());
		Page<Book> books = bookRepository.findAllDisplayableBooks(pageable, user.getId());
		List<BookResponse> booksResponse = books.stream()
                .map(bookMapper::toBookResponse)
                .toList();
		return new PageResponses<>(
                booksResponse,
                books.getNumber(),
                books.getSize(),
                books.getTotalElements(),
                books.getTotalPages(),
                books.isFirst(),
                books.isLast()
        );
	}

	public PageResponses<BookResponse> findAllBooksByOwner(int page, int size, Authentication connectedUser) {
		  User user = ((User) connectedUser.getPrincipal());
		  Pageable pageable= PageRequest.of(page, size,Sort.by( "createdDate").descending());
		  Page<Book> books = bookRepository.findAll(withOwnerId(user.getId()), pageable);
		  List<BookResponse> booksResponse = books.stream()
	                .map(bookMapper::toBookResponse)
	                .toList();
		  return new PageResponses<>(
	                booksResponse,
	                books.getNumber(),
	                books.getSize(),
	                books.getTotalElements(),
	                books.getTotalPages(),
	                books.isFirst(),
	                books.isLast()
	        );
	}
	
}
