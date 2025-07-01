package com.os.console.api;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.os.console.api.search.model.fields.Field;
import com.os.console.api.search.model.fields.FieldSerializer;

import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;

@Configuration
public class LedgerSearchWebClientConfig {

	@Autowired
	ApplicationConfig authConfig;

	@Bean
	public WebClient ledgerSearchWebClient() {
		ObjectMapper objectMapper = new ObjectMapper()
				.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
				.registerModule(new SimpleModule().addSerializer(Field.class, new FieldSerializer()));

		
		ConnectionProvider provider = ConnectionProvider.builder("restConnectionBuilder")
				.maxConnections(500)
				.maxIdleTime(Duration.ofSeconds(20))
				.maxLifeTime(Duration.ofSeconds(60))
				.pendingAcquireTimeout(Duration.ofSeconds(60))
				.evictInBackground(Duration.ofSeconds(120)).build();
		
		return WebClient.builder().baseUrl(authConfig.getApi_uri())
				.exchangeStrategies(ExchangeStrategies.builder().codecs(clientDefaultCodecsConfigurer -> {
					clientDefaultCodecsConfigurer.defaultCodecs()
							.jackson2JsonEncoder(new Jackson2JsonEncoder(objectMapper, MediaType.APPLICATION_JSON));
					clientDefaultCodecsConfigurer.defaultCodecs()
							.jackson2JsonDecoder(new Jackson2JsonDecoder(objectMapper, MediaType.APPLICATION_JSON));
				}).build())
				.clientConnector(new ReactorClientHttpConnector(HttpClient.create(provider)))
				.build();
	}
}
