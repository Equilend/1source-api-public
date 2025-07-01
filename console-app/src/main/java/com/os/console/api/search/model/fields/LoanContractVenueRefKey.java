package com.os.console.api.search.model.fields;

public class LoanContractVenueRefKey extends Field {

	String venueRefKey;

	public String getVenueRefKey() {
		return venueRefKey;
	}

	public void setVenueRefKey(String venueRefKey) {
		this.venueRefKey = venueRefKey;
	}

	public String getFieldName() {
		return "loanContract.payload.tradeAgreement.loanAgreement.executionVenues.venueRefKey.keyword";
	}

	@Override
	public String getFieldValue() {
		return venueRefKey;
	}
}
