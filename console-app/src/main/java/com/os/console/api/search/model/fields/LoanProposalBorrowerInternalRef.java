package com.os.console.api.search.model.fields;

public class LoanProposalBorrowerInternalRef extends Field {

	String internalRef;

	public String getInternalRef() {
		return internalRef;
	}

	public void setInternalRef(String internalRef) {
		this.internalRef = internalRef;
	}

	public String getFieldName() {
		return "loanProposal.payload.source.borrowerTP.internalRef.unpack.keyword";
	}

	@Override
	public String getFieldValue() {
		return internalRef;
	}
}
