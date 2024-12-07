package com.northstar.book_network_shop.history;

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
public class BookTransactionHistory extends CommonClass{

	//user relationship
	//book relationship
	
	private boolean returned;
	 private boolean returnApproved;
	
}
