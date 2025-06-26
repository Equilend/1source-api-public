package com.os.console.api.search.model.fields;

public class LoanContractCollateralCurrency extends Field {

	String collateralCurrency;

	public String getCollateralCurrency() {
		return collateralCurrency;
	}

	public void setCollateralCurrency(String collateralCurrency) {
		this.collateralCurrency = collateralCurrency;
	}

	public String getFieldValue() {
		return collateralCurrency;
	}

	public String getFieldName() {
		return "loanContract.payload.tradeAgreement.loanAgreement.collateral.collateralCurrency.unpack.keyword";
	}
}
