# Loan

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**loanId** | **String** |  | 
**lastEvent** | [**Event**](Event.md) |  |  [optional]
**loanStatus** | [**LoanStatus**](LoanStatus.md) |  | 
**loanStatusReason** | **OneOfLoanLoanStatusReason** |  |  [optional]
**lastUpdatePartyId** | **String** |  |  [optional]
**lastUpdateDateTime** | [**OffsetDateTime**](OffsetDateTime.md) |  |  [optional]
**isInitiator** | **Boolean** |  |  [optional]
**trade** | [**TradeAgreement**](TradeAgreement.md) |  |  [optional]
**settlement** | [**List&lt;PartySettlementInstruction&gt;**](PartySettlementInstruction.md) |  |  [optional]
**parentLoanId** | **String** |  |  [optional]
