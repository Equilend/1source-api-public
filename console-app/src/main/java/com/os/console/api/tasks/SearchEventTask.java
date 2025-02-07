package com.os.console.api.tasks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.reactive.function.client.WebClient;

import com.os.client.model.Event;
import com.os.console.util.RESTUtil;

public class SearchEventTask implements Runnable {

	private static final Logger logger = LoggerFactory.getLogger(SearchEventTask.class);

	private WebClient webClient;
	private String eventId;

	private Event event;
	
	public SearchEventTask(WebClient webClient, String eventId) {
		this.webClient = webClient;
		this.eventId = eventId;
	}

	@Override
	public void run() {

		event = (Event) RESTUtil.getRequest(webClient, "/events/" + eventId, Event.class);

		if (event == null) {
			logger.warn("Invalid event object or event not found");
			System.out.println("event not found");
		} else {
			System.out.println("complete");
			System.out.println();			
		}
	}

	public Event getEvent() {
		return event;
	}
	
	
}
