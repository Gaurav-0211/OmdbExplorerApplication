package com.omdb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class OmdbExplorerApplication {

	public static void main(String[] args) {
		SpringApplication.run(OmdbExplorerApplication.class, args);
	}

}
