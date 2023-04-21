package com.codelabs.Requester;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class RequesterApplication {

	public static void main(String[] args) {
		SpringApplication.run(RequesterApplication.class, args);
	}


}
