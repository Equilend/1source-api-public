package com.os.console.api.search.model.fields;

public class LoanContractLenderInternalRef extends Field {

	String internalRef;

	public String getInternalRef() {
		return internalRef;
	}

	public void setInternalRef(String internalRef) {
		this.internalRef = internalRef;
	}

	public String getFieldName() {
		return "loanContract.payload.tradeAgreement.lenderTP.internalRef.unpack.keyword";
	}

	@Override
	public String getFieldValue() {
		return internalRef;
	}
}
