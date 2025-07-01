package com.os.console;

import java.io.BufferedReader;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;

import com.os.client.model.ModelReturn;
import com.os.console.api.ApplicationConfig;
import com.os.console.api.tasks.CancelReturnTask;
import com.os.console.api.tasks.SearchReturnTask;
import com.os.console.api.tasks.SearchReturnsTask;

public class ReturnsConsole extends AbstractConsole {

	@Autowired
	WebClient restWebClient;

	protected boolean prompt() {
		System.out.print(ApplicationConfig.ACTING_PARTY.getPartyId() + " /returns > ");
		return true;
	}

	public void handleArgs(String args[], BufferedReader consoleIn) {

		if (args[0].equals("I")) {
			System.out.print("Listing all returns...");
			SearchReturnsTask searchReturnsTask = new SearchReturnsTask(restWebClient);
			Thread taskT = new Thread(searchReturnsTask);
			taskT.run();
			try {
				taskT.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else if (args[0].equals("S")) {
			if (args.length != 2 || args[1].length() != 36) {
				System.out.println("Invalid UUID");
			} else {
				String returnId = args[1];
				try {
					if (UUID.fromString(returnId).toString().equals(returnId)) {
						System.out.print("Retrieving return " + returnId + "...");
						SearchReturnTask searchReturnTask = new SearchReturnTask(restWebClient, returnId);
						Thread taskT = new Thread(searchReturnTask);
						taskT.run();
						try {
							taskT.join();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						if (searchReturnTask.getReturn() != null) {
							ReturnConsole returnConsole = new ReturnConsole(searchReturnTask.getReturn());
							returnConsole.execute(consoleIn);
						}
					} else {
						System.out.println("Invalid UUID");
					}
				} catch (Exception u) {
					System.out.println("Invalid UUID");
				}
			}
		} else if (args[0].equals("C")) {
			if (args.length != 2 || args[1].length() != 36) {
				System.out.println("Invalid UUID");
			} else {
				String returnId = args[1];
				try {
					if (UUID.fromString(returnId).toString().equals(returnId)) {
						System.out.print("Retrieving return " + returnId + "...");
						SearchReturnTask searchReturnTask = new SearchReturnTask(restWebClient, returnId);
						Thread taskT = new Thread(searchReturnTask);
						taskT.run();
						try {
							taskT.join();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						if (searchReturnTask.getReturn() != null) {
							ModelReturn modelReturn = searchReturnTask.getReturn();
							System.out.print("Canceling return...");
							CancelReturnTask cancelReturnTask = new CancelReturnTask(restWebClient, modelReturn.getLoanId(), modelReturn.getReturnId());
							Thread taskS = new Thread(cancelReturnTask);
							taskS.run();
							try {
								taskS.join();
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					} else {
						System.out.println("Invalid UUID");
					}
				} catch (Exception u) {
					System.out.println("Invalid UUID");
				}
			}
		} else {
			System.out.println("Unknown command");
		}
	}

	protected void printMenu() {
		System.out.println("Returns Menu");
		System.out.println("-----------------------");
		System.out.println("I             - List all returns");
		System.out.println("S <Return Id> - Load a return by Id");
		System.out.println();
		System.out.println("C <Return Id> - Cancel a return by Id");
		System.out.println();
		System.out.println("X             - Go back");
	}

}
