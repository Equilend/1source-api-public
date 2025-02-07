package com.os.console.util;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import com.os.client.model.AcknowledgementType;
import com.os.client.model.AnyOfLoanDeclineErrorResponseErrorsItems;
import com.os.client.model.BenchmarkCd;
import com.os.client.model.Collateral;
import com.os.client.model.CollateralType;
import com.os.client.model.CurrencyCd;
import com.os.client.model.DelegationAuthorization;
import com.os.client.model.DelegationAuthorizationType;
import com.os.client.model.DelegationProposal;
import com.os.client.model.FeeRate;
import com.os.client.model.FixedRate;
import com.os.client.model.FixedRateDef;
import com.os.client.model.FloatingRate;
import com.os.client.model.FloatingRateDef;
import com.os.client.model.Instrument;
import com.os.client.model.Loan;
import com.os.client.model.LoanCancelErrorReason;
import com.os.client.model.LoanCancelErrorResponse;
import com.os.client.model.LoanDeclineErrorReason;
import com.os.client.model.LoanDeclineErrorReasonFieldCollateralCurrency;
import com.os.client.model.LoanDeclineErrorReasonFieldQuantity;
import com.os.client.model.LoanDeclineErrorResponse;
import com.os.client.model.LoanProposal;
import com.os.client.model.LoanProposalApproval;
import com.os.client.model.LoanSplit;
import com.os.client.model.LoanSplitAcknowledgment;
import com.os.client.model.LoanSplitLot;
import com.os.client.model.LoanSplitLotAcknowledge;
import com.os.client.model.LoanSplitProposal;
import com.os.client.model.LoanSplitProposalLot;
import com.os.client.model.Party;
import com.os.client.model.PartyRole;
import com.os.client.model.PartySettlementInstruction;
import com.os.client.model.RebateRate;
import com.os.client.model.RecallAcknowledgement;
import com.os.client.model.RecallProposal;
import com.os.client.model.RerateProposal;
import com.os.client.model.ReturnAcknowledgement;
import com.os.client.model.ReturnProposal;
import com.os.client.model.RoundingMode;
import com.os.client.model.SettlementInstructionUpdate;
import com.os.client.model.SettlementType;
import com.os.client.model.TermType;
import com.os.client.model.TradeAgreement;
import com.os.client.model.TransactingParties;
import com.os.client.model.TransactingParty;
import com.os.client.model.Venue;
import com.os.client.model.VenueType;
import com.os.client.model.Venues;
import com.os.console.api.ConsoleConfig;

public class PayloadUtil {

	public static LoanProposal createLoanProposal(Party borrowerParty, Party lenderParty,
			PartyRole proposingPartyRole, Instrument instrument) {

		Random random = new Random();

		InstrumentUtil instrumentUtil = InstrumentUtil.getInstance();
		
		LoanProposal loanProposal = new LoanProposal();

		TradeAgreement trade = new TradeAgreement();

		TransactingParties transactingParties = new TransactingParties();

		TransactingParty borrowerTransactingParty = new TransactingParty();
		borrowerTransactingParty.setPartyRole(PartyRole.BORROWER);
		borrowerTransactingParty.setParty(borrowerParty);
		transactingParties.add(borrowerTransactingParty);

		borrowerTransactingParty.setInternalReference(PartyRole.BORROWER.equals(proposingPartyRole) ? UUID.randomUUID().toString() : null);

		TransactingParty lenderTransactingParty = new TransactingParty();
		lenderTransactingParty.setPartyRole(PartyRole.LENDER);
		lenderTransactingParty.setParty(lenderParty);
		transactingParties.add(lenderTransactingParty);

		lenderTransactingParty.setInternalReference(PartyRole.LENDER.equals(proposingPartyRole) ? UUID.randomUUID().toString() : null);

		trade.setTransactingParties(transactingParties);

		Venues venues = new Venues();

		Venue venue = new Venue();
		venue.setType(VenueType.OFFPLATFORM);
		venue.setVenueRefKey("CONSOLE" + System.currentTimeMillis());

		venues.add(venue);

		trade.setVenues(venues);

		trade.setInstrument(instrument);

		LocalDate tradeDate = LocalDate.now(ZoneId.of("UTC"));

		CollateralType collateralType = CollateralType.CASH;

		FloatingRateDef floatingRateDef = new FloatingRateDef();
		floatingRateDef.setSpread(Double.valueOf(random.nextInt((100 - 1) + 100)));
		floatingRateDef.setRerateCutoffTime("18:00");
		floatingRateDef.setEffectiveDate(tradeDate);
		floatingRateDef.setBenchmark(BenchmarkCd.OBFR);
		floatingRateDef.setIsAutoRerate(true);

		FloatingRate floatingRate = new FloatingRate();
		floatingRate.setFloating(floatingRateDef);

		RebateRate rebateRate = new RebateRate();
		rebateRate.setRebate(floatingRate);

		trade.setRate(rebateRate);

		trade.setQuantity(((((random.nextInt(100000 - 10000) + 10000)) + 99) / 100) * 100);
		trade.setBillingCurrency(instrumentUtil.getInstrumentCurrency(instrument.getMarketCode()));
		trade.setDividendRatePct(85d);
		trade.setTradeDate(tradeDate);
		trade.setTermType(TermType.OPEN);
		trade.setTermDate(null);

		trade.setSettlementDate(tradeDate.plusDays(1));
		trade.setSettlementType(SettlementType.DVP);

		Collateral collateral = new Collateral();
		collateral.setContractPrice(trade.getInstrument().getPrice().getValue().doubleValue());

		BigDecimal contractValue = BigDecimal.valueOf(
				trade.getQuantity().doubleValue() * (trade.getInstrument().getPrice().getValue().doubleValue()));
		contractValue = contractValue.setScale(2, java.math.RoundingMode.HALF_UP);
		collateral.setContractValue(contractValue.doubleValue());

		BigDecimal collateralValue = BigDecimal
				.valueOf(trade.getQuantity().doubleValue() * collateral.getContractPrice().doubleValue() * 1.02);
		collateralValue = collateralValue.setScale(2, java.math.RoundingMode.HALF_UP);
		collateral.setCollateralValue(collateralValue.doubleValue());

		collateral.setCollateralCurrency(instrumentUtil.getInstrumentCurrency(instrument.getMarketCode()));
		collateral.setCollateralType(collateralType);
		collateral.setCollateralMargin(102d);

		// only add rounding rules if proposer is the lender
		if (PartyRole.LENDER.equals(proposingPartyRole)) {
			collateral.setRoundingRule(10);
			collateral.setRoundingMode(RoundingMode.ALWAYSUP);
		}

		trade.setCollateral(collateral);

		loanProposal.setTrade(trade);

		loanProposal.setSettlement(Collections.singletonList(ConsoleConfig.SETTLEMENT_INSTRUCTIONS));

		return loanProposal;
	}

	public static LoanProposalApproval createLoanProposalApproval() {

		LoanProposalApproval proposalApproval = new LoanProposalApproval();

		proposalApproval.setInternalReference(UUID.randomUUID().toString());

		if (PartyRole.LENDER.equals(ConsoleConfig.ACTING_AS)) {
			proposalApproval.setRoundingRule(10);
			proposalApproval.setRoundingMode(RoundingMode.ALWAYSUP);
		}

		proposalApproval.setSettlement(ConsoleConfig.SETTLEMENT_INSTRUCTIONS);

		return proposalApproval;
	}

