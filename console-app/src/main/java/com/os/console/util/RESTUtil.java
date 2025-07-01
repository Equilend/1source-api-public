package com.os.console.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;

import com.os.client.model.LedgerResponse;
import com.os.console.api.ApplicationConfig;
import com.os.console.api.LedgerException;

import reactor.core.publisher.Mono;

public class RESTUtil {

	private static final Logger logger = LoggerFactory.getLogger(RESTUtil.class);

	public static Object getRequest(WebClient webClient, String url, @SuppressWarnings("rawtypes") Class c) {
		return getRequest(webClient, url, c, null);
	}

	@SuppressWarnings("unchecked")
	public static Object getRequest(WebClient webClient, String url, @SuppressWarnings("rawtypes") Class c,
			Object payload) {

		Object o = null;

		try {

			if (payload != null) {
				String json = ConsoleOutputUtil.createJsonPayload(payload);

				logger.debug("GET: " + url + " with Payload: " + json);

				o = webClient.method(HttpMethod.GET).uri(url).contentType(MediaType.APPLICATION_JSON)
						.body(Mono.just(json), String.class)
						.headers(h -> h.setBearerAuth(ApplicationConfig.TOKEN.getAccess_token())).retrieve()
						.onStatus(HttpStatusCode::is4xxClientError, response -> {
							System.out.println(response.statusCode().toString());
							return response.bodyToMono(LedgerException.class);
						}).bodyToMono(c).block();
			} else {

				logger.debug("GET: " + url);

				o = webClient.get().uri(url).headers(h -> h.setBearerAuth(ApplicationConfig.TOKEN.getAccess_token()))
						.retrieve().onStatus(HttpStatusCode::is4xxClientError, response -> {
							System.out.println(response.statusCode().toString());
							return response.bodyToMono(LedgerException.class);
						}).bodyToMono(c).block();
			}
		} catch (Exception e) {
			System.out.println("Failed on exception");
			if (e.getCause() instanceof LedgerException) {
				ConsoleOutputUtil.printObject(((LedgerException) e.getCause()).getLedgerResponse());
			} else {
				System.out.println(e.getMessage());
			}
		}

		return o;
	}

	public static void patchRequest(WebClient webClient, String url, Object payload) {

		String json = ConsoleOutputUtil.createJsonPayload(payload);

		logger.debug("PATCH: " + url + " with Payload: " + json);

		ResponseSpec spec = webClient.patch().uri(url).contentType(MediaType.APPLICATION_JSON).bodyValue(json)
				.headers(h -> h.setBearerAuth(ApplicationConfig.TOKEN.getAccess_token())).retrieve();

		makeRequest(spec);
	}

	public static void postRequest(WebClient webClient, String url) {
		postRequest(webClient, url, null);
	}

	public static void postRequest(WebClient webClient, String url, Object payload) {

		ResponseSpec spec;

		if (payload != null) {
			String json = ConsoleOutputUtil.createJsonPayload(payload);

			logger.debug("POST: " + url + " with Payload: " + json);

			spec = webClient.post().uri(url).contentType(MediaType.APPLICATION_JSON).bodyValue(json)
					.headers(h -> h.setBearerAuth(ApplicationConfig.TOKEN.getAccess_token())).retrieve();
		} else {

			logger.debug("POST: " + url);

			spec = webClient.post().uri(url).headers(h -> h.setBearerAuth(ApplicationConfig.TOKEN.getAccess_token()))
					.retrieve();

		}
		makeRequest(spec);
	}

	private static void makeRequest(ResponseSpec spec) {
		try {

			LedgerResponse ledgerResponse = spec.onStatus(HttpStatusCode::is4xxClientError, response -> {
				System.out.println(response.statusCode().toString());
				return response.bodyToMono(LedgerException.class);
			}).bodyToMono(LedgerResponse.class).block();

			if (ledgerResponse != null) {
				System.out.println("Complete with status: " + ledgerResponse.getCode());
				ConsoleOutputUtil.printObject(ledgerResponse);
			} else {
				System.out.println("Failed with empty response");
			}
		} catch (Exception e) {
			System.out.println("Failed on exception");
			if (e.getCause() instanceof LedgerException) {
				ConsoleOutputUtil.printObject(((LedgerException) e.getCause()).getLedgerResponse());
			} else {
				System.out.println(e.getMessage());
			}
		}
	}
}
