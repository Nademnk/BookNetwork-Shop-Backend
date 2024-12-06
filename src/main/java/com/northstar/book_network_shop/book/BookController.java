package com.northstar.book_network_shop.book;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.northstar.book_network_shop.common.PageResponses;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("books")
@RequiredArgsConstructor
@Tag(name = "Book")
public class BookController {

	 private final BookService service;

	    @PostMapping
	    public ResponseEntity<Integer> saveBook(
	            @Valid @RequestBody BookRequest request,
	            Authentication connectedUser
	    ) {
	        return ResponseEntity.ok(service.save(request, connectedUser));
	    }
	
	    @GetMapping("{book-id}")
	    public ResponseEntity<BookResponse> findBookById(
	    		
	    		@PathVariable("book-id") Integer bookId
	    		
	    		){
	    	return ResponseEntity.ok(service.findById(bookId));
	    }
	    
	    @GetMapping
	    public ResponseEntity<PageResponses<BookResponse>> findAllBooks(
	            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
	            @RequestParam(name = "size", defaultValue = "10", required = false) int size,
	            Authentication connectedUser
	    ) {
	        return ResponseEntity.ok(service.findAllBooks(page, size, connectedUser));
	    }
	    
	    @GetMapping("/owner")
	    public ResponseEntity<PageResponses<BookResponse>> findAllBooksByOwner(
	            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
	            @RequestParam(name = "size", defaultValue = "10", required = false) int size,
	            Authentication connectedUser
	    ) {
	        return ResponseEntity.ok(service.findAllBooksByOwner(page, size, connectedUser));
	    }
}
