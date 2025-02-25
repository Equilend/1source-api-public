package com.os.console.api.tasks;

import java.util.Comparator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.reactive.function.client.WebClient;

import com.os.client.model.Loan;
import com.os.client.model.LoanStatus;
import com.os.client.model.Loans;
import com.os.client.model.Party;
import com.os.client.model.PartyRole;
import com.os.client.model.PartySettlementInstruction;
import com.os.client.model.TransactingParties;
import com.os.client.model.TransactingParty;
import com.os.console.util.ConsoleOutputUtil;
import com.os.console.util.RESTUtil;

public class SearchLoansTask implements Runnable, Comparator<Loan> {

	private static final Logger logger = LoggerFactory.getLogger(SearchLoansTask.class);

	private WebClient webClient;
	private LoanStatus loanStatus;
	private Party counterparty;

	public SearchLoansTask(WebClient webClient, LoanStatus loanStatus, Party counterparty) {
		this.webClient = webClient;
		this.loanStatus = loanStatus;
		this.counterparty = counterparty;
	}

	@Override
	public void run() {

		String uri = "/loans" + (loanStatus != null ? "?loanStatus=" + loanStatus.getValue() : "");
		
		Loans loans = (Loans) RESTUtil.getRequest(webClient, uri, Loans.class);

		if (loans == null || loans.size() == 0) {
			logger.warn("Invalid loans object or no loans");
			System.out.println("no loans found");
			printHeader();
		} else {
			
			loans.sort(this);
			
			System.out.println("complete");
			printHeader();
			int rows = 1;
			for (Loan loan : loans) {

				String borrower = null;
				String lender = null;
				TransactingParties parties = loan.getTrade().getTransactingParties(); // there should be 2
				if (parties != null) {
					for (TransactingParty party : parties) {
						if (borrower == null && PartyRole.BORROWER.equals(party.getPartyRole())) {
							borrower = party.getParty().getPartyId();
						} else if (lender == null && PartyRole.LENDER.equals(party.getPartyRole())) {
							lender = party.getParty().getPartyId();
						}

					}
				}

				if (counterparty != null && !counterparty.getPartyId().equals(borrower) && !counterparty.getPartyId().equals(lender)) {
					continue;
				}

				String borrowerSettlement = "NONE";
				String lenderSettlement = "NONE";
				List<PartySettlementInstruction> settlementInstructions = loan.getSettlement();
				if (settlementInstructions != null) {
					for (PartySettlementInstruction settlementInstruction : settlementInstructions) {
						if (PartyRole.BORROWER.equals(settlementInstruction.getPartyRole())) {
							borrowerSettlement = settlementInstruction.getSettlementStatus().toString();
						} else if (PartyRole.LENDER.equals(settlementInstruction.getPartyRole())) {
							lenderSettlement = settlementInstruction.getSettlementStatus().toString();
						}

					}
				}

				if (rows % 15 == 0) {
					printHeader();
				}

				System.out.print(ConsoleOutputUtil.padSpaces(loan.getLoanId(), 40));
				System.out.print(ConsoleOutputUtil.padSpaces(loan.getLoanStatus().toString(), 12));

				System.out.print(ConsoleOutputUtil.padSpaces(borrower, 15));
				System.out.print(ConsoleOutputUtil.padSpaces(borrowerSettlement, 14));
				System.out.print(ConsoleOutputUtil.padSpaces(lender, 15));
				System.out.print(ConsoleOutputUtil.padSpaces(lenderSettlement, 14));

				System.out.print(ConsoleOutputUtil.padSpaces(loan.getTrade().getTradeDate(), 15));
				System.out.print(ConsoleOutputUtil.padSpaces(loan.getTrade().getInstrument().getTicker(), 10));
				System.out.print(ConsoleOutputUtil.padSpaces(loan.getTrade().getQuantity(), 15));
				System.out.print(ConsoleOutputUtil.padSpaces(loan.getTrade().getOpenQuantity(), 15));
				System.out.println();

				rows++;
			}
		}
		System.out.println();
	}

	public void printHeader() {
		System.out.println();
		System.out.print(ConsoleOutputUtil.padSpaces("Loan Id", 40));
		System.out.print(ConsoleOutputUtil.padSpaces("Status", 12));
		System.out.print(ConsoleOutputUtil.padSpaces("Borrower", 15));
		System.out.print(ConsoleOutputUtil.padSpaces("Settlement", 14));
		System.out.print(ConsoleOutputUtil.padSpaces("Lender", 15));
		System.out.print(ConsoleOutputUtil.padSpaces("Settlement", 14));
		System.out.print(ConsoleOutputUtil.padSpaces("Trade Date", 15));
		System.out.print(ConsoleOutputUtil.padSpaces("Ticker", 10));
		System.out.print(ConsoleOutputUtil.padSpaces("Orig Quantity", 15));
		System.out.print(ConsoleOutputUtil.padSpaces("Open Quantity", 15));
		System.out.println();
		System.out.print(ConsoleOutputUtil.padSpaces("-----------", 40));
		System.out.print(ConsoleOutputUtil.padSpaces("------", 12));
		System.out.print(ConsoleOutputUtil.padSpaces("--------", 15));
		System.out.print(ConsoleOutputUtil.padSpaces("----------", 14));
		System.out.print(ConsoleOutputUtil.padSpaces("------", 15));
		System.out.print(ConsoleOutputUtil.padSpaces("----------", 14));
		System.out.print(ConsoleOutputUtil.padSpaces("----------", 15));
		System.out.print(ConsoleOutputUtil.padSpaces("------", 10));
		System.out.print(ConsoleOutputUtil.padSpaces("-------------", 15));
		System.out.print(ConsoleOutputUtil.padSpaces("-------------", 15));
		System.out.println();
	}

	@Override
	public int compare(Loan o1, Loan o2) {
		return o1.getTrade().getTradeDate().compareTo(o2.getTrade().getTradeDate());
	}
}
