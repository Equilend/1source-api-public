package com.os.console.api.search.model.fields;

public class LoanProposalVenueRefKey extends Field {

	String venueRefKey;

	public String getVenueRefKey() {
		return venueRefKey;
	}

	public void setVenueRefKey(String venueRefKey) {
		this.venueRefKey = venueRefKey;
	}

	public String getFieldName() {
		return "loanProposal.payload.source.loanAgreement.executionVenues.venueRefKey.keyword";
	}

	@Override
	public String getFieldValue() {
		return venueRefKey;
	}
}
