package com.biblioteca.saraiva;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class SaraivaApplication {
	public static void main(String[] args) {
		SpringApplication.run(SaraivaApplication.class, args);
	}
}