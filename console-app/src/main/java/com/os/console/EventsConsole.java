package com.os.console;

import java.io.BufferedReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;

import com.os.client.model.EventType;
import com.os.console.api.ApplicationConfig;
import com.os.console.api.tasks.SearchEventTask;
import com.os.console.api.tasks.SearchEventsTask;

public class EventsConsole extends AbstractConsole {

	@Autowired
	WebClient restWebClient;
	
	protected boolean prompt() {
		System.out.print(ApplicationConfig.ACTING_PARTY.getPartyId() + " /events > ");
		return true;
	}

	public void handleArgs(String args[], BufferedReader consoleIn) {

		// The second arg may need to be split
		String arg0 = args[0];
		String arg1 = null;
		String arg2 = null;

		if (args.length == 2) {
			arg1 = args[1].trim();
			int idxSep = arg1.indexOf(" ");
			if (idxSep > 0) {
				arg2 = arg1.substring(idxSep + 1).trim();
				arg1 = arg1.substring(0, idxSep);
			}
		}

		if (arg0.equals("I")) {
			Integer days = 1;
			EventType eventType = null;

			SearchEventsTask searchEventsTask = null;

			if (arg1 != null) {
				try {

					days = Integer.valueOf(arg1);

					if (days <= 60) {
						if (arg2 != null) {
							try {
								eventType = EventType.fromValue(arg2.toUpperCase());
								if (eventType == null) {
									System.out.println("Invalid Event Type");
								} else {
									searchEventsTask = new SearchEventsTask(restWebClient, days, eventType, null, null);
								}
							} catch (Exception u) {
								System.out.println("Invalid Event Type");
							}
						} else {
							searchEventsTask = new SearchEventsTask(restWebClient, days, null, null, null);
						}
					} else {
						System.out.println("Invalid Days value. Maximum is 60.");
					}
				} catch (Exception e) {
					System.out.println("Invalid Days value. Maximum is 60.");
				}

			} else {
				searchEventsTask = new SearchEventsTask(restWebClient, null, null, null, null);
			}

			if (searchEventsTask != null) {
				System.out.print("Listing last " + days + " days of "
						+ (eventType != null ? eventType.getValue() : "ALL") + " events...");

				Thread taskT = new Thread(searchEventsTask);
				taskT.run();
				try {
					taskT.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		} else if (arg0.equals("N")) {

			Long eventId = null;
			EventType eventType = null;

			SearchEventsTask searchEventsTask = null;

			if (arg1 != null && arg1.length() <= 30) {
				try {
					eventId = Long.valueOf(arg1);

					if (arg2 != null) {
						try {
							eventType = EventType.fromValue(arg2.toUpperCase());
							if (eventType == null) {
								System.out.println("Invalid Event Type");
							} else {
								searchEventsTask = new SearchEventsTask(restWebClient, null, eventType, null, eventId);
							}
						} catch (Exception u) {
							System.out.println("Invalid Event Type");
						}
					} else {
						searchEventsTask = new SearchEventsTask(restWebClient, null, null, null, eventId);
					}
				} catch (Exception e) {
					System.out.println("Invalid Event Id");
				}

			} else {
				System.out.println("Invalid Event Id");
			}

			if (searchEventsTask != null) {
				System.out.print("Listing events since event " + eventId + " of type "
						+ (eventType != null ? eventType.getValue() : "ALL") + "...");

				Thread taskT = new Thread(searchEventsTask);
				taskT.run();
				try {
					taskT.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		} else if (arg0.equals("V")) {
			Integer seconds = null;
			EventType eventType = null;

			SearchEventsTask searchEventsTask = null;

			if (arg1 != null && arg1.length() <= 6) {
				try {
					seconds = Integer.valueOf(arg1);

					if (seconds <= 604800) {

						if (arg2 != null) {
							try {
								eventType = EventType.fromValue(arg2.toUpperCase());
								if (eventType == null) {
									System.out.println("Invalid Event Type");
								} else {
									searchEventsTask = new SearchEventsTask(restWebClient, null, eventType, seconds, null);
								}
							} catch (Exception u) {
								System.out.println("Invalid Event Type");
							}
						} else {
							searchEventsTask = new SearchEventsTask(restWebClient, null, null, seconds, null);
						}
					} else {
						System.out.println("Invalid Seconds value. Should be less than 604800.");
					}
				} catch (Exception e) {
					System.out.println("Invalid Seconds value. Should be less than 604800.");
				}

			} else {
				System.out.println("Invalid Seconds value");
			}

			if (searchEventsTask != null) {
				System.out.print("Listing last " + seconds + " seconds of "
						+ (eventType != null ? eventType.getValue() : "ALL") + " events...");

				Thread taskT = new Thread(searchEventsTask);
				taskT.run();
				try {
					taskT.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} else if (arg0.equals("S")) {
			if (arg1 != null && arg1.length() > 20) {
				String eventId = arg1.toLowerCase();
				try {
					if (Long.valueOf(eventId) != null) {
						System.out.print("Searching for event " + eventId + "...");
						SearchEventTask searchEventTask = new SearchEventTask(restWebClient, eventId);
						Thread taskT = new Thread(searchEventTask);
						taskT.run();
						try {
							taskT.join();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						if (searchEventTask.getEvent() != null) {
							EventConsole eventConsole = new EventConsole(searchEventTask.getEvent());
							eventConsole.execute(consoleIn);
						}
					} else {
						System.out.println("Invalid event Id");
					}
				} catch (Exception u) {
					System.out.println("Invalid event Id");
				}
			} else {
				System.out.println("Invalid event Id");
			}
		} else {
			System.out.println("Unknown command");
		}

	}

	protected void printMenu() {
		System.out.println("Events Menu");
		System.out.println("-----------------------");
		System.out.println(
				"I <Days>     <Event Type> - List events for last Days of Event Type. Defaults to All events for current business day");
		System.out.println("N <Event Id> <Event Type> - List events since Event Id of Event Type");
		System.out.println("V <Seconds>  <Event Type> - List events created in last Seconds of Event Type");
		System.out.println();
		System.out.println("S <Event Id>              - Search an event by Id");
		System.out.println();
		System.out.println("X                         - Go back");
	}

}
