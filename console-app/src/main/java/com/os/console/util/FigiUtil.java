package com.os.console.util;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import com.os.console.api.figi.OpenFigiV3Request;
import com.os.console.api.figi.OpenFigiV3Response;

public class FigiUtil {

	private static final Logger logger = LoggerFactory.getLogger(FigiUtil.class);

	public static void mapFigi(WebClient webClient, String idType, String idValue) {

		ArrayList<OpenFigiV3Request> figiRequests = new ArrayList<OpenFigiV3Request>();

		OpenFigiV3Request figiRequest = new OpenFigiV3Request();
		figiRequest.setIdType(idType);
		figiRequest.setIdValue(idValue);
		figiRequests.add(figiRequest);

		logger.debug("Making Figi mapping request for " + idType + ":" + idValue);
		
		OpenFigiV3Response figiResponse = webClient.post().uri("/v3/mapping").contentType(MediaType.APPLICATION_JSON)
				.bodyValue(figiRequests)
				// .headers(h -> h.set("X-OPENFIGI-APIKEY", "***************"))
				.retrieve().bodyToMono(OpenFigiV3Response.class).block();

		ConsoleOutputUtil.printObject(figiResponse);
	}

}
