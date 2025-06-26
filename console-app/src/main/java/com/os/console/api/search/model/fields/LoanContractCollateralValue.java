package com.os.console.api.search.model.fields;

public class LoanContractCollateralValue extends Field {

	String collateralValue;

	public String getCollateralValue() {
		return collateralValue;
	}

	public void setCollateralValue(String collateralValue) {
		this.collateralValue = collateralValue;
	}

	public String getFieldValue() {
		return collateralValue;
	}

	public String getFieldName() {
		return "loanContract.payload.tradeAgreement.loanAgreement.collateral.collateralValue";
	}
}
