package com.northstar.book_network_shop.book;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.northstar.book_network_shop.user.User;

import lombok.RequiredArgsConstructor;

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
	
}
