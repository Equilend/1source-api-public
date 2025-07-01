package com.os.console.api.search.model.fields;

public class LoanPendingVenueRefKey extends Field {

	String venueRefKey;

	public String getVenueRefKey() {
		return venueRefKey;
	}

	public void setVenueRefKey(String venueRefKey) {
		this.venueRefKey = venueRefKey;
	}

	public String getFieldName() {
		return "loanPending.payload.tradeAgreement.loanAgreement.executionVenues.venueRefKey.keyword";
	}

	@Override
	public String getFieldValue() {
		return venueRefKey;
	}
}
