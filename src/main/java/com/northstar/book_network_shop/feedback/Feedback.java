package com.northstar.book_network_shop.feedback;

import com.northstar.book_network_shop.book.Book;
import com.northstar.book_network_shop.common.CommonClass;

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
public class Feedback extends CommonClass{

	
	private Double note;
	
	private String comment;
	
	@ManyToOne
	@JoinColumn(name = "book_id")
	private Book book;
	
	
}
