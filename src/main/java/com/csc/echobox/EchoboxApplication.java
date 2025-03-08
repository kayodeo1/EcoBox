package com.csc.echobox;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EchoboxApplication {

	public static void main(String[] args) {
		  Dotenv dotenv = Dotenv.load(); // Load .env file

	        // Set environment variables for Spring Boot
	        dotenv.entries().forEach(entry -> System.setProperty(entry.getKey(), entry.getValue()));

		SpringApplication.run(EchoboxApplication.class, args);
	}

}
