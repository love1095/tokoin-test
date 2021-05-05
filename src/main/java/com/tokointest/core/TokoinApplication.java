package com.tokointest.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.tokointest.core.scanner.MainHandler;

/**
 * TokionService.
 *
 * @author Love
 *
 */
@SpringBootApplication
public class TokoinApplication implements CommandLineRunner {

	@Autowired
	private MainHandler mainHandler;

	public static void main(String[] args) throws Exception {
		SpringApplication.run(TokoinApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		mainHandler.executeSearch();
	}
}

