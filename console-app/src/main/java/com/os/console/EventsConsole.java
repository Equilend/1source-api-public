package com.os.console;

import java.io.BufferedReader;

import org.springframework.web.reactive.function.client.WebClient;

import com.os.client.model.EventType;
import com.os.console.api.ConsoleConfig;
import com.os.console.api.tasks.SearchEventTask;
import com.os.console.api.tasks.SearchEventsTask;

public class EventsConsole extends AbstractConsole {

	public EventsConsole() {

	}

	protected boolean prompt() {
		System.out.print(ConsoleConfig.ACTING_PARTY.getPartyId() + " /events > ");
		return true;
	}

	public void handleArgs(String args[], BufferedReader consoleIn, WebClient webClient) {

		if (args[0].equals("I")) {
			Integer size = 100;
			if (args.length == 2) {
				try {
					size = Integer.valueOf(args[1]);
				} catch (Exception e) {
					System.out.println("Invalid size value");
				}
			}
			System.out.print("Listing latest " + size + " events...");
			SearchEventsTask searchEventsTask = new SearchEventsTask(webClient, size, null, null, null);
			Thread taskT = new Thread(searchEventsTask);
			taskT.run();
			try {
				taskT.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else if (args[0].equals("T")) {
			EventType eventType = null;
			if (args.length != 2) {
				System.out.println("Invalid event type");
			} else {
				eventType = EventType.fromValue(args[1].toUpperCase());
				if (eventType != null) {
					System.out.print(
							"Listing all " + (eventType != null ? (eventType.getValue() + " ") : "") + "events...");
					SearchEventsTask searchEventsTask = new SearchEventsTask(webClient, null, eventType, null, null);
					Thread taskT = new Thread(searchEventsTask);
					taskT.run();
					try {
						taskT.join();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				} else {
					System.out.println("Invalid event type");
				}
			}
		} else if (args[0].equals("N")) {
			if (args.length != 2 || args[1].length() > 20) {
				System.out.println("Invalid event Id");
			} else {
				try {
					Long eventId = Long.valueOf(args[1]);
					System.out.print("Listing events after " + eventId + "...");
					SearchEventsTask searchEventsTask = new SearchEventsTask(webClient, null, null, null, eventId);
					Thread taskT = new Thread(searchEventsTask);
					taskT.run();
					try {
						taskT.join();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				} catch (Exception u) {
					System.out.println("Invalid event Id");
				}
			}
		} else if (args[0].equals("V")) {
			if (args.length != 2 || args[1].length() > 20) {
				System.out.println("Invalid seconds value");
			} else {
				try {
					Integer seconds = Integer.valueOf(args[1]);
					System.out.print("Listing events created in the last " + seconds + " seconds...");
					SearchEventsTask searchEventsTask = new SearchEventsTask(webClient, null, null, seconds, null);
					Thread taskT = new Thread(searchEventsTask);
					taskT.run();
					try {
						taskT.join();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				} catch (Exception u) {
					System.out.println("Invalid seconds value");
				}
			}
		} else if (args[0].equals("S")) {
			if (args.length != 2 || args[1].length() > 20) {
				System.out.println("Invalid event Id");
			} else {
				String eventId = args[1].toLowerCase();
				try {
					if (Long.valueOf(eventId) != null) {
						System.out.print("Searching for event " + eventId + "...");
						SearchEventTask searchEventTask = new SearchEventTask(webClient, eventId);
						Thread taskT = new Thread(searchEventTask);
						taskT.run();
						try {
							taskT.join();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						if (searchEventTask.getEvent() != null) {
							EventConsole eventConsole = new EventConsole(searchEventTask.getEvent());
							eventConsole.execute(consoleIn, webClient);
						}
					} else {
						System.out.println("Invalid event Id");
					}
				} catch (Exception u) {
					System.out.println("Invalid event Id");
				}
			}
		} else {
			System.out.println("Unknown command");
		}

	}

	protected void printMenu() {
		System.out.println("Events Menu");
		System.out.println("-----------------------");
		System.out.println("I <size>       - List latest events");
		System.out.println("T <Event Type> - List events matching Event Type");
		System.out.println("N <Event Id>   - List events since Event Id");
		System.out.println("V <Seconds>    - List events created in last Seconds");
		System.out.println("S <Event Id>   - Search an event by Id");
		System.out.println();
		System.out.println("X              - Go back");
	}

}