	public static ReturnProposal createReturnProposal(Loan loan, Integer quantity) {

		ReturnProposal proposal = new ReturnProposal();

		Venue venue = new Venue();
		venue.setType(VenueType.OFFPLATFORM);
		venue.setVenueRefKey("CONSOLE" + System.currentTimeMillis());

		proposal.setExecutionVenue(venue);
		
		proposal.setQuantity(quantity);
		proposal.setReturnDate(LocalDate.now(ZoneId.of("UTC")));
		
		LocalDate returnSettlementDate = proposal.getReturnDate().plusDays(1);
		if (returnSettlementDate.getDayOfWeek().equals(DayOfWeek.SATURDAY)) {
			returnSettlementDate = returnSettlementDate.plusDays(2);
		} else if (returnSettlementDate.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
			returnSettlementDate = returnSettlementDate.plusDays(1);
		}
		proposal.setReturnSettlementDate(returnSettlementDate);
		proposal.setSettlementType(SettlementType.DVP);

		BigDecimal collateralValue = BigDecimal
				.valueOf(quantity.doubleValue() * loan.getTrade().getCollateral().getContractPrice().doubleValue() * 1.02);
		collateralValue = collateralValue.setScale(2, java.math.RoundingMode.HALF_UP);
		proposal.setCollateralValue(collateralValue.doubleValue());

//		PartySettlementInstruction partySettlementInstruction = new PartySettlementInstruction();
//		partySettlementInstruction.setPartyRole(ConsoleConfig.ACTING_AS);
//		partySettlementInstruction.setSettlementStatus(SettlementStatus.NONE);
//		partySettlementInstruction.setInternalAcctCd(consoleConfig.getSettlement_internalAcctCd());
//
//		SettlementInstruction instruction = new SettlementInstruction();
//		partySettlementInstruction.setInstruction(instruction);
//		instruction.setSettlementBic(consoleConfig.getSettlement_settlementBic());
//		instruction.setLocalAgentBic(consoleConfig.getSettlement_localAgentBic());
//		instruction.setLocalAgentName(consoleConfig.getSettlement_localAgentName());
//		instruction.setLocalAgentAcct(consoleConfig.getSettlement_localAgentAcct());
//		instruction.setCustodianBic(consoleConfig.getSettlement_custodianBic());
//		instruction.setCustodianName(consoleConfig.getSettlement_custodianName());
//		instruction.setCustodianAcct(consoleConfig.getSettlement_custodianAcct());
//		instruction.setDtcParticipantNumber(consoleConfig.getSettlement_dtcParticipantNumber());
//
//		proposal.setSettlement(partySettlementInstruction);

		return proposal;
	}

	public static RecallProposal createRecallProposal(Integer quantity) {

		RecallProposal proposal = new RecallProposal();

		Venue venue = new Venue();
		venue.setType(VenueType.OFFPLATFORM);
		venue.setVenueRefKey("CONSOLE" + System.currentTimeMillis());

		proposal.setExecutionVenue(venue);

		proposal.setQuantity(quantity);
		proposal.setRecallDate(LocalDate.now(ZoneId.of("UTC")));
		
		LocalDate recallDueDate = proposal.getRecallDate().plusDays(3);
		if (recallDueDate.getDayOfWeek().equals(DayOfWeek.SATURDAY)) {
			recallDueDate = recallDueDate.plusDays(2);
		} else if (recallDueDate.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
			recallDueDate = recallDueDate.plusDays(1);
		}
		proposal.setRecallDueDate(recallDueDate);

//		PartySettlementInstruction partySettlementInstruction = new PartySettlementInstruction();
//		partySettlementInstruction.setPartyRole(ConsoleConfig.ACTING_AS);
//		partySettlementInstruction.setSettlementStatus(SettlementStatus.NONE);
//		partySettlementInstruction.setInternalAcctCd(consoleConfig.getSettlement_internalAcctCd());
//
//		SettlementInstruction instruction = new SettlementInstruction();
//		partySettlementInstruction.setInstruction(instruction);
//		instruction.setSettlementBic(consoleConfig.getSettlement_settlementBic());
//		instruction.setLocalAgentBic(consoleConfig.getSettlement_localAgentBic());
//		instruction.setLocalAgentName(consoleConfig.getSettlement_localAgentName());
//		instruction.setLocalAgentAcct(consoleConfig.getSettlement_localAgentAcct());
//		instruction.setCustodianBic(consoleConfig.getSettlement_custodianBic());
//		instruction.setCustodianName(consoleConfig.getSettlement_custodianName());
//		instruction.setCustodianAcct(consoleConfig.getSettlement_custodianAcct());
//		instruction.setDtcParticipantNumber(consoleConfig.getSettlement_dtcParticipantNumber());
//
//		proposal.setSettlement(partySettlementInstruction);

		return proposal;
	}

