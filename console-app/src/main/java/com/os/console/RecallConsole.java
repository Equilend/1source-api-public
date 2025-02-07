package com.os.console;

import java.io.BufferedReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.reactive.function.client.WebClient;

import com.os.client.model.AcknowledgementType;
import com.os.client.model.Loan;
import com.os.client.model.Recall;
import com.os.client.model.RecallAcknowledgement;
import com.os.console.api.ConsoleConfig;
import com.os.console.api.tasks.AcknowledgeRecallTask;
import com.os.console.api.tasks.CancelRecallTask;
import com.os.console.api.tasks.SearchLoanRecallTask;
import com.os.console.api.tasks.SearchLoanTask;
import com.os.console.util.ConsoleOutputUtil;
import com.os.console.util.PayloadUtil;

public class RecallConsole extends AbstractConsole {

	private static final Logger logger = LoggerFactory.getLogger(RecallConsole.class);

	Recall recall;

	public RecallConsole(Recall recall) {
		this.recall = recall;
	}

	protected boolean prompt() {
		
		if (recall == null) {
			System.out.println("Recall not available");
			return false;
		}
		
		System.out.print(ConsoleConfig.ACTING_PARTY.getPartyId() + " /recalls/" + recall.getRecallId() + " > ");
		
		return true;
	}

	public void handleArgs(String args[], BufferedReader consoleIn, WebClient webClient) {

		if (args[0].equals("J")) {

			ConsoleOutputUtil.printObject(recall);

		} else if (args[0].equals("P")) {
			String message = null;
			if (args.length == 2) {
				message = args[1];
				if (message.length() > 100) {
					System.out.println("Invalid acknowledgement message");
					message = null;
				}
			}
			try {
				System.out.print("Postive acknowledging recall " + recall.getRecallId() + "...");

				RecallAcknowledgement recallAcknowledgement = PayloadUtil
						.createRecallAcknowledgement(AcknowledgementType.POSITIVE, message);

				ConsoleOutputUtil.printObject(recallAcknowledgement);

				AcknowledgeRecallTask acknowledgeRecallTask = new AcknowledgeRecallTask(webClient, recall,
						recallAcknowledgement);
				Thread taskT = new Thread(acknowledgeRecallTask);
				taskT.run();
				try {
					taskT.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} catch (Exception u) {
				System.out.println("Invalid acknowledgement message");
			}
		} else if (args[0].equals("N")) {
			String message = null;
			if (args.length == 2) {
				message = args[1];
				if (message.length() > 100) {
					System.out.println("Invalid acknowledgement message");
					message = null;
				}
			}
			try {
				System.out.print("Negative acknowledging recall " + recall.getRecallId() + "...");

				RecallAcknowledgement recallAcknowledgement = PayloadUtil
						.createRecallAcknowledgement(AcknowledgementType.NEGATIVE, message);

				ConsoleOutputUtil.printObject(recallAcknowledgement);

				AcknowledgeRecallTask acknowledgeRecallTask = new AcknowledgeRecallTask(webClient, recall,
						recallAcknowledgement);
				
				Thread taskT = new Thread(acknowledgeRecallTask);
				taskT.run();
				try {
					taskT.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} catch (Exception u) {
				System.out.println("Invalid acknowledgement message");
			}
		} else if (args[0].equals("C")) {
			System.out.print("Searching for loan " + recall.getLoanId() + "...");

			SearchLoanTask searchLoanTask = new SearchLoanTask(webClient, recall.getLoanId());
			Thread taskT = new Thread(searchLoanTask);
			taskT.run();
			try {
				taskT.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			if (searchLoanTask.getLoan() != null) {
				System.out.print("Canceling recall...");
				CancelRecallTask cancelRecallTask = new CancelRecallTask(webClient, recall.getLoanId(), recall.getRecallId());
				Thread taskS = new Thread(cancelRecallTask);
				taskS.run();
				try {
					taskS.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				refreshRecall(webClient, searchLoanTask.getLoan());
			}
		} else {
			System.out.println("Unknown command");
		}
	}

	private void refreshRecall(WebClient webClient, Loan loan) {

		System.out.print("Refreshing recall " + recall.getRecallId() + "...");
		SearchLoanRecallTask searchLoanRecallTask = new SearchLoanRecallTask(webClient, loan.getLoanId(),
				recall.getRecallId());
		Thread taskT = new Thread(searchLoanRecallTask);
		taskT.run();
		try {
			taskT.join();
		} catch (InterruptedException e) {
			logger.error(e.getMessage(), e);
		}
		recall = searchLoanRecallTask.getRecall();
	}

	protected void printMenu() {
		System.out.println("Recall Menu");
		System.out.println("-----------------------");
		System.out.println("J             - Print JSON");
		System.out.println();
		System.out.println("P <Message>   - Positively acknowledge with optional message");
		System.out.println("N <Message>   - Negatively acknowledge with optional message");
		System.out.println("C             - Cancel");
		System.out.println();
		System.out.println("X             - Go back");
	}

}
