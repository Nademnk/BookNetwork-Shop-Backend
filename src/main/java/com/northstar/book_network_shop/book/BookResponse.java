package com.northstar.book_network_shop.book;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookResponse {

	private Integer id;
	private String title;
	private String authorName;
	private String isbn;
	private String synopsis;
	private String owner;
	private double rate;
	private byte[] cover;
	private boolean archived;
	private boolean shareable;
}
