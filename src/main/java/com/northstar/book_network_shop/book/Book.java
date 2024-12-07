package com.northstar.book_network_shop.book;

import java.util.List;

import com.northstar.book_network_shop.common.CommonClass;
import com.northstar.book_network_shop.feedback.Feedback;
import com.northstar.book_network_shop.history.BookTransactionHistory;
import com.northstar.book_network_shop.user.User;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Book extends CommonClass {

	private String title;

	private String authorName;

	private String isbn;

	private String synopsis;

	private String bookCover;

	private boolean archived;

	private boolean shareable;
	
	@ManyToOne
	@JoinColumn(name = "owner_id")
	private User owner;

	@OneToMany(mappedBy = "book")
	private List<Feedback> feedback;
	
	@OneToMany(mappedBy = "book")
	private List<BookTransactionHistory> histories;
	
}
