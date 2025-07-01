package com.os.console;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.UUID;

import org.springframework.web.reactive.function.client.WebClient;

import com.os.client.model.LoanStatus;
import com.os.client.model.Party;
import com.os.client.model.PartyRole;
import com.os.console.api.ApplicationConfig;
import com.os.console.api.tasks.ApproveLoanTask;
import com.os.console.api.tasks.CancelLoanTask;
import com.os.console.api.tasks.CancelPendingLoanTask;
import com.os.console.api.tasks.DeclineLoanTask;
import com.os.console.api.tasks.OpenSearchQueryTask;
import com.os.console.api.tasks.ProposeLoanSplitTask;
import com.os.console.api.tasks.SearchLoanHistoryTask;
import com.os.console.api.tasks.SearchLoanMarksTask;
import com.os.console.api.tasks.SearchLoanRateHistoryTask;
import com.os.console.api.tasks.SearchLoanTask;
import com.os.console.api.tasks.SearchLoansTask;
import com.os.console.api.tasks.SearchPartyTask;
import com.os.console.api.tasks.UpdateLoanSettlementStatusTask;
import com.os.console.util.ConsoleOutputUtil;
import com.os.console.util.PayloadUtil;

public class LoansConsole extends AbstractConsole {

	private WebClient restWebClient;
	private WebClient ledgerSearchWebClient;
	
	public LoansConsole(WebClient restWebClient, WebClient ledgerSearchWebClient) {
		this.restWebClient = restWebClient;
		this.ledgerSearchWebClient = ledgerSearchWebClient;
	}

	protected boolean prompt() {
		System.out.print(ApplicationConfig.ACTING_PARTY.getPartyId() + " /loans > ");
		return true;
	}

