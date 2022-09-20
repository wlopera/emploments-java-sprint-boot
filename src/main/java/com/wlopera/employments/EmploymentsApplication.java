package com.wlopera.employments;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@EnableJpaRepositories
//@EnableJpaRepositories(basePackages ={"com.wlopera.employments.repository"})
//@EntityScan(basePackages = { "com.wlopera.employments.model" })
public class EmploymentsApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmploymentsApplication.class, args);
	}

}
