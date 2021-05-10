package com.tokointest;

import com.tokointest.handler.TokoinSearchHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Tokoin application class for the code challenge.
 *
 * @author Love
 */
@SpringBootApplication
public class TokoinApplication implements CommandLineRunner {

	@Autowired
    private TokoinSearchHandler tokoinSearchHandler;

    /**
     * Searches the data and return the results in a human readable format.
     *
     * @param args
     *            the given argument for main function
     */
	public static void main(String[] args) throws Exception {
		SpringApplication.run(TokoinApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
        tokoinSearchHandler.searchProcess();
	}
}
