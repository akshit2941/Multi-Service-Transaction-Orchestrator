package com.subscription.orchestrator_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

@SpringBootApplication
public class OrchestratorServiceApplication {

	public static void main(String[] args) {
		System.out.println(TimeZone.getDefault());
		SpringApplication.run(OrchestratorServiceApplication.class, args);
	}

}
