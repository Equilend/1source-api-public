package com.os.console;

import java.io.BufferedReader;
import org.springframework.web.reactive.function.client.WebClient;

import com.os.client.model.Party;
import com.os.console.api.ConsoleConfig;
import com.os.console.api.tasks.ReportMarkToMarketTask;
import com.os.console.api.tasks.SearchPartyTask;

public class OperationsConsole extends AbstractConsole {

	public OperationsConsole() {

	}

	protected boolean prompt() {
		System.out.print(ConsoleConfig.ACTING_PARTY.getPartyId() + " /operations > ");
		return true;
	}

	public void handleArgs(String args[], BufferedReader consoleIn, WebClient webClient) {

		if (args[0].equals("M")) {

			Party searchParty = null;

			String partyId = null;
			if (args.length == 2 && args[1].length() <= 30) {
				partyId = args[1];
				if (ConsoleConfig.ACTING_PARTY.getPartyId().equals(args[1])) {
					System.out.println("You are not a counterparty to yourself");
				} else {
					try {
						System.out.print("Verifying party " + partyId + "...");
						SearchPartyTask searchPartyTask = new SearchPartyTask(webClient, partyId);
						Thread taskT = new Thread(searchPartyTask);
						taskT.run();
						try {
							taskT.join();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						if (searchPartyTask.getParty() != null) {
							searchParty = searchPartyTask.getParty();
						}
					} catch (Exception u) {
						System.out.println("Invalid party id");
					}
				}
			} else {
				System.out.println("Invalid Party Id");
			}

			try {
				if (searchParty != null) {
					System.out.print("Generating Mark to Market report for " + searchParty.getPartyId() + " ...");
				} else {
					System.out.print("Generating Mark to Market report...");
				}
				ReportMarkToMarketTask reportMarkToMarketTask = new ReportMarkToMarketTask(webClient, searchParty);
				Thread taskF = new Thread(reportMarkToMarketTask);
				taskF.run();
				try {
					taskF.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} catch (Exception u) {
				System.out.println("Invalid party id");
			}

		} else {
			System.out.println("Unknown command");
		}

	}

	protected void printMenu() {
		System.out.println("Operations Menu");
		System.out.println("-----------------------");
		System.out.println("M <Party ID>         - Mark to Market Report");
		System.out.println("C <Party ID>         - Collateral Exposure Report");
		System.out.println("R <Party ID>         - Volume Weighted Rate Report");
		System.out.println();
		System.out.println("X                    - Go back");
	}

}
