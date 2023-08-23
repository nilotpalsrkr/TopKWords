package com.nilotpal.practice.TopKWords;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class TopKWordsApplication {

	public static void main(String[] args) {
		SpringApplication.run(TopKWordsApplication.class, args);
	}

}
