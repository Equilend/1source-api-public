package com.os.console;

import java.io.BufferedReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.reactive.function.client.WebClient;

import com.os.client.model.Party;
import com.os.client.model.PartyRole;
import com.os.client.model.PartySettlementInstruction;
import com.os.client.model.SettlementInstruction;
import com.os.client.model.SettlementStatus;
import com.os.console.api.ApplicationConfig;
import com.os.console.api.tasks.AuthTask;
import com.os.console.api.tasks.SearchPartyTask;

public class LoginConsole {

	private static final Logger logger = LoggerFactory.getLogger(LoginConsole.class);

	private WebClient restWebClient;
	
	public LoginConsole(WebClient restWebClient) {
		this.restWebClient = restWebClient;
	}
	
	public void login(ApplicationConfig authConfig, BufferedReader consoleIn) {

		try {

			if (authConfig.getAuth_username() == null) {
				System.out.print("Username: ");
				String username;

				int retries = 0;
				while ((username = consoleIn.readLine()) != null) {
					username = username.trim();
					if (username.length() == 0) {
						if (retries == 3) {
							System.exit(-1);
							break;
						}
						System.out.print("Username: ");
						retries++;
						continue;
					}
					break;
				}

				System.out.print("Password: ");
				String password = consoleIn.readLine();
				if (password == null || password.length() == 0) {
					System.exit(-1);
				}

				System.out.print("Party: ");
				String party = consoleIn.readLine();
				if (party == null || party.length() == 0) {
					System.exit(-1);
				}

				authConfig.setAuth_username(username);
				authConfig.setAuth_password(password);
				authConfig.setAuth_party(party);
			} else {
				System.out.println("Using properties for authentication");
			}

			System.out.print("Authenticating..." + authConfig.getAuth_uri());

			AuthTask authTask = new AuthTask(authConfig);
			Thread taskT = new Thread(authTask);
			taskT.run();
			try {
				taskT.join();
				if (ApplicationConfig.TOKEN != null) {
					System.out.println("...success");
				} else {
					System.out.println("...invalid username and/or password");
					return;
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			System.out.println("Connecting..." + authConfig.getApi_uri());

			System.out.print("Verify acting as...");

			PartyRole actingAs = PartyRole.fromValue(authConfig.getAuth_actAs());
			if (actingAs != null) {
				ApplicationConfig.ACTING_AS = actingAs;
				System.out.println(ApplicationConfig.ACTING_AS);
			} else {
				System.out.println("act as not set properly");
				return;
			}

			if (PartyRole.VENUE.equals(ApplicationConfig.ACTING_AS)) {
				Party venueParty = new Party();
				venueParty.setPartyId(authConfig.getAuth_party());
				venueParty.setPartyName("Venue " + authConfig.getAuth_party());
				venueParty.setGleifLei("213800BN4DRR1ADYGP92");
				ApplicationConfig.ACTING_PARTY = venueParty;
				System.out.println("TODO - Authorize venue party");
			} else {
				System.out.print("Authorizing " + authConfig.getAuth_party() + "...");

				SearchPartyTask searchPartyTask = new SearchPartyTask(restWebClient, authConfig.getAuth_party());
				taskT = new Thread(searchPartyTask);
				taskT.run();
				try {
					taskT.join();
					if (searchPartyTask.getParty() != null) {
						ApplicationConfig.ACTING_PARTY = searchPartyTask.getParty();
					} else {
						System.out.println("acting party not recognized");
						return;
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			PartySettlementInstruction partySettlementInstruction = new PartySettlementInstruction();
			partySettlementInstruction.setPartyRole(ApplicationConfig.ACTING_AS);
			partySettlementInstruction.setSettlementStatus(SettlementStatus.NONE);
			partySettlementInstruction.setInternalAccountCode(authConfig.getSettlement_internalAcctCd());

			SettlementInstruction instruction = new SettlementInstruction();
			partySettlementInstruction.setInstruction(instruction);
			instruction.setSettlementBic(authConfig.getSettlement_settlementBic());
			instruction.setLocalAgentBic(authConfig.getSettlement_localAgentBic());
			instruction.setLocalAgentName(authConfig.getSettlement_localAgentName());
			instruction.setLocalAgentAccount(authConfig.getSettlement_localAgentAcct());
			instruction.setCustodianBic(authConfig.getSettlement_custodianBic());
			instruction.setCustodianName(authConfig.getSettlement_custodianName());
			instruction.setCustodianAccount(authConfig.getSettlement_custodianAcct());
			instruction.setDtcParticipantNumber(authConfig.getSettlement_dtcParticipantNumber());
			
			ApplicationConfig.SETTLEMENT_INSTRUCTIONS = partySettlementInstruction;

			PartySettlementInstruction counterpartySettlementInstruction = new PartySettlementInstruction();
			counterpartySettlementInstruction.setPartyRole(PartyRole.BORROWER.equals(partySettlementInstruction.getPartyRole()) ? PartyRole.LENDER : PartyRole.BORROWER);
			counterpartySettlementInstruction.setSettlementStatus(SettlementStatus.NONE);
			counterpartySettlementInstruction.setInternalAccountCode(authConfig.getSettlement_internalAcctCd());

			SettlementInstruction counterpartyInstruction = new SettlementInstruction();
			counterpartySettlementInstruction.setInstruction(counterpartyInstruction);
			
			if (authConfig.getCounterparty_settlement_settlementBic() != null && authConfig.getCounterparty_settlement_settlementBic().trim().length() > 0) {
				counterpartyInstruction.setSettlementBic(authConfig.getCounterparty_settlement_settlementBic());
			}
			
			if (authConfig.getCounterparty_settlement_localAgentBic() != null && authConfig.getCounterparty_settlement_localAgentBic().trim().length() > 0) {
				counterpartyInstruction.setLocalAgentBic(authConfig.getCounterparty_settlement_localAgentBic());
			}
			
			if (authConfig.getCounterparty_settlement_localAgentName() != null && authConfig.getCounterparty_settlement_localAgentName().trim().length() > 0) {
				counterpartyInstruction.setLocalAgentName(authConfig.getCounterparty_settlement_localAgentName());
			}
			
			if (authConfig.getCounterparty_settlement_localAgentAcct() != null && authConfig.getCounterparty_settlement_localAgentAcct().trim().length() > 0) {
				counterpartyInstruction.setLocalAgentAccount(authConfig.getCounterparty_settlement_localAgentAcct());
			}
			
			if (authConfig.getCounterparty_settlement_custodianBic() != null && authConfig.getCounterparty_settlement_custodianBic().trim().length() > 0) {
				counterpartyInstruction.setCustodianBic(authConfig.getCounterparty_settlement_custodianBic());
			}
			
			if (authConfig.getCounterparty_settlement_custodianName() != null && authConfig.getCounterparty_settlement_custodianName().trim().length() > 0) {
				counterpartyInstruction.setCustodianName(authConfig.getCounterparty_settlement_custodianName());
			}
			
			if (authConfig.getCounterparty_settlement_custodianAcct() != null && authConfig.getCounterparty_settlement_custodianAcct().trim().length() > 0) {
				counterpartyInstruction.setCustodianAccount(authConfig.getCounterparty_settlement_custodianAcct());
			}
			
			if (authConfig.getCounterparty_settlement_dtcParticipantNumber() != null && authConfig.getCounterparty_settlement_dtcParticipantNumber().trim().length() > 0) {
				counterpartyInstruction.setDtcParticipantNumber(authConfig.getCounterparty_settlement_dtcParticipantNumber());
			}
			
			ApplicationConfig.COUNTERPARTY_SETTLEMENT_INSTRUCTIONS = counterpartySettlementInstruction;

		} catch (Exception e) {
			System.out.println("Error during login");
			logger.error("Exception during login: " + e.getMessage());
		} finally {
		}

	}

}
