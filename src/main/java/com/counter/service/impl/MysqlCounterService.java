package com.counter.service.impl;

import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.counter.service.CounterService;

@Repository
public class MysqlCounterService implements CounterService {

	private static final AtomicLong count = new AtomicLong(0);

	private static final Logger logger =LoggerFactory.getLogger(MysqlCounterService.class.getSimpleName());

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public long incrementAndGet() {

		return count.incrementAndGet();

	}

	/**
	 * 
	 */
	@Override
	public void dumpAndReset() {
		long countToBeDumped=count.getAndSet(0l);
		boolean updated=true;
		try {
			jdbcTemplate.update("UPDATE number SET count = count + ?", new Object[] { countToBeDumped });
			logger.info("Count Updated To Databse = {}",countToBeDumped);
		} catch (DataAccessException e) {
			updated=false;
		} catch (Exception e) {
			updated=false;
		}
		
		if(!updated) {
			//adding the counts that were incrementing while we were saving to DB and it failed.
			count.set(countToBeDumped+count.get()); 
		}


	}

}
