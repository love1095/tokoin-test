package com.mycompany.tokointest.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mycompany.tokointest.services.TokoinService;

/**
 * TokionService.
 *
 * @author Love
 *
 */
@SpringBootApplication
public class TokoinApplication implements CommandLineRunner {

	@Autowired
	private TokoinService tokoinService;

	public static void main(String[] args) throws Exception {
		SpringApplication.run(TokoinApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		tokoinService.search();
	}
}
