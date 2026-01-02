package com.memento.practice.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class JobApplicationTrackerApi {

	public static void main(String[] args) {
		SpringApplication.run(JobApplicationTrackerApi.class, args);
	}

	@GetMapping
	public String tester() {
		return "Job Application API (:";
	}

}