package com.os.console.api.tasks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.reactive.function.client.WebClient;

import com.os.client.model.Parties;
import com.os.client.model.Party;
import com.os.client.model.PartyType;
import com.os.console.util.ConsoleOutputUtil;
import com.os.console.util.RESTUtil;

public class SearchPartiesTask implements Runnable {

	private static final Logger logger = LoggerFactory.getLogger(SearchPartiesTask.class);

	private WebClient webClient;
	private PartyType partyType;
	private String gleifLei;

	public SearchPartiesTask(WebClient webClient, PartyType partyType, String gleifLei) {
		this.webClient = webClient;
		this.partyType = partyType;
		this.gleifLei = gleifLei;
	}

	@Override
	public void run() {

		String uri = "/parties";
		if (partyType != null || gleifLei != null) {
			uri += "?";
			uri += (partyType != null ? "partyType=" + partyType.getValue() : "");
			uri += "&";
			uri += (gleifLei != null ? "gleifLei=" + gleifLei : "");
		}

		Parties parties = (Parties) RESTUtil.getRequest(webClient, uri, Parties.class);

		if (parties == null || parties.size() == 0) {
			logger.warn("Invalid parties object or no parties");
			System.out.println("no parties found");
			printHeader();
		} else {
			System.out.println("complete");
			printHeader();
			int rows = 1;
			for (Party party : parties) {
				if (rows % 15 == 0) {
					printHeader();
				}

				System.out.print(ConsoleOutputUtil.padSpaces(party.getPartyId(), 25));
				System.out.print(ConsoleOutputUtil.padSpaces(party.getPartyName(), 40));
				System.out.print(ConsoleOutputUtil.padSpaces(party.getGleifLei(), 35));
				System.out.print(ConsoleOutputUtil.padSpaces(party.getPartyType().getValue(), 20));

				System.out.println();

				rows++;
			}
		}
		System.out.println();
	}

	public void printHeader() {
		System.out.println();
		System.out.print(ConsoleOutputUtil.padSpaces("Party Id", 25));
		System.out.print(ConsoleOutputUtil.padSpaces("Party Name", 40));
		System.out.print(ConsoleOutputUtil.padSpaces("GLEIF LEI", 35));
		System.out.print(ConsoleOutputUtil.padSpaces("Party Type", 20));
		System.out.println();
		System.out.print(ConsoleOutputUtil.padSpaces("--------", 25));
		System.out.print(ConsoleOutputUtil.padSpaces("----------", 40));
		System.out.print(ConsoleOutputUtil.padSpaces("---------", 35));
		System.out.print(ConsoleOutputUtil.padSpaces("----------", 20));
		System.out.println();
	}
}
