package com.os.console;

import java.io.BufferedReader;

import com.os.client.model.Event;
import com.os.console.api.ApplicationConfig;
import com.os.console.util.ConsoleOutputUtil;

public class EventConsole extends AbstractConsole {

	Event event;

	public EventConsole(Event event) {
		this.event = event;
	}

	protected boolean prompt() {
		
		if (event == null) {
			System.out.println("Event not available");
			return false;
		}

		System.out.print(ApplicationConfig.ACTING_PARTY.getPartyId() + " /events/" + event.getEventId() + " > ");
		
		return true;
	}

	public void handleArgs(String args[], BufferedReader consoleIn) {

		if (args[0].equals("J")) {

			ConsoleOutputUtil.printObject(event);

		} else {
			System.out.println("Unknown command");
		}
	}

	protected void printMenu() {
		System.out.println("Event Menu");
		System.out.println("-----------------------");
		System.out.println("J             - Print JSON");
		System.out.println();
		System.out.println("X             - Go back");
	}

}
