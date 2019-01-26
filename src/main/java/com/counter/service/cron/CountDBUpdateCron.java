package com.counter.service.cron;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.counter.service.CounterService;

@Service
public class CountDBUpdateCron {

	@Autowired
	CounterService counterService;
	
	@Scheduled(cron = "0/10 * * * * *")
	public void updateCounToDb() {
		counterService.dumpAndReset();
		
	}
	
}
