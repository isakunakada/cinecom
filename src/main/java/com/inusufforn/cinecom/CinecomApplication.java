package com.inusufforn.cinecom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// @SpringBootApplicationのパラメータは必要。
//@SpringBootApplication(scanBasePackages={"com.springboot.dao"})
@SpringBootApplication
public class CinecomApplication {

	public static void main(String[] args) {
		SpringApplication.run(CinecomApplication.class, args);
	}

}
