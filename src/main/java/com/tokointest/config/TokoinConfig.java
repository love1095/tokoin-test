package com.tokointest.config;

import java.util.Scanner;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Tokoin configuration class for search project.
 *
 * @author Love
 */
@Configuration
public class TokoinConfig {
	@Bean
	public Scanner scanner() {
		return new Scanner(System.in);
	}
}
