package com.counter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ParallelCounterAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParallelCounterAppApplication.class, args);
	}

}

