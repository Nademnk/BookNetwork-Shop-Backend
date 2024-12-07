package com.northstar.book_network_shop.history;

import com.northstar.book_network_shop.book.Book;
import com.northstar.book_network_shop.common.CommonClass;
import com.northstar.book_network_shop.user.User;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class BookTransactionHistory extends CommonClass{

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "boo_id")
	private Book book; 
	
	private boolean returned;
	private boolean returnApproved;
	
}
