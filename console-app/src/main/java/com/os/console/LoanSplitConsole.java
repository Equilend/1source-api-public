package com.os.console;

import java.io.BufferedReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;

import com.os.client.model.Loan;
import com.os.client.model.LoanSplit;
import com.os.client.model.LoanSplitAcknowledgment;
import com.os.console.api.ApplicationConfig;
import com.os.console.api.tasks.ApproveLoanSplitTask;
import com.os.console.util.ConsoleOutputUtil;
import com.os.console.util.PayloadUtil;

public class LoanSplitConsole extends AbstractConsole {

	//private static final Logger logger = LoggerFactory.getLogger(LoanSplitConsole.class);

	@Autowired
	WebClient restWebClient;

	private Loan loan;
	private LoanSplit loanSplit;

	public LoanSplitConsole(Loan loan, LoanSplit loanSplit) {
		this.loan = loan;
		this.loanSplit = loanSplit;
	}

	protected boolean prompt() {

		if (loan == null) {
			System.out.println("Loan not available");
			return false;
		} else if (loanSplit == null) {
			System.out.println("Loan Split not available");
			return false;
		}

		System.out.print(ApplicationConfig.ACTING_PARTY.getPartyId() + " /loans/" + loan.getLoanId() + "/split/" + loanSplit.getLoanSplitId() + " > ");
		return true;
	}

	public void handleArgs(String args[], BufferedReader consoleIn) {

		if (args[0].equals("J")) {
			ConsoleOutputUtil.printObject(loanSplit);
		} else if (args[0].equals("A")) {
			try {
				System.out.print("Acknowledging loan split " + loanSplit.getLoanSplitId() + "...");

				LoanSplitAcknowledgment loanSplitAcknowledgment = PayloadUtil
						.createLoanSplitProposalApproval(loanSplit);

				ConsoleOutputUtil.printObject(loanSplitAcknowledgment);

				ApproveLoanSplitTask approveLoanSplitTask = new ApproveLoanSplitTask(restWebClient, loan, loanSplit,
						loanSplitAcknowledgment);
				Thread taskT = new Thread(approveLoanSplitTask);
				taskT.run();
				try {
					taskT.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} catch (Exception u) {
				System.out.println("Invalid acknowledge message");
			}
		} else {
			System.out.println("Unknown command");
		}
	}

	protected void printMenu() {
		System.out.println("Loan Split Menu");
		System.out.println("-----------------------");
		System.out.println("J    - Print JSON");
		System.out.println();
		System.out.println("A    - Acknowledge the loan split");
		System.out.println();
		System.out.println("X    - Go back");
	}

}
