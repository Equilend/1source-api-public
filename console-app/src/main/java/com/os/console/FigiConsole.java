package com.os.console;

import java.io.BufferedReader;

import org.springframework.web.reactive.function.client.WebClient;

import com.os.client.model.Instrument;
import com.os.console.api.ConsoleConfig;
import com.os.console.util.ConsoleOutputUtil;
import com.os.console.util.FigiUtil;
import com.os.console.util.InstrumentUtil;

public class FigiConsole extends AbstractConsole {

	public FigiConsole() {

	}

	protected boolean prompt() {
		System.out.print(ConsoleConfig.ACTING_PARTY.getPartyId() + " /figi > ");
		return true;
	}

	public void handleArgs(String args[], BufferedReader consoleIn, WebClient webClient) {

		if (args[0].equals("A")) {
			if (args.length != 2 || args[1].length() == 0 || args[1].length() > 50) {
				System.out.println("Invalid identifier");
			} else {
				String securityId = args[1];
				System.out.print("Looking up " + securityId + "...");
				Instrument instrument = InstrumentUtil.getInstance().getInstrument(securityId);

				if (instrument == null) {
					System.out.println("not found");
				} else {
					ConsoleOutputUtil.printObject(instrument);
				}
			}
		} else if (args[0].equals("T")) {
			if (args.length != 2 || args[1].length() == 0 || args[1].length() > 50) {
				System.out.println("Invalid identifier");
			} else {
				String securityId = args[1];
				System.out.print("Looking up TICKER " + securityId + "...");
				FigiUtil.mapFigi(webClient, "TICKER", securityId);
			}
		} else if (args[0].equals("I")) {
			if (args.length != 2 || args[1].length() == 0 || args[1].length() > 50) {
				System.out.println("Invalid identifier");
			} else {
				String securityId = args[1];
				System.out.print("Looking up ISIN " + securityId + "...");
				FigiUtil.mapFigi(webClient, "ID_ISIN", securityId);
			}
		} else if (args[0].equals("C")) {
			if (args.length != 2 || args[1].length() == 0 || args[1].length() > 50) {
				System.out.println("Invalid identifier");
			} else {
				String securityId = args[1];
				System.out.print("Looking up CUSIP " + securityId + "...");
				FigiUtil.mapFigi(webClient, "ID_CUSIP", securityId);
			}
		} else if (args[0].equals("S")) {
			if (args.length != 2 || args[1].length() == 0 || args[1].length() > 50) {
				System.out.println("Invalid identifier");
			} else {
				String securityId = args[1];
				System.out.print("Looking up SEDOL " + securityId + "...");
				FigiUtil.mapFigi(webClient, "ID_SEDOL", securityId);
			}
		} else {
			System.out.println("Unknown command");
		}

	}

	protected void printMenu() {
		System.out.println("Figi Menu");
		System.out.println("-----------------------");
		System.out.println("A <identifier>    - Search the console instrument cache for the identifier");
		System.out.println();
		System.out.println("T <identifier>    - Search openfigi.com for TICKER");
		System.out.println("I <identifier>    - Search openfigi.com for ISIN");
		System.out.println("C <identifier>    - Search openfigi.com for CUSIP");
		System.out.println("S <identifier>    - Search openfigi.com for SEDOL");
		System.out.println();
		System.out.println("X                 - Go back");
	}

}
