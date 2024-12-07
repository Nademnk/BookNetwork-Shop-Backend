package com.northstar.book_network_shop.book;

import com.northstar.book_network_shop.common.CommonClass;

import jakarta.persistence.Entity;
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
public class Book extends CommonClass{

	
	
	private String title;
	
	private String authorName;
	
	private String isbn;
	
	private String synopsis;
	
	private String bookCover;
	
	private boolean archived;
	
	private boolean shareable;
	
	
}
