package com.counter.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.counter.service.CounterService;

@RestController
public class CounterController {
	
	@Autowired
	CounterService counterService;
	
	@RequestMapping("/increment")
	public Long increment() {
		long count= counterService.incrementAndGet();
		if(count==5000) {
			System.out.println(new Date());
		}
		return count;
		
	}
	
}
