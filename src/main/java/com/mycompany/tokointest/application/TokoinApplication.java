package com.mycompany.tokointest.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mycompany.tokointest.services.TokoinService;

/**
 * TokionService.
 *
 * @author Love
 *
 */
@SpringBootApplication
public class TokoinApplication {

	@Autowired
	private static TokoinService tokoinService;
    
	public static void main(String[] args) throws Exception {
//        SpringApplication.run(SpringBootWebApplication.class, args);
	}

}
