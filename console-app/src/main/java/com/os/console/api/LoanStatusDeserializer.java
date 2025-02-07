package com.os.console.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.os.client.model.AnyOfLoanDeclineErrorResponseErrorsItems;
import com.os.client.model.CollateralType;
import com.os.client.model.CurrencyCd;
import com.os.client.model.LoanCancelErrorReason;
import com.os.client.model.LoanCancelErrorResponse;
import com.os.client.model.LoanCancelPendingErrorReason;
import com.os.client.model.LoanCancelPendingErrorResponse;
import com.os.client.model.LoanDeclineErrorReason;
import com.os.client.model.LoanDeclineErrorReasonFieldBillingCurrency;
import com.os.client.model.LoanDeclineErrorReasonFieldCollateralCurrency;
import com.os.client.model.LoanDeclineErrorReasonFieldCollateralMargin;
import com.os.client.model.LoanDeclineErrorReasonFieldCollateralType;
import com.os.client.model.LoanDeclineErrorReasonFieldDividendRate;
import com.os.client.model.LoanDeclineErrorReasonFieldQuantity;
import com.os.client.model.LoanDeclineErrorReasonFieldRate;
import com.os.client.model.LoanDeclineErrorReasonFieldSettlementDate;
import com.os.client.model.LoanDeclineErrorReasonFieldSettlementType;
import com.os.client.model.LoanDeclineErrorReasonFieldTermDate;
import com.os.client.model.LoanDeclineErrorReasonFieldTermType;
import com.os.client.model.LoanDeclineErrorReasonFieldTradeDate;
import com.os.client.model.LoanDeclineErrorResponse;
import com.os.client.model.OneOfLoanLoanStatusReason;
import com.os.client.model.SettlementType;
import com.os.client.model.TermType;

