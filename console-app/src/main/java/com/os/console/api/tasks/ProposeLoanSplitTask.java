package com.os.console.api.tasks;

import org.springframework.web.reactive.function.client.WebClient;

import com.os.client.model.Loan;
import com.os.client.model.LoanSplitProposal;
import com.os.client.model.Party;
import com.os.client.model.TransactingParties;
import com.os.client.model.TransactingParty;
import com.os.console.util.RESTUtil;

public class ProposeLoanSplitTask implements Runnable {

	private WebClient webClient;
	private Loan loan;
	private LoanSplitProposal loanSplitProposal;
	private Party actingParty;

	public ProposeLoanSplitTask(WebClient webClient, Loan loan, LoanSplitProposal loanSplitProposal, Party actingParty) {
		this.webClient = webClient;
		this.loan = loan;
		this.loanSplitProposal = loanSplitProposal;
		this.actingParty = actingParty;
	}

	@Override
	public void run() {

		TransactingParties parties = loan.getTrade().getTransactingParties();
		boolean canAct = false;
		for (TransactingParty transactingParty : parties) {
			if (actingParty.equals(transactingParty.getParty())) {
				canAct = true;
				break;
			}
		}
		
		if (!canAct) {
			System.out.println("Not a transacting party");			
			return;
		}

		RESTUtil.postRequest(webClient, "/loans/" + loan.getLoanId() + "/split", loanSplitProposal);

	}
}
