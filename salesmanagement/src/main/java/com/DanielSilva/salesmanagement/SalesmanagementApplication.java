package com.DanielSilva.salesmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SalesmanagementApplication {

	// Pacotes a serem criados:
	// domain/model
	// domain/repository
	// domain/enums
	// domain/exceptions
	// application/service
	// presentation

	public static void main(String[] args) {
		SpringApplication.run(SalesmanagementApplication.class, args);
	}

}