public class LoanStatusDeserializer extends StdDeserializer<OneOfLoanLoanStatusReason> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3709103313504551007L;

	public LoanStatusDeserializer() {
		this(null);
	}

	protected LoanStatusDeserializer(Class<?> vc) {
		super(vc);
	}

	@Override
	public OneOfLoanLoanStatusReason deserialize(JsonParser p, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {

		OneOfLoanLoanStatusReason impl = null;

		JsonNode nodeLoanStatusReason = p.readValueAsTree();

		JsonNode nodeReason = nodeLoanStatusReason.get("reason");
		if (nodeReason != null) {

			if (LoanDeclineErrorReason.fromValue(nodeReason.textValue()) != null) {

				LoanDeclineErrorResponse loanDeclineErrorResponse = new LoanDeclineErrorResponse();
				loanDeclineErrorResponse.setReason(LoanDeclineErrorReason.fromValue(nodeReason.textValue()));

				JsonNode errors = nodeLoanStatusReason.get("errors");
				if (errors != null && errors.isArray()) {
					
					List<AnyOfLoanDeclineErrorResponseErrorsItems> responseErrors = new ArrayList<>();
					
					for (JsonNode error : errors) {
						JsonNode nodeField = error.get("field");
						if (nodeField != null) {

							String field = nodeField.textValue();
							
							JsonNode nodeValue = error.get("expectedValue");

							if (LoanDeclineErrorReasonFieldBillingCurrency.FieldEnum.fromValue(field) != null) {
								LoanDeclineErrorReasonFieldBillingCurrency loanDeclineErrorReasonFieldBillingCurrency = new LoanDeclineErrorReasonFieldBillingCurrency();
								loanDeclineErrorReasonFieldBillingCurrency.setField(LoanDeclineErrorReasonFieldBillingCurrency.FieldEnum.fromValue(field));
								if (nodeValue != null) {
									loanDeclineErrorReasonFieldBillingCurrency.setExpectedValue(CurrencyCd.fromValue(nodeValue.textValue()));
								}
								responseErrors.add(loanDeclineErrorReasonFieldBillingCurrency);
								
							} else if (LoanDeclineErrorReasonFieldCollateralCurrency.FieldEnum
									.fromValue(field) != null) {

								LoanDeclineErrorReasonFieldCollateralCurrency loanDeclineErrorReasonFieldCollateralCurrency = new LoanDeclineErrorReasonFieldCollateralCurrency();
								loanDeclineErrorReasonFieldCollateralCurrency.setField(LoanDeclineErrorReasonFieldCollateralCurrency.FieldEnum.fromValue(field));
								if (nodeValue != null) {
									loanDeclineErrorReasonFieldCollateralCurrency.setExpectedValue(CurrencyCd.fromValue(nodeValue.textValue()));
								}
								responseErrors.add(loanDeclineErrorReasonFieldCollateralCurrency);

							} else if (LoanDeclineErrorReasonFieldQuantity.FieldEnum.fromValue(field) != null) {

								LoanDeclineErrorReasonFieldQuantity loanDeclineErrorReasonFieldQuantity = new LoanDeclineErrorReasonFieldQuantity();
								loanDeclineErrorReasonFieldQuantity.setField(LoanDeclineErrorReasonFieldQuantity.FieldEnum.fromValue(field));
								if (nodeValue != null) {
									loanDeclineErrorReasonFieldQuantity.setExpectedValue(nodeValue.intValue());
								}
								responseErrors.add(loanDeclineErrorReasonFieldQuantity);

							} else if (LoanDeclineErrorReasonFieldRate.FieldEnum.fromValue(field) != null) {

								LoanDeclineErrorReasonFieldRate loanDeclineErrorReasonFieldRate = new LoanDeclineErrorReasonFieldRate();
								loanDeclineErrorReasonFieldRate.setField(LoanDeclineErrorReasonFieldRate.FieldEnum.fromValue(field));
								if (nodeValue != null) {
									RateDeserializer deserializer = new RateDeserializer();
									loanDeclineErrorReasonFieldRate.setExpectedValue(deserializer.deserialize(nodeValue));
								}
								responseErrors.add(loanDeclineErrorReasonFieldRate);
								
							} else if (LoanDeclineErrorReasonFieldCollateralMargin.FieldEnum.fromValue(field) != null) {

								LoanDeclineErrorReasonFieldCollateralMargin loanDeclineErrorReasonFieldCollateralMargin = new LoanDeclineErrorReasonFieldCollateralMargin();
								loanDeclineErrorReasonFieldCollateralMargin.setField(LoanDeclineErrorReasonFieldCollateralMargin.FieldEnum.fromValue(field));
								if (nodeValue != null) {
									loanDeclineErrorReasonFieldCollateralMargin.setExpectedValue(nodeValue.doubleValue());
								}
								responseErrors.add(loanDeclineErrorReasonFieldCollateralMargin);

							} else if (LoanDeclineErrorReasonFieldCollateralType.FieldEnum.fromValue(field) != null) {

								LoanDeclineErrorReasonFieldCollateralType loanDeclineErrorReasonFieldCollateralType = new LoanDeclineErrorReasonFieldCollateralType();
								loanDeclineErrorReasonFieldCollateralType.setField(LoanDeclineErrorReasonFieldCollateralType.FieldEnum.fromValue(field));
								if (nodeValue != null) {
									loanDeclineErrorReasonFieldCollateralType.setExpectedValue(CollateralType.fromValue(nodeValue.textValue()));
								}
								responseErrors.add(loanDeclineErrorReasonFieldCollateralType);

							} else if (LoanDeclineErrorReasonFieldDividendRate.FieldEnum.fromValue(field) != null) {

								LoanDeclineErrorReasonFieldDividendRate loanDeclineErrorReasonFieldDividendRate = new LoanDeclineErrorReasonFieldDividendRate();
								loanDeclineErrorReasonFieldDividendRate.setField(LoanDeclineErrorReasonFieldDividendRate.FieldEnum.fromValue(field));
								if (nodeValue != null) {
									loanDeclineErrorReasonFieldDividendRate.setExpectedValue(nodeValue.doubleValue());
								}
								responseErrors.add(loanDeclineErrorReasonFieldDividendRate);

							} else if (LoanDeclineErrorReasonFieldSettlementDate.FieldEnum.fromValue(field) != null) {

								LoanDeclineErrorReasonFieldSettlementDate loanDeclineErrorReasonFieldSettlementDate = new LoanDeclineErrorReasonFieldSettlementDate();
								loanDeclineErrorReasonFieldSettlementDate.setField(LoanDeclineErrorReasonFieldSettlementDate.FieldEnum.fromValue(field));
								if (nodeValue != null) {
									LocalDateJacksonDeserializer dateDeserializer = new LocalDateJacksonDeserializer();
									loanDeclineErrorReasonFieldSettlementDate.setExpectedValue(dateDeserializer.deserialize(nodeValue.textValue()));
								}
								responseErrors.add(loanDeclineErrorReasonFieldSettlementDate);

							} else if (LoanDeclineErrorReasonFieldSettlementType.FieldEnum.fromValue(field) != null) {

								LoanDeclineErrorReasonFieldSettlementType loanDeclineErrorReasonFieldSettlementType = new LoanDeclineErrorReasonFieldSettlementType();
								loanDeclineErrorReasonFieldSettlementType.setField(LoanDeclineErrorReasonFieldSettlementType.FieldEnum.fromValue(field));
								if (nodeValue != null) {
									loanDeclineErrorReasonFieldSettlementType.setExpectedValue(SettlementType.fromValue(nodeValue.textValue()));
								}
								responseErrors.add(loanDeclineErrorReasonFieldSettlementType);

							} else if (LoanDeclineErrorReasonFieldTradeDate.FieldEnum.fromValue(field) != null) {

								LoanDeclineErrorReasonFieldTradeDate loanDeclineErrorReasonFieldTradeDate = new LoanDeclineErrorReasonFieldTradeDate();
								loanDeclineErrorReasonFieldTradeDate.setField(LoanDeclineErrorReasonFieldTradeDate.FieldEnum.fromValue(field));
								if (nodeValue != null) {
									LocalDateJacksonDeserializer dateDeserializer = new LocalDateJacksonDeserializer();
									loanDeclineErrorReasonFieldTradeDate.setExpectedValue(dateDeserializer.deserialize(nodeValue.textValue()));
								}
								responseErrors.add(loanDeclineErrorReasonFieldTradeDate);

							} else if (LoanDeclineErrorReasonFieldTermDate.FieldEnum.fromValue(field) != null) {

								LoanDeclineErrorReasonFieldTermDate loanDeclineErrorReasonFieldTermDate = new LoanDeclineErrorReasonFieldTermDate();
								loanDeclineErrorReasonFieldTermDate.setField(LoanDeclineErrorReasonFieldTermDate.FieldEnum.fromValue(field));
								if (nodeValue != null) {
									LocalDateJacksonDeserializer dateDeserializer = new LocalDateJacksonDeserializer();
									loanDeclineErrorReasonFieldTermDate.setExpectedValue(dateDeserializer.deserialize(nodeValue.textValue()));
								}
								responseErrors.add(loanDeclineErrorReasonFieldTermDate);

							} else if (LoanDeclineErrorReasonFieldTermType.FieldEnum.fromValue(field) != null) {

								LoanDeclineErrorReasonFieldTermType loanDeclineErrorReasonFieldTermType = new LoanDeclineErrorReasonFieldTermType();
								loanDeclineErrorReasonFieldTermType.setField(LoanDeclineErrorReasonFieldTermType.FieldEnum.fromValue(field));
								if (nodeValue != null) {
									loanDeclineErrorReasonFieldTermType.setExpectedValue(TermType.fromValue(nodeValue.textValue()));
								}
								responseErrors.add(loanDeclineErrorReasonFieldTermType);

							}
						}
					}
					
					loanDeclineErrorResponse.setErrors(responseErrors);
				}
				
				impl = loanDeclineErrorResponse;

			} else if (LoanCancelErrorReason.fromValue(nodeReason.textValue()) != null) {

				LoanCancelErrorResponse loanCancelErrorResponse = new LoanCancelErrorResponse();
				loanCancelErrorResponse.setReason(LoanCancelErrorReason.fromValue(nodeReason.textValue()));

				impl = loanCancelErrorResponse;

			} else if (LoanCancelPendingErrorReason.fromValue(nodeReason.textValue()) != null) {

				LoanCancelPendingErrorResponse loanCancelPendingErrorResponse = new LoanCancelPendingErrorResponse();
				loanCancelPendingErrorResponse
						.setReason(LoanCancelPendingErrorReason.fromValue(nodeReason.textValue()));

				impl = loanCancelPendingErrorResponse;

			}
		}

		return impl;
	}
}
