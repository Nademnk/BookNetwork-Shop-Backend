package com.northstar.book_network_shop.email;

import lombok.Getter;

@Getter
public enum EmailTemplateName {
	
	ACTIVATE_ACCOUNT("activate_account");

	 EmailTemplateName(String name) {
		 this.name = name;
	}

	private final String name;
	
}