package com.os.console.api.tasks;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.reactive.function.client.WebClient;

import com.os.client.model.Event;
import com.os.client.model.EventType;
import com.os.client.model.Events;
import com.os.console.util.ConsoleOutputUtil;
import com.os.console.util.RESTUtil;

public class SearchEventsTask implements Runnable {

	private static final Logger logger = LoggerFactory.getLogger(SearchEventsTask.class);

	private WebClient webClient;
	private Integer days;
    private EventType eventType;
    private Integer seconds;
    private Long fromEventId;
    
	public SearchEventsTask(WebClient webClient, Integer days, EventType eventType, Integer seconds, Long fromEventId) {
		this.webClient = webClient;
		this.days = days;
		this.eventType = eventType;
		this.seconds = seconds;
		this.fromEventId = fromEventId;
	}

	@Override
	public void run() {

		String uri = "/events?";
		if (days != null && days >= 1) {
			LocalDateTime localDateTime = LocalDateTime.now(ZoneOffset.UTC).minusDays(days);
			uri += ("since=" + DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss").format(localDateTime) + "Z&");
		} else if (seconds != null) {
			LocalDateTime localDateTime = LocalDateTime.now(ZoneOffset.UTC).minusSeconds(seconds);
			uri += "since=" + DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss").format(localDateTime) + "Z&";
		}
		
		if (fromEventId != null) {
			uri += "fromEventId=" + fromEventId + "&";
		}

		if (eventType != null) {
			uri += "eventType=" + eventType.getValue() + "&";
		}
		
		Events events = (Events) RESTUtil.getRequest(webClient, uri, Events.class);

		if (events == null || events.size() == 0) {
			logger.warn("Invalid events object or no events");
			System.out.println("no events found");
			printHeader();
		} else {
			System.out.println("complete");
			printHeader();
			int rows = 1;
			for (Event event : events) {
				if (rows % 15 == 0) {
					printHeader();
				}

				System.out.print(ConsoleOutputUtil.padSpaces(event.getEventId(), 25));
				System.out.print(ConsoleOutputUtil.padSpaces(event.getEventDateTime(), 30));
				System.out.print(ConsoleOutputUtil.padSpaces(event.getEventType().toString(), 35));
				System.out.print(ConsoleOutputUtil.padSpaces(event.getResourceUri(), 100));

				System.out.println();

				rows++;
			}
		}
		System.out.println();
	}

	public void printHeader() {
		System.out.println();
		System.out.print(ConsoleOutputUtil.padSpaces("Event Id", 25));
		System.out.print(ConsoleOutputUtil.padSpaces("Date Time", 30));
		System.out.print(ConsoleOutputUtil.padSpaces("Type", 35));
		System.out.print(ConsoleOutputUtil.padSpaces("URI", 100));
		System.out.println();
		System.out.print(ConsoleOutputUtil.padDivider(25));
		System.out.print(ConsoleOutputUtil.padDivider(30));
		System.out.print(ConsoleOutputUtil.padDivider(35));
		System.out.print(ConsoleOutputUtil.padDivider(100));
		System.out.println();
	}
}
