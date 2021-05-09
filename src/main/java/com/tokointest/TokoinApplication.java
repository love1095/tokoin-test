package com.tokointest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.tokointest.handler.TokoinSearchHandler;

/**
 * Tokoin application class for the code challenge.
 *
 * @author Love
 */
@SpringBootApplication
public class TokoinApplication implements CommandLineRunner {

	@Autowired
	private TokoinSearchHandler searchHandler;

	public static void main(String[] args) throws Exception {
		SpringApplication.run(TokoinApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		searchHandler.searchProcess();
	}
}
