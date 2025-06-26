package com.os.console.api.search.model.fields;

public class LoanContractBorrower extends Field {

	String borrower;

	public String getBorrower() {
		return borrower;
	}

	public void setBorrower(String borrower) {
		this.borrower = borrower;
	}

	public String getFieldValue() {
		return borrower;
	}
	
	public String getFieldName() {
		return "loanContract.payload.borrower.keyword";
	}
}
