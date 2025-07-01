package com.os.console;

import java.io.BufferedReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;

import com.os.client.model.Instrument;
import com.os.client.model.LoanProposal;
import com.os.client.model.Party;
import com.os.client.model.PartyRole;
import com.os.console.api.ApplicationConfig;
import com.os.console.api.tasks.ProposeLoanTask;
import com.os.console.util.ConsoleOutputUtil;
import com.os.console.util.InstrumentUtil;
import com.os.console.util.PayloadUtil;

public class LoanProposalConsole extends AbstractConsole {

	private static final Logger logger = LoggerFactory.getLogger(LoanProposalConsole.class);

	private LoanProposal loanProposal;

	@Autowired
	WebClient restWebClient;

	private Party borrowerParty;
	private Party lenderParty;
	private PartyRole proposingPartyRole;

	private boolean firstPrompt = true;

	public LoanProposalConsole(Party borrowerParty, Party lenderParty, PartyRole proposingPartyRole) {

		this.borrowerParty = borrowerParty;
		this.lenderParty = lenderParty;
		this.proposingPartyRole = proposingPartyRole;
	}

	protected boolean prompt() {

		if (firstPrompt) {
			Instrument instrument = InstrumentUtil.getInstance().getRandomInstrument();
			Instrument minInstrument = minimizeInstrument(instrument, instrument.getFigi());
			loanProposal = PayloadUtil.createLoanProposal(borrowerParty, lenderParty, proposingPartyRole,
					instrument, minInstrument);
			ConsoleOutputUtil.printObject(loanProposal);
			firstPrompt = false;
		}
		System.out.print(ApplicationConfig.ACTING_PARTY.getPartyId() + " /loans/ proposal > ");
		return true;
	}

	public void handleArgs(String args[], BufferedReader consoleIn) {

		if (args[0].equals("R")) {

			Instrument instrument = InstrumentUtil.getInstance().getRandomInstrument();
			Instrument minInstrument = minimizeInstrument(instrument, instrument.getFigi());

			loanProposal = PayloadUtil.createLoanProposal(borrowerParty, lenderParty, proposingPartyRole,
					instrument, minInstrument);

			ConsoleOutputUtil.printObject(loanProposal);
		} else if (args[0].equals("B")) {

			Instrument instrument = InstrumentUtil.getInstance().getRandomInstrument();
			Instrument minInstrument = minimizeInstrument(instrument, instrument.getFigi());

			loanProposal = PayloadUtil.createLoanProposal(borrowerParty, lenderParty, proposingPartyRole,
					instrument, minInstrument);

			loanProposal.getSettlement().add(ApplicationConfig.COUNTERPARTY_SETTLEMENT_INSTRUCTIONS);

			ConsoleOutputUtil.printObject(loanProposal);

		} else if (args[0].equals("G")) {
			if (args.length != 2 || args[1].length() == 0 || args[1].length() > 50) {
				System.out.println("Invalid security ID");
			} else {
				String securityId = args[1];
				System.out.print("Looking up " + securityId + "...");
				Instrument instrument = InstrumentUtil.getInstance().getInstrument(securityId);

				if (instrument == null) {
					System.out.println("instrument not configured");
				} else {
					loanProposal = PayloadUtil.createLoanProposal(borrowerParty, lenderParty, proposingPartyRole,
							instrument, minimizeInstrument(instrument, securityId));
					ConsoleOutputUtil.printObject(loanProposal);
				}
			}
		} else if (args[0].equals("J")) {
			if (args.length != 2 || args[1].length() == 0 || args[1].length() > 50) {
				System.out.println("Invalid security ID");
			} else {
				String securityId = args[1];
				System.out.print("Looking up " + securityId + "...");
				Instrument instrument = InstrumentUtil.getInstance().getInstrument(securityId);

				if (instrument == null) {
					System.out.println("instrument not configured");
				} else {
					loanProposal = PayloadUtil.createLoanProposal(borrowerParty, lenderParty, proposingPartyRole,
							instrument, minimizeInstrument(instrument, securityId));
					loanProposal.getSettlement().add(ApplicationConfig.COUNTERPARTY_SETTLEMENT_INSTRUCTIONS);
					ConsoleOutputUtil.printObject(loanProposal);
				}
			}
		} else if (args[0].equals("S")) {

			try {
				System.out.print("Proposing loan...");
				ProposeLoanTask proposeLoanTask = new ProposeLoanTask(restWebClient, loanProposal);
				Thread taskT = new Thread(proposeLoanTask);
				taskT.run();
				try {
					taskT.join();
				} catch (InterruptedException e) {
					logger.error("Propose loan interrupted.", e);
					e.printStackTrace();
				}
			} catch (Exception u) {
				logger.error("Error proposing loan.", u);
				System.out.println("Error proposing loan");
			}

		} else {
			System.out.println("Unknown command");
		}
	}

	private Instrument minimizeInstrument(Instrument i, String securityId) {

		Instrument minInstrument = new Instrument();

		if (i.getFigi() != null && securityId.toUpperCase().equals(i.getFigi().toUpperCase())) {
			minInstrument.setFigi(i.getFigi());
		} else if (i.getIsin() != null && securityId.toUpperCase().equals(i.getIsin().toUpperCase())) {
			minInstrument.setIsin(i.getIsin());
			minInstrument.setMarketCode(i.getMarketCode());
		} else if (i.getCusip() != null && securityId.toUpperCase().equals(i.getCusip().toUpperCase())) {
			minInstrument.setCusip(i.getCusip());
			minInstrument.setMarketCode(i.getMarketCode());
		} else if (i.getSedol() != null && securityId.toUpperCase().equals(i.getSedol().toUpperCase())) {
			minInstrument.setSedol(i.getSedol());
		} else if (i.getTicker() != null && securityId.toUpperCase().equals(i.getTicker().toUpperCase())) {
			minInstrument.setTicker(i.getTicker());
		}

		return minInstrument;
	}

	protected void printMenu() {
		System.out.println("Loan Proposal Menu");
		System.out.println("-----------------------");
		System.out.println("S               - Submit loan proposal");
		System.out.println();
		System.out.println("R               - Create a random loan");
		System.out.println("B               - Create a random loan with both party settlement info");
		System.out.println();
		System.out.println("G <Security Id> - Generate loan with Security Id");
		System.out.println("J <Security Id> - Generate loan with Security Id and both party settlement info");
		System.out.println();
		System.out.println("X               - Go back");
	}

}
