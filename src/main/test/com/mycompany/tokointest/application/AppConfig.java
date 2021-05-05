package com.mycompany.tokointest.application;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mycompany.tokointest.services.TokoinOrganizationService;
import com.mycompany.tokointest.services.TokoinService;
import com.mycompany.tokointest.services.TokoinTicketsService;
import com.mycompany.tokointest.services.TokoinUserService;

@Configuration
public class AppConfig {
	@Bean
	public TokoinService tokoinService() {
		System.out.println("ser from bean");
		return new TokoinService();
	}

	@Bean
	public TokoinUserService tokoinUserService() {
		System.out.println("ser from bean");
		return new TokoinUserService();
	}

	@Bean
	public TokoinTicketsService tokoinTicketService() {
		System.out.println("ser from bean");
		return new TokoinTicketsService();
	}

	@Bean
	public TokoinOrganizationService tokoinOrganizationService() {
		System.out.println("ser from bean");
		return new TokoinOrganizationService();
	}
}
