package com.os.console.api.tasks;

import org.springframework.web.reactive.function.client.WebClient;

import com.os.client.model.Loan;
import com.os.client.model.LoanSplit;
import com.os.client.model.LoanSplitAcknowledgment;
import com.os.console.util.RESTUtil;

public class ApproveLoanSplitTask implements Runnable {

	private WebClient webClient;
	private Loan loan;
	private LoanSplit loanSplit;
	private LoanSplitAcknowledgment loanSplitAcknowledgment;

	public ApproveLoanSplitTask(WebClient webClient, Loan loan, LoanSplit loanSplit, LoanSplitAcknowledgment loanSplitAcknowledgment) {
		this.webClient = webClient;
		this.loan = loan;
		this.loanSplit = loanSplit;
		this.loanSplitAcknowledgment = loanSplitAcknowledgment;
	}

	@Override
	public void run() {
		RESTUtil.postRequest(webClient, "/loans/" + loan.getLoanId() + "/split/" + loanSplit.getLoanSplitId() + "/acknowledge", loanSplitAcknowledgment);
	}
}
