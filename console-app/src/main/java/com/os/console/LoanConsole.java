package com.os.console;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.reactive.function.client.WebClient;

import com.os.client.model.EventType;
import com.os.client.model.Loan;
import com.os.client.model.SettlementInstructionUpdate;
import com.os.console.api.ConsoleConfig;
import com.os.console.api.tasks.ApproveLoanTask;
import com.os.console.api.tasks.CancelLoanTask;
import com.os.console.api.tasks.CancelPendingLoanTask;
import com.os.console.api.tasks.DeclineLoanTask;
import com.os.console.api.tasks.ProposeLoanSplitTask;
import com.os.console.api.tasks.SearchLoanEventsTask;
import com.os.console.api.tasks.SearchLoanHistoryTask;
import com.os.console.api.tasks.SearchLoanRateHistoryTask;
import com.os.console.api.tasks.SearchLoanSplitTask;
import com.os.console.api.tasks.SearchLoanTask;
import com.os.console.api.tasks.UpdateLoanInternalReferenceTask;
import com.os.console.api.tasks.UpdateLoanSettlementInstructionTask;
import com.os.console.api.tasks.UpdateLoanSettlementStatusTask;
import com.os.console.util.ConsoleOutputUtil;
import com.os.console.util.PayloadUtil;

public class LoanConsole extends AbstractConsole {

	private static final Logger logger = LoggerFactory.getLogger(LoanConsole.class);

	Loan loan;

	public LoanConsole(Loan loan) {
		this.loan = loan;
	}

	protected boolean prompt() {

		if (loan == null) {
			System.out.println("Loan not available");
			return false;
		}

		System.out.print(ConsoleConfig.ACTING_PARTY.getPartyId() + " /loans/" + loan.getLoanId() + " > ");

		return true;
	}

	public void handleArgs(String args[], BufferedReader consoleIn, WebClient webClient) {

		if (args[0].equals("J")) {
			ConsoleOutputUtil.printObject(loan);
		} else if (args[0].equals("F")) {
			refreshLoan(webClient);
		} else if (args[0].equals("H")) {
			System.out.print("Listing full history...");
			SearchLoanHistoryTask searchLoanHistoryTask = new SearchLoanHistoryTask(webClient, loan);
			Thread taskT = new Thread(searchLoanHistoryTask);
			taskT.run();
			try {
				taskT.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else if (args[0].equals("Y")) {
			System.out.print("Listing rate change history...");
			SearchLoanRateHistoryTask searchLoanRateHistoryTask = new SearchLoanRateHistoryTask(webClient, loan);
			Thread taskT = new Thread(searchLoanRateHistoryTask);
			taskT.run();
			try {
				taskT.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else if (args[0].equals("V")) {

			// The second arg may need to be split
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

			Integer days = 1;
			EventType eventType = null;

			SearchLoanEventsTask searchLoanEventsTask = null;

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
									searchLoanEventsTask = new SearchLoanEventsTask(webClient, loan, days, eventType);
								}
							} catch (Exception u) {
								System.out.println("Invalid Event Type");
							}
						} else {
							searchLoanEventsTask = new SearchLoanEventsTask(webClient, loan, days, null);
						}
					} else {
						System.out.println("Invalid Days value. Maximum is 60.");
					}
				} catch (Exception e) {
					System.out.println("Invalid Days value. Maximum is 60.");
				}

			} else {
				searchLoanEventsTask = new SearchLoanEventsTask(webClient, loan, null, null);
			}

