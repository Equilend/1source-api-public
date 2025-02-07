package com.os.console;

import java.io.BufferedReader;

import org.springframework.web.reactive.function.client.WebClient;

import com.os.client.model.PartyType;
import com.os.console.api.ConsoleConfig;
import com.os.console.api.tasks.SearchPartiesTask;
import com.os.console.api.tasks.SearchPartyTask;

public class PartiesConsole extends AbstractConsole {

	public PartiesConsole() {

	}

	protected boolean prompt() {
		System.out.print(ConsoleConfig.ACTING_PARTY.getPartyId() + " /parties > ");
		return true;
	}

	public void handleArgs(String args[], BufferedReader consoleIn, WebClient webClient) {

		if (args[0].equals("I")) {
			System.out.print("Listing parties...");
			SearchPartiesTask searchPartiesTask = new SearchPartiesTask(webClient, null, null);
			Thread taskT = new Thread(searchPartiesTask);
			taskT.run();
			try {
				taskT.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else if (args[0].equals("T")) {
			PartyType partyType = null;
			if (args.length == 2) {
				partyType = PartyType.fromValue(args[1].toUpperCase());
			}
			System.out.print("Listing all " + (partyType != null ? (partyType.getValue() + " ") : "") + "parties...");
			SearchPartiesTask searchPartiesTask = new SearchPartiesTask(webClient, partyType, null);
			Thread taskT = new Thread(searchPartiesTask);
			taskT.run();
			try {
				taskT.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else if (args[0].equals("G")) {
			String gleifLei = null;
			if (args.length == 2 && args[1].length() == 20) {
				//984500CC1ZFAABE58227 <-- example lei
				gleifLei = args[1];
			}
			System.out.print("Listing all " + (gleifLei != null ? (gleifLei + " ") : "") + "parties...");
			SearchPartiesTask searchPartiesTask = new SearchPartiesTask(webClient, null, gleifLei);
			Thread taskT = new Thread(searchPartiesTask);
			taskT.run();
			try {
				taskT.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else if (args[0].equals("S")) {
			if (args.length != 2 || args[1].length() > 35) {
				System.out.println("Invalid party id");
			} else {
				String partyId = args[1];
				try {
					System.out.print("Searching for case sensative party " + partyId + "...");
					SearchPartyTask searchPartyTask = new SearchPartyTask(webClient, partyId);
					Thread taskT = new Thread(searchPartyTask);
					taskT.run();
					try {
						taskT.join();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if (searchPartyTask.getParty() != null) {
						PartyConsole partyConsole = new PartyConsole(searchPartyTask.getParty());
						partyConsole.execute(consoleIn, webClient);
					}
				} catch (Exception u) {
					System.out.println("Invalid party id");
				}
			}
		} else {
			System.out.println("Unknown command");
		}

	}

	protected void printMenu() {
		System.out.println("Parties Menu");
		System.out.println("-----------------------");
		System.out.println("I              - List parties");
		System.out.println("T <Party Type> - List parties matching Party Type");
		System.out.println("G <GLEIF LEI>  - List parties matching GLEIF LEI");
		System.out.println("S <Party Id>   - Search a party by Id");
		System.out.println();
		System.out.println("X              - Go back");
	}

}
