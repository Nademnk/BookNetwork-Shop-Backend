package com.northstar.book_network_shop;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;

import com.northstar.book_network_shop.role.Role;
import com.northstar.book_network_shop.role.RoleRepository;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@EnableAsync
@EntityScan(basePackages = {
	    "com.northstar.book_network_shop.user",
	    "com.northstar.book_network_shop.role", // Add the Role package here
	    "com.northstar.book_network_shop.book",
	    "com.northstar.book_network_shop.history",
	    "com.northstar.book_network_shop.feedback",
	})

public class BookNetworkShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookNetworkShopApplication.class, args);
	}

	@Bean
		public CommandLineRunner runner(RoleRepository roleRepository) {
			return args -> {
				if (roleRepository.findByName("USER").isEmpty()) {
					roleRepository.save(Role.builder().name("USER").build());
				}
			};
		}
	
}
