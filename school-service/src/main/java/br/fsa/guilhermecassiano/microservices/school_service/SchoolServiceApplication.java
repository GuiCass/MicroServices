package br.fsa.guilhermecassiano.microservices.school_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages = "br.fsa.guilhermecassiano.microservices")
@EnableJpaRepositories(basePackages = "br.fsa.guilhermecassiano.microservices.repository")
@EntityScan(basePackages = "br.fsa.guilhermecassiano.microservices.entity")
public class SchoolServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SchoolServiceApplication.class, args);
	}

}
