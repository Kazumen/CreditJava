package com.labs.lab48;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@EnableAsync
@SpringBootApplication
public class Lab48Application {

	public static void main(String[] args) {
		SpringApplication.run(Lab48Application.class, args);
	}

}
