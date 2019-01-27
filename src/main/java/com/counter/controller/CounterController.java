package com.counter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.counter.service.CounterService;

@RestController
public class CounterController {
	
	@Autowired
	CounterService counterService;
	
	/**
	 * Rest API to increment  
	 * 
	 * @return
	 */
	@RequestMapping("/increment")
	public Long increment() {
		long count= counterService.incrementAndGet();

		return count;
		
	}
	
}
