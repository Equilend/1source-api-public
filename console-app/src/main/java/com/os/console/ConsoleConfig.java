package com.os.console;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ConsoleConfig {

	@Autowired
	WebClient restWebClient;

	@Autowired
	WebClient ledgerSearchWebClient;

	@Bean
	public LoginConsole loginConsole() {
		return new LoginConsole(restWebClient);
	}

	@Bean
	public LoansConsole loansConsole() {
		return new LoansConsole(restWebClient, ledgerSearchWebClient);
	}

}