	public static RerateProposal createRerateProposal(Loan loan, Double rerate) {

		RerateProposal proposal = new RerateProposal();

		Venue venue = new Venue();
		venue.setType(VenueType.OFFPLATFORM);
		venue.setVenueRefKey("CONSOLE" + System.currentTimeMillis());

		proposal.setExecutionVenue(venue);

		LocalDate rerateDate = LocalDate.now(ZoneId.of("UTC"));
		
		if (loan.getTrade().getRate() instanceof FeeRate) {
			
			FeeRate feeRate = new FeeRate();
			
			FixedRateDef fixedRateDef = new FixedRateDef();
			fixedRateDef.setBaseRate(rerate);
			fixedRateDef.setEffectiveDate(rerateDate);
			feeRate.setFee(fixedRateDef);
			
			proposal.setRate(feeRate);

		} else if (loan.getTrade().getRate() instanceof RebateRate) {

			RebateRate rebateRate = new RebateRate();

			if (((RebateRate) loan.getTrade().getRate()).getRebate() instanceof FixedRate) {
			
				FixedRate fixedRate = new FixedRate();
				FixedRateDef fixedRateDef = new FixedRateDef();
				fixedRateDef.setBaseRate(rerate);
				fixedRateDef.setEffectiveDate(rerateDate);
				fixedRate.setFixed(fixedRateDef);
				
				rebateRate.setRebate(fixedRate);
				
			} else if (((RebateRate) loan.getTrade().getRate()).getRebate() instanceof FloatingRate) {
				
				FloatingRateDef origRate = ((FloatingRate)((RebateRate) loan.getTrade().getRate()).getRebate()).getFloating();
				
				FloatingRate floatingRate = new FloatingRate();
				FloatingRateDef floatingRateDef = new FloatingRateDef();
				floatingRateDef.setBenchmark(origRate.getBenchmark());
				floatingRateDef.setIsAutoRerate(origRate.isIsAutoRerate());
				if (!floatingRateDef.isIsAutoRerate()) {
					floatingRateDef.setBaseRate(origRate.getBaseRate());
				}
				floatingRateDef.setSpread(rerate);
				floatingRateDef.setEffectiveDate(rerateDate);
				floatingRate.setFloating(floatingRateDef);
				
				rebateRate.setRebate(floatingRate);
			}

			proposal.setRate(rebateRate);
		}

		return proposal;
	}

	public static DelegationProposal createDelegationProposal(Party counterParty, Party venueParty,
			DelegationAuthorizationType authorizationType) {

		DelegationProposal delegationProposal = new DelegationProposal();

		DelegationAuthorization authorization = new DelegationAuthorization();
		authorization.setAuthorizationType(authorizationType);

		delegationProposal.setAuthorization(authorization);
		delegationProposal.setDelegationParty(venueParty);
		delegationProposal.setCounterparty(counterParty);

		return delegationProposal;
	}

	public static ReturnAcknowledgement createReturnAcknowledgement(AcknowledgementType acknowledgementType, String message) {
		
		ReturnAcknowledgement returnAcknowledgement = new ReturnAcknowledgement();
		
		returnAcknowledgement.setAcknowledgementType(acknowledgementType);
		returnAcknowledgement.setDescription(message);
		
		return returnAcknowledgement;
	}

