package com.os.console.api.search.model.fields;

public class LoanContractMarkValue extends Field {

	String markValue;

	public String getMarkValue() {
		return markValue;
	}

	public void setMarkValue(String markValue) {
		this.markValue = markValue;
	}

	public String getFieldValue() {
		return markValue;
	}

	public String getFieldName() {
		return "loanContract.payload.mark.value";
	}
}