	public void handleArgs(String args[], BufferedReader consoleIn) {

		if (args[0].equals("I")) {
			LoanStatus loanStatus = null;
			if (args.length == 2) {
				loanStatus = LoanStatus.fromValue(args[1].toUpperCase());
			}
			System.out.print("Listing all " + (loanStatus != null ? (loanStatus.getValue() + " ") : "") + "loans...");
			SearchLoansTask searchLoansTask = new SearchLoansTask(restWebClient, loanStatus, null);
			Thread taskT = new Thread(searchLoansTask);
			taskT.run();
			try {
				taskT.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else if (args[0].equals("J")) {
			if (args.length != 2 || args[1].length() != 36) {
				System.out.println("Invalid UUID");
			} else {
				String loanId = args[1].toLowerCase();
				try {
					if (UUID.fromString(loanId).toString().equalsIgnoreCase(loanId)) {
						System.out.print("Searching for loan " + loanId + "...");
						SearchLoanTask searchLoanTask = new SearchLoanTask(restWebClient, loanId);
						Thread taskT = new Thread(searchLoanTask);
						taskT.run();
						try {
							taskT.join();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						if (searchLoanTask.getLoan() != null) {
							ConsoleOutputUtil.printObject(searchLoanTask.getLoan());
						}
					} else {
						System.out.println("Invalid UUID");
					}
				} catch (Exception u) {
					System.out.println("Invalid UUID");
				}
			}
		} else if (args[0].equals("S")) {
			if (args.length != 2 || args[1].length() != 36) {
				System.out.println("Invalid UUID");
			} else {
				String loanId = args[1].toLowerCase();
				try {
					if (UUID.fromString(loanId).toString().equalsIgnoreCase(loanId)) {
						System.out.print("Searching for loan " + loanId + "...");
						SearchLoanTask searchLoanTask = new SearchLoanTask(restWebClient, loanId);
						Thread taskT = new Thread(searchLoanTask);
						taskT.run();
						try {
							taskT.join();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						if (searchLoanTask.getLoan() != null) {
							LoanConsole loanConsole = new LoanConsole(searchLoanTask.getLoan());
							loanConsole.execute(consoleIn);
						}
					} else {
						System.out.println("Invalid UUID");
					}
				} catch (Exception u) {
					System.out.println("Invalid UUID");
				}
			}
		} else if (args[0].equals("R")) {
			if (args.length != 2 || args[1].trim().length() == 0 || args[1].length() > 100) {
				System.out.println("Invalid Internal Ref ID");
			} else {
				String internalRefId = args[1].toLowerCase();
				System.out.print("Searching with Internal Reference " + internalRefId + "...");
				OpenSearchQueryTask searchLoansInternalRefTask = new OpenSearchQueryTask(ledgerSearchWebClient,
						PayloadUtil.createInternalRefIdQuery(internalRefId));
				Thread taskS = new Thread(searchLoansInternalRefTask);
				taskS.run();
				try {
					taskS.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} else if (args[0].equals("H")) {
			if (args.length != 2 || args[1].length() != 36) {
				System.out.println("Invalid UUID");
			} else {
				String loanId = args[1].toLowerCase();
				try {
					if (UUID.fromString(loanId).toString().equalsIgnoreCase(loanId)) {
						System.out.print("Searching for loan " + loanId + "...");
						SearchLoanTask searchLoanTask = new SearchLoanTask(restWebClient, loanId);
						Thread taskT = new Thread(searchLoanTask);
						taskT.run();
						try {
							taskT.join();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						if (searchLoanTask.getLoan() != null) {
							System.out.print("Listing loan full history " + loanId + "...");
							SearchLoanHistoryTask searchLoanHistoryTask = new SearchLoanHistoryTask(restWebClient,
									searchLoanTask.getLoan());
							Thread taskS = new Thread(searchLoanHistoryTask);
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
		} else if (args[0].equals("Y")) {
			if (args.length != 2 || args[1].length() != 36) {
				System.out.println("Invalid UUID");
			} else {
				String loanId = args[1].toLowerCase();
				try {
					if (UUID.fromString(loanId).toString().equalsIgnoreCase(loanId)) {
						System.out.print("Searching for loan " + loanId + "...");
						SearchLoanTask searchLoanTask = new SearchLoanTask(restWebClient, loanId);
						Thread taskT = new Thread(searchLoanTask);
						taskT.run();
						try {
							taskT.join();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						if (searchLoanTask.getLoan() != null) {
							System.out.print("Listing loan rate change history " + loanId + "...");
							SearchLoanRateHistoryTask searchLoanRateHistoryTask = new SearchLoanRateHistoryTask(
									restWebClient, searchLoanTask.getLoan());
							Thread taskS = new Thread(searchLoanRateHistoryTask);
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
		} else if (args[0].equals("A")) {
			if (args.length != 2 || args[1].length() != 36) {
				System.out.println("Invalid UUID");
			} else {
				String loanId = args[1].toLowerCase();
				try {
					if (UUID.fromString(loanId).toString().equalsIgnoreCase(loanId)) {
						System.out.print("Searching for loan " + loanId + "...");
						SearchLoanTask searchLoanTask = new SearchLoanTask(restWebClient, loanId);
						Thread taskT = new Thread(searchLoanTask);
						taskT.run();
						try {
							taskT.join();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						if (searchLoanTask.getLoan() != null) {
							System.out.print("Approving loan " + loanId + "...");
							ApproveLoanTask approveLoanTask = new ApproveLoanTask(restWebClient, searchLoanTask.getLoan(),
									PayloadUtil.createLoanProposalApproval());
							Thread taskS = new Thread(approveLoanTask);
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
		} else if (args[0].equals("C")) {
			if (args.length != 2 || args[1].length() != 36) {
				System.out.println("Invalid UUID");
			} else {
				String loanId = args[1].toLowerCase();
				try {
					if (UUID.fromString(loanId).toString().equalsIgnoreCase(loanId)) {
						System.out.print("Canceling loan " + loanId + "...");
						CancelLoanTask cancelLoanTask = new CancelLoanTask(restWebClient, loanId,
								PayloadUtil.createLoanCancelErrorResponse());
						Thread taskT = new Thread(cancelLoanTask);
						taskT.run();
						try {
							taskT.join();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					} else {
						System.out.println("Invalid UUID");
					}
				} catch (Exception u) {
					System.out.println("Invalid UUID");
				}
			}
		} else if (args[0].equals("N")) {
			if (args.length != 2 || args[1].length() != 36) {
				System.out.println("Invalid UUID");
			} else {
				String loanId = args[1].toLowerCase();
				try {
					if (UUID.fromString(loanId).toString().equalsIgnoreCase(loanId)) {
						System.out.print("Canceling pending loan " + loanId + "...");
						CancelPendingLoanTask cancelPendingLoanTask = new CancelPendingLoanTask(restWebClient, loanId);
						Thread taskT = new Thread(cancelPendingLoanTask);
						taskT.run();
						try {
							taskT.join();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					} else {
						System.out.println("Invalid UUID");
					}
				} catch (Exception u) {
					System.out.println("Invalid UUID");
				}
			}
		} else if (args[0].equals("D")) {
			if (args.length != 2 || args[1].length() != 36) {
				System.out.println("Invalid UUID");
			} else {
				String loanId = args[1].toLowerCase();
				try {
					if (UUID.fromString(loanId).toString().equalsIgnoreCase(loanId)) {
						System.out.print("Declining loan " + loanId + "...");

						SearchLoanTask searchLoanTask = new SearchLoanTask(restWebClient, loanId);
						Thread taskT = new Thread(searchLoanTask);
						taskT.run();
						try {
							taskT.join();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}

						if (searchLoanTask.getLoan() != null) {

							DeclineLoanTask declineLoanTask = new DeclineLoanTask(restWebClient, loanId,
									PayloadUtil.createLoanDeclineErrorResponse(searchLoanTask.getLoan()));
							Thread taskD = new Thread(declineLoanTask);
							taskD.run();
							try {
								taskD.join();
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
		} else if (args[0].equals("L")) {
			if (args.length != 2) {
				System.out.println("Invalid UUID and split quantities");
			} else {
				int splitsIdx = args[1].indexOf(" ");
				if (splitsIdx > 0) {
					String loanId = args[1].substring(0, splitsIdx).toLowerCase();
					ArrayList<Integer> quantitySplits = new ArrayList<>();
					try {
						String[] splits = args[1].substring(splitsIdx + 1).split(",");
						for (int s = 0; s < splits.length; s++) {
							quantitySplits.add(Integer.parseInt(splits[s]));
						}
					} catch (Exception e) {
						System.out.println("Could not parse " + args[1].substring(splitsIdx + 1));
					}

					if (quantitySplits.size() > 1) {
						try {
							if (UUID.fromString(loanId).toString().equalsIgnoreCase(loanId)) {

								System.out.print("Retrieving loan " + loanId + "...");

								SearchLoanTask searchLoanTask = new SearchLoanTask(restWebClient, loanId);
								Thread taskT = new Thread(searchLoanTask);
								taskT.run();
								try {
									taskT.join();
								} catch (InterruptedException e) {
									e.printStackTrace();
								}

								if (searchLoanTask.getLoan() != null) {

									System.out.print("Splitting loan " + loanId + "...");
									ProposeLoanSplitTask proposeLoanSplitTask = new ProposeLoanSplitTask(restWebClient,
											searchLoanTask.getLoan(),
											PayloadUtil.createLoanSplitProposal(quantitySplits),
											ApplicationConfig.ACTING_PARTY);
									Thread taskU = new Thread(proposeLoanSplitTask);
									taskU.run();
									try {
										taskU.join();
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
					} else {
						System.out.println("Add quantity splits as x,y,z");
					}
				} else {
					System.out.println("Add quantity splits as x,y,z");
				}
			}
		} else if (args[0].equals("U")) {
			if (args.length != 2 || args[1].length() != 36) {
				System.out.println("Invalid UUID");
			} else {
				String loanId = args[1].toLowerCase();
				try {
					if (UUID.fromString(loanId).toString().equalsIgnoreCase(loanId)) {

						System.out.print("Retrieving loan " + loanId + "...");

						SearchLoanTask searchLoanTask = new SearchLoanTask(restWebClient, loanId);
						Thread taskT = new Thread(searchLoanTask);
						taskT.run();
						try {
							taskT.join();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}

						if (searchLoanTask.getLoan() != null) {
							System.out.print("Updating loan " + loanId + " settlement status to SETTLED...");
							UpdateLoanSettlementStatusTask updateSettlementStatusTask = new UpdateLoanSettlementStatusTask(
									restWebClient, searchLoanTask.getLoan(), ApplicationConfig.ACTING_PARTY);
							Thread taskU = new Thread(updateSettlementStatusTask);
							taskU.run();
							try {
								taskU.join();
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
		} else if (args[0].equals("P")) {
			if (args.length != 2 || args[1].length() > 30) {
				System.out.println("Invalid Party Id");
			} else if (ApplicationConfig.ACTING_PARTY.getPartyId().equals(args[1])) {
				System.out.println("You cannot propose a loan to yourself");
			} else {

				String partyId = args[1];
				try {
					System.out.print("Verifying party " + partyId + "...");
					SearchPartyTask searchPartyTask = new SearchPartyTask(restWebClient, partyId);
					Thread taskT = new Thread(searchPartyTask);
					taskT.run();
					try {
						taskT.join();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if (searchPartyTask.getParty() != null) {
						LoanProposalConsole loanProposalConsole = new LoanProposalConsole(
								(PartyRole.BORROWER.equals(ApplicationConfig.ACTING_AS) ? ApplicationConfig.ACTING_PARTY
										: searchPartyTask.getParty()),
								(PartyRole.LENDER.equals(ApplicationConfig.ACTING_AS) ? ApplicationConfig.ACTING_PARTY
										: searchPartyTask.getParty()),
								ApplicationConfig.ACTING_AS);
						loanProposalConsole.execute(consoleIn);
					}
				} catch (Exception u) {
					System.out.println("Invalid party id");
				}
			}
		} else if (args[0].equals("F")) {
			if (args.length != 2 || args[1].length() > 30) {
				System.out.println("Invalid Party Id");
			} else if (ApplicationConfig.ACTING_PARTY.getPartyId().equals(args[1])) {
				System.out.println("You are not a counterparty to yourself");
			} else {

				String partyId = args[1];
				try {
					System.out.print("Verifying party " + partyId + "...");
					SearchPartyTask searchPartyTask = new SearchPartyTask(restWebClient, partyId);
					Thread taskT = new Thread(searchPartyTask);
					taskT.run();
					try {
						taskT.join();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if (searchPartyTask.getParty() != null) {
						System.out.print("Listing all " + searchPartyTask.getParty().getPartyId() + " loans...");
						SearchLoansTask searchLoansTask = new SearchLoansTask(restWebClient, null,
								searchPartyTask.getParty());
						Thread taskF = new Thread(searchLoansTask);
						taskF.run();
						try {
							taskF.join();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				} catch (Exception u) {
					System.out.println("Invalid party id");
				}
			}
		} else if (args[0].equals("M")) {

			Party searchParty = null;

			String partyId = null;
			if (args.length == 2 && args[1].length() <= 30) {
				partyId = args[1];
				if (ApplicationConfig.ACTING_PARTY.getPartyId().equals(args[1])) {
					System.out.println("You are not a counterparty to yourself");
				} else {
					try {
						System.out.print("Verifying party " + partyId + "...");
						SearchPartyTask searchPartyTask = new SearchPartyTask(restWebClient, partyId);
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
					System.out.print("Listing all OPEN " + searchParty.getPartyId() + " loans...");
				} else {
					System.out.print("Listing all OPEN loans...");
				}
				SearchLoanMarksTask searchLoanMarksTask = new SearchLoanMarksTask(restWebClient, LoanStatus.OPEN,
						searchParty);
				Thread taskF = new Thread(searchLoanMarksTask);
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
		System.out.println("Loans Menu");
		System.out.println("-----------------------");
		System.out.println("I <Loan Status>      - List all loans");
		System.out.println("F <Party ID>         - List all loans with a counterparty");
		System.out.println("R <Internal Ref ID>  - List all loans with Internal Ref ID");
		System.out.println("V <Venue Ref Key>    - List all loans with Venue Ref Key");
		System.out.println();
		System.out.println("M <Party ID>         - List current marks for OPEN loans with optional counterparty");
		System.out.println();
		System.out.println("J <Loan Id>          - Print JSON for loan by Id");
		System.out.println("S <Loan Id>          - Search a loan by Id");
		System.out.println("H <Loan Id>          - Show full history for loan Id");
		System.out.println("Y <Loan Id>          - Show rate change history for loan Id");
		System.out.println();
		System.out.println("A <Loan Id>          - Approve a loan by Id");
		System.out.println("C <Loan Id>          - Cancel a loan by Id");
		System.out.println("D <Loan Id>          - Decline a loan by Id");
		System.out.println("U <Loan Id>          - Update settlement status to SETTLED");
		System.out.println();
		System.out.println("N <Loan Id>          - Request to cancel (Pending Loans Only)");
		System.out.println("L <Loan Id>  <x,y,z> - Split loan into x,y,z... quantities (Open Loans Only)");
		System.out.println();
		System.out.println("P <Party ID>         - Propose a loan to Party Id");
		System.out.println();
		System.out.println("X                    - Go back");
	}

}
