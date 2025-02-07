package com.os.console.api;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class OpenFigiWebClientConfig {

	@Bean
	public WebClient openFigiWebClient() {
		return WebClient.create("https://api.openfigi.com");
	}
	
}
