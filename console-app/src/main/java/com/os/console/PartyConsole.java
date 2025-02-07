package com.os.console;

import java.io.BufferedReader;

import org.springframework.web.reactive.function.client.WebClient;

import com.os.client.model.Party;
import com.os.console.api.ConsoleConfig;
import com.os.console.util.ConsoleOutputUtil;

public class PartyConsole extends AbstractConsole {

	Party party;

	public PartyConsole(Party party) {
		this.party = party;
	}

	protected boolean prompt() {
		
		if (party == null) {
			System.out.println("Party not available");
			return false;
		}

		System.out.print(ConsoleConfig.ACTING_PARTY.getPartyId() + " /parties/" + party.getPartyId() + " > ");

		return true;
	}

	public void handleArgs(String args[], BufferedReader consoleIn, WebClient webClient) {

		if (args[0].equals("J")) {

			ConsoleOutputUtil.printObject(party);

		} else {
			System.out.println("Unknown command");
		}
	}

	protected void printMenu() {
		System.out.println("Party Menu");
		System.out.println("-----------------------");
		System.out.println("J             - Print JSON");
		System.out.println();
		System.out.println("X             - Go back");
	}

}