			if (searchLoanEventsTask != null) {
				System.out.print("Listing last " + days + " days of "
						+ (eventType != null ? eventType.getValue() : "ALL") + " events...");

				Thread taskT = new Thread(searchLoanEventsTask);
				taskT.run();
				try {
					taskT.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		} else if (args[0].equals("A")) {

			System.out.print("Approving loan...");
			ApproveLoanTask approveLoanTask = new ApproveLoanTask(webClient, loan,
					PayloadUtil.createLoanProposalApproval());
			Thread taskT = new Thread(approveLoanTask);
			taskT.run();
			try {
				taskT.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			refreshLoan(webClient);
		} else if (args[0].equals("C")) {
			System.out.print("Canceling loan...");
			CancelLoanTask cancelLoanTask = new CancelLoanTask(webClient, loan.getLoanId(), PayloadUtil.createLoanCancelErrorResponse());
			Thread taskT = new Thread(cancelLoanTask);
			taskT.run();
			try {
				taskT.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			refreshLoan(webClient);
		} else if (args[0].equals("N")) {
			System.out.print("Canceling Pending loan...");
			CancelPendingLoanTask cancelPendingLoanTask = new CancelPendingLoanTask(webClient, loan.getLoanId());
			Thread taskT = new Thread(cancelPendingLoanTask);
			taskT.run();
			try {
				taskT.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			refreshLoan(webClient);
		} else if (args[0].equals("D")) {
			System.out.print("Declining loan...");
			DeclineLoanTask declineLoanTask = new DeclineLoanTask(webClient, loan.getLoanId(), PayloadUtil.createLoanDeclineErrorResponse(loan));
			Thread taskT = new Thread(declineLoanTask);
			taskT.run();
			try {
				taskT.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			refreshLoan(webClient);
		} else if (args[0].equals("L")) {
			if (args.length != 2) {
				System.out.println("Add quantity splits as x,y,z");
			} else {
				ArrayList<Integer> quantitySplits = new ArrayList<>();
				try {
					String[] splits = args[1].split(",");
					for (int s = 0; s < splits.length; s++) {
						quantitySplits.add(Integer.parseInt(splits[s]));
					}
				} catch (Exception e) {
					System.out.println("Could not parse " + args[1]);
				}

				if (quantitySplits.size() > 1) {
					try {

						System.out.print("Splitting loan...");
						ProposeLoanSplitTask proposeLoanSplitTask = new ProposeLoanSplitTask(webClient, loan,
								PayloadUtil.createLoanSplitProposal(quantitySplits), ConsoleConfig.ACTING_PARTY);
						Thread taskU = new Thread(proposeLoanSplitTask);
						taskU.run();
						try {
							taskU.join();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}

					} catch (Exception u) {
						System.out.println("Invalid UUID");
					}
				} else {
					System.out.println("Add quantity splits as x,y,z");
				}
			}
		} else if (args[0].equals("S")) {
			if (args.length != 2 || args[1].length() == 0 || args[1].length() != 36) {
				System.out.println("Invalid UUID");
			} else {
				String splitId = args[1];
				try {
					if (UUID.fromString(splitId).toString().equals(splitId)) {
						System.out.print("Retrieving split proposal " + splitId + "...");
						SearchLoanSplitTask searchLoanSplitTask = new SearchLoanSplitTask(webClient, loan.getLoanId(),
								splitId);
						Thread taskT = new Thread(searchLoanSplitTask);
						taskT.run();
						try {
							taskT.join();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						if (searchLoanSplitTask.getLoanSplit() != null) {
							LoanSplitConsole loanSplitConsole = new LoanSplitConsole(loan,
									searchLoanSplitTask.getLoanSplit());
							loanSplitConsole.execute(consoleIn, webClient);
						}
					} else {
						System.out.println("Invalid UUID");
					}
				} catch (Exception u) {
					System.out.println("Invalid UUID");
				}
			}
		} else if (args[0].equals("U")) {
			System.out.print("Updating settlement status to SETTLED...");

			UpdateLoanSettlementStatusTask updateSettlementStatusTask = new UpdateLoanSettlementStatusTask(webClient,
					loan, ConsoleConfig.ACTING_PARTY);
			Thread taskT = new Thread(updateSettlementStatusTask);
			taskT.run();
			try {
				taskT.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			refreshLoan(webClient);
		} else if (args[0].equals("I")) {
			if (args.length != 2 || args[1].length() == 0 || args[1].length() > 50) {
				System.out.println("Invalid internal reference id");
			} else {
				String internalRefId = args[1];
				try {
					System.out.print("Assigning internal reference id " + internalRefId + "...");
					UpdateLoanInternalReferenceTask updateLoanInternalReferenceTask = new UpdateLoanInternalReferenceTask(
							webClient, loan.getLoanId(), internalRefId);
					Thread taskT = new Thread(updateLoanInternalReferenceTask);
					taskT.run();
					try {
						taskT.join();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					refreshLoan(webClient);
				} catch (Exception u) {
					System.out.println("Invalid internal reference id");
				}
			}
		} else if (args[0].equals("K")) {
			if (args.length != 2 || args[1].length() != 4) {
				System.out.println("Invalid DTC box number. Must be 4 digits.");
			} else {
				String dtcParticipantNumber = args[1];
				try {
					System.out.print(
							"Updating settlement instruction DTC Participant umber to " + dtcParticipantNumber + "...");

					SettlementInstructionUpdate settlementInstructionUpdate = PayloadUtil
							.createSettlementInstructionUpdate(loan, dtcParticipantNumber);
					UpdateLoanSettlementInstructionTask updateLoanSettlementInstructionTask = new UpdateLoanSettlementInstructionTask(
							webClient, loan.getLoanId(), settlementInstructionUpdate);
					Thread taskT = new Thread(updateLoanSettlementInstructionTask);
					taskT.run();
					try {
						taskT.join();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					refreshLoan(webClient);
				} catch (Exception u) {
					System.out.println("Invalid internal reference id");
				}
			}
		} else if (args[0].equals("R")) {
			LoanReturnsConsole loanReturnsConsole = new LoanReturnsConsole(loan);
			loanReturnsConsole.execute(consoleIn, webClient);
		} else if (args[0].equals("E")) {
			LoanRecallsConsole loanRecallsConsole = new LoanRecallsConsole(loan);
			loanRecallsConsole.execute(consoleIn, webClient);
		} else if (args[0].equals("T")) {
			LoanReratesConsole loanReratesConsole = new LoanReratesConsole(loan);
			loanReratesConsole.execute(consoleIn, webClient);
		} else {
			System.out.println("Unknown command");
		}
	}

	private void refreshLoan(WebClient webClient) {

		System.out.print("Refreshing loan " + loan.getLoanId() + "...");
		SearchLoanTask searchLoanTask = new SearchLoanTask(webClient, loan.getLoanId());
		Thread taskT = new Thread(searchLoanTask);
		taskT.run();
		try {
			taskT.join();
		} catch (InterruptedException e) {
			logger.error(e.getMessage(), e);
		}
		loan = searchLoanTask.getLoan();
	}

	protected void printMenu() {
		System.out.println("Loan Menu");
		System.out.println("-----------------------------");
		System.out.println("J                     - Print JSON");
		System.out.println("F                     - Refresh");
		System.out.println("H                     - Full history");
		System.out.println("Y                     - Rate change history");
		System.out.println("V <Days> <Event Type> - List events for last Days of Event Type. Defaults to All events for current business day");
		System.out.println();
		System.out.println("A                     - Approve");
		System.out.println("C                     - Cancel");
		System.out.println("D                     - Decline");
		System.out.println("U                     - Update settlement status to SETTLED");
		System.out.println();
		System.out.println("N                     - Request to cancel (Pending Loans Only)");
		System.out.println("I <Internal Ref>      - Update internal reference Id (Open Loans Only)");
		System.out.println("K <DTC Number>        - Update settlement instructions to a new DTC box");
		System.out.println("L <x,y,z>             - Split loan into x,y,z... quantities (Open Loans Only)");
		System.out.println("S <Split ID>          - Search a split proposal by Id");
		System.out.println();
		System.out.println("R                     - Manage returns");
		System.out.println("E                     - Manage recalls");
		System.out.println("T                     - Manage rerates");
		System.out.println();
		System.out.println("X                     - Go back");
	}

}
