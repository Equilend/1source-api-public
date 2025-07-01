package com.os.console.api.search.model;

import java.util.ArrayList;

public class IndexSource {

	ArrayList<String> signatories = new ArrayList<>();
	ArrayList<String> observers = new ArrayList<>();
	LoanProposal loanProposal;
	LoanPending loanPending;
	LoanContract loanContract;
	ArrayList<ReratePending> reratePendings = new ArrayList<>();
	ArrayList<RerateProposal> rerateProposals = new ArrayList<>();
	ArrayList<Return> returns = new ArrayList<>();
	ArrayList<Buyin> buyins = new ArrayList<>();
	ArrayList<Recall> recalls = new ArrayList<>();
	ArrayList<Split> splits = new ArrayList<>();

	public ArrayList<String> getSignatories() {
		return signatories;
	}

	public void setSignatories(ArrayList<String> signatories) {
		this.signatories = signatories;
	}

	public ArrayList<String> getObservers() {
		return observers;
	}

	public void setObservers(ArrayList<String> observers) {
		this.observers = observers;
	}

	public LoanProposal getLoanProposal() {
		return loanProposal;
	}

	public void setLoanProposal(LoanProposal loanProposal) {
		this.loanProposal = loanProposal;
	}

	public LoanPending getLoanPending() {
		return loanPending;
	}

	public void setLoanPending(LoanPending loanPending) {
		this.loanPending = loanPending;
	}

	public LoanContract getLoanContract() {
		return loanContract;
	}

	public void setLoanContract(LoanContract loanContract) {
		this.loanContract = loanContract;
	}

	public ArrayList<ReratePending> getReratePendings() {
		return reratePendings;
	}

	public void setReratePendings(ArrayList<ReratePending> reratePendings) {
		this.reratePendings = reratePendings;
	}

	public ArrayList<RerateProposal> getRerateProposals() {
		return rerateProposals;
	}

	public void setRerateProposals(ArrayList<RerateProposal> rerateProposals) {
		this.rerateProposals = rerateProposals;
	}

	public ArrayList<Return> getReturns() {
		return returns;
	}

	public void setReturns(ArrayList<Return> returns) {
		this.returns = returns;
	}

	public ArrayList<Buyin> getBuyins() {
		return buyins;
	}

	public void setBuyins(ArrayList<Buyin> buyins) {
		this.buyins = buyins;
	}

	public ArrayList<Recall> getRecalls() {
		return recalls;
	}

	public void setRecalls(ArrayList<Recall> recalls) {
		this.recalls = recalls;
	}

	public ArrayList<Split> getSplits() {
		return splits;
	}

	public void setSplits(ArrayList<Split> splits) {
		this.splits = splits;
	}

}