package com.os.console;

import java.io.BufferedReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;

import com.os.client.model.CurrencyCd;
import com.os.console.api.ApplicationConfig;
import com.os.console.api.tasks.ReportMarkToMarketTask;

public class OperationsConsole extends AbstractConsole {

	@Autowired
	WebClient ledgerSearchWebClient;

	public OperationsConsole() {

	}

	protected boolean prompt() {
		System.out.print(ApplicationConfig.ACTING_PARTY.getPartyId() + " /operations > ");
		return true;
	}

	public void handleArgs(String args[], BufferedReader consoleIn) {

		if (args[0].equals("M")) {

			CurrencyCd currencyCd = null;

			if (args.length == 2 && args[1].length() <= 30) {
				currencyCd = CurrencyCd.fromValue(args[1]);
			}

			if (currencyCd == null) {
				System.out.println("Currency is required");
			} else {
				try {
					System.out.print("Generating Mark to Market report for " + currencyCd.getValue() + "...");
					ReportMarkToMarketTask reportMarkToMarketTask = new ReportMarkToMarketTask(ledgerSearchWebClient, currencyCd);
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
			}
		} else {
			System.out.println("Unknown command");
		}

	}

	protected void printMenu() {
		System.out.println("Operations Menu");
		System.out.println("-----------------------");
		System.out.println("M <Billing Currency> - Mark to Market Report");
		System.out.println("R <Party ID>         - Volume Weighted Rate Report");
		System.out.println();
		System.out.println("X                    - Go back");
	}

}