	public static RecallAcknowledgement createRecallAcknowledgement(AcknowledgementType acknowledgementType, String message) {
		
		RecallAcknowledgement recallAcknowledgement = new RecallAcknowledgement();
		
		recallAcknowledgement.setAcknowledgementType(acknowledgementType);
		recallAcknowledgement.setDescription(message);
		
		return recallAcknowledgement;
	}

	public static LoanSplitProposal createLoanSplitProposal(List<Integer> quantitySplits) {
		
		LoanSplitProposal loanSplitProposal = new LoanSplitProposal();
		
		for (Integer quantity : quantitySplits) {
			LoanSplitProposalLot lot = new LoanSplitProposalLot();
			lot.setQuantity(quantity);
			lot.setInternalReference(UUID.randomUUID().toString());
			
			loanSplitProposal.add(lot);
		}
		
		return loanSplitProposal;
	}

	public static LoanSplitAcknowledgment createLoanSplitProposalApproval(LoanSplit loanSplit) {
		
		LoanSplitAcknowledgment loanSplitProposalApproval = new LoanSplitAcknowledgment();
		
		for (LoanSplitLot lot : loanSplit.getSplitLots()) {
			LoanSplitLotAcknowledge lotApproval = new LoanSplitLotAcknowledge();
			lotApproval.setLoanId(lot.getLoanId());
			lotApproval.setInternalReference(UUID.randomUUID().toString());
			
			loanSplitProposalApproval.add(lotApproval);
		}
		
		return loanSplitProposalApproval;
	}
	
	public static SettlementInstructionUpdate createSettlementInstructionUpdate(Loan loan, String dtcParticipantNumber) {

		SettlementInstructionUpdate settlementInstructionUpdate = null;
		
		TransactingParties parties = loan.getTrade().getTransactingParties();
		boolean canAct = false;
		PartyRole partyRole = null;
		for (TransactingParty transactingParty : parties) {
			if (ConsoleConfig.ACTING_PARTY.equals(transactingParty.getParty())) {
				canAct = true;
				partyRole = transactingParty.getPartyRole();
				break;
			}
		}

		if (canAct && partyRole != null) {
			settlementInstructionUpdate = new SettlementInstructionUpdate();
			List<PartySettlementInstruction> instructions = loan.getSettlement();
			for (PartySettlementInstruction instruction : instructions) {
				if (partyRole.equals(instruction.getPartyRole())) {
					settlementInstructionUpdate.setSettlement(instruction);
					instruction.getInstruction().setDtcParticipantNumber(dtcParticipantNumber);
					break;
				}
			}
		}
		
		return settlementInstructionUpdate;
	}

	public static LoanCancelErrorResponse createLoanCancelErrorResponse() {
		
		LoanCancelErrorResponse response = new LoanCancelErrorResponse();
		
		response.setReason(LoanCancelErrorReason.NO_RESPONSE);

		return response;
	}
	
	public static LoanDeclineErrorResponse createLoanDeclineErrorResponse() {
		
		LoanDeclineErrorResponse response = new LoanDeclineErrorResponse();
		
		response.setReason(LoanDeclineErrorReason.INCORRECT_LOAN_INFO);
		
		List<AnyOfLoanDeclineErrorResponseErrorsItems> errors = new ArrayList<>();
		
		LoanDeclineErrorReasonFieldQuantity quantityError = new LoanDeclineErrorReasonFieldQuantity();
		quantityError.setField(LoanDeclineErrorReasonFieldQuantity.FieldEnum.QUANTITY);
		quantityError.setExpectedValue(111111);;
		errors.add(quantityError);

		LoanDeclineErrorReasonFieldCollateralCurrency collateralCurrencyError = new LoanDeclineErrorReasonFieldCollateralCurrency();
		collateralCurrencyError.setField(LoanDeclineErrorReasonFieldCollateralCurrency.FieldEnum.COLLATERAL_CURRENCY);
		collateralCurrencyError.setExpectedValue(CurrencyCd.CAD);
		errors.add(collateralCurrencyError);

		response.setErrors(errors);
		
		return response;
	}
}
