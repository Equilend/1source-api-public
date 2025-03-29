package com.os.console.api;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

import com.os.client.model.Party;
import com.os.client.model.PartyRole;
import com.os.client.model.PartySettlementInstruction;

@ConfigurationProperties(prefix = "com.os.config")
public class ConsoleConfig {

	public static AuthToken TOKEN = null;
	public static Party ACTING_PARTY = null;
	public static PartyRole ACTING_AS = null;
	public static PartySettlementInstruction SETTLEMENT_INSTRUCTIONS = null;
	public static PartySettlementInstruction COUNTERPARTY_SETTLEMENT_INSTRUCTIONS = null;

	private final String api_uri;

	private final String auth_uri;
	private final String auth_client_id;
	private final String auth_client_secret;

	private String auth_username;
	private String auth_password;
	private String auth_party;
	private String auth_actAs;

	private String settlement_internalAcctCd;
	private String settlement_settlementBic;
	private String settlement_localAgentBic;
	private String settlement_localAgentName;
	private String settlement_localAgentAcct;
	private String settlement_dtcParticipantNumber;
	private String settlement_custodianName;
	private String settlement_custodianBic;
	private String settlement_custodianAcct;

	private String counterparty_settlement_internalAcctCd;
	private String counterparty_settlement_settlementBic;
	private String counterparty_settlement_localAgentBic;
	private String counterparty_settlement_localAgentName;
	private String counterparty_settlement_localAgentAcct;
	private String counterparty_settlement_dtcParticipantNumber;
	private String counterparty_settlement_custodianName;
	private String counterparty_settlement_custodianBic;
	private String counterparty_settlement_custodianAcct;

	@ConstructorBinding
	public ConsoleConfig(String api_uri, String auth_uri, String auth_client_id, String auth_client_secret,
			String auth_username, String auth_password, String auth_party, String auth_actAs,
			String settlement_internalAcctCd, String settlement_settlementBic, String settlement_localAgentBic,
			String settlement_localAgentName, String settlement_localAgentAcct, String settlement_dtcParticipantNumber,
			String settlement_custodianName, String settlement_custodianBic, String settlement_custodianAcct,
			String counterparty_settlement_internalAcctCd, String counterparty_settlement_settlementBic,
			String counterparty_settlement_localAgentBic, String counterparty_settlement_localAgentName,
			String counterparty_settlement_localAgentAcct, String counterparty_settlement_dtcParticipantNumber,
			String counterparty_settlement_custodianName, String counterparty_settlement_custodianBic,
			String counterparty_settlement_custodianAcct) {
		super();
		this.api_uri = api_uri;
		this.auth_uri = auth_uri;
		this.auth_client_id = auth_client_id;
		this.auth_client_secret = auth_client_secret;
		this.auth_username = auth_username;
		this.auth_password = auth_password;
		this.auth_party = auth_party;
		this.auth_actAs = auth_actAs;
		this.settlement_internalAcctCd = settlement_internalAcctCd;
		this.settlement_settlementBic = settlement_settlementBic;
		this.settlement_localAgentBic = settlement_localAgentBic;
		this.settlement_localAgentName = settlement_localAgentName;
		this.settlement_localAgentAcct = settlement_localAgentAcct;
		this.settlement_dtcParticipantNumber = settlement_dtcParticipantNumber;
		this.settlement_custodianName = settlement_custodianName;
		this.settlement_custodianBic = settlement_custodianBic;
		this.settlement_custodianAcct = settlement_custodianAcct;
		this.counterparty_settlement_internalAcctCd = counterparty_settlement_internalAcctCd;
		this.counterparty_settlement_settlementBic = counterparty_settlement_settlementBic;
		this.counterparty_settlement_localAgentBic = counterparty_settlement_localAgentBic;
		this.counterparty_settlement_localAgentName = counterparty_settlement_localAgentName;
		this.counterparty_settlement_localAgentAcct = counterparty_settlement_localAgentAcct;
		this.counterparty_settlement_dtcParticipantNumber = counterparty_settlement_dtcParticipantNumber;
		this.counterparty_settlement_custodianName = counterparty_settlement_custodianName;
		this.counterparty_settlement_custodianBic = counterparty_settlement_custodianBic;
		this.counterparty_settlement_custodianAcct = counterparty_settlement_custodianAcct;
	}

	public String getAuth_username() {
		return auth_username;
	}

	public void setAuth_username(String auth_username) {
		this.auth_username = auth_username;
	}

	public String getAuth_password() {
		return auth_password;
	}

	public void setAuth_password(String auth_password) {
		this.auth_password = auth_password;
	}

	public String getAuth_party() {
		return auth_party;
	}

	public void setAuth_party(String auth_party) {
		this.auth_party = auth_party;
	}

	public String getAuth_actAs() {
		return auth_actAs;
	}

	public void setAuth_actAs(String auth_actAs) {
		this.auth_actAs = auth_actAs;
	}

	public String getSettlement_internalAcctCd() {
		return isSet(settlement_internalAcctCd) ? settlement_internalAcctCd : null;
	}

	public void setSettlement_internalAcctCd(String settlement_internalAcctCd) {
		this.settlement_internalAcctCd = settlement_internalAcctCd;
	}

	public String getSettlement_settlementBic() {
		return isSet(settlement_settlementBic) ? settlement_settlementBic : null;
	}

	public void setSettlement_settlementBic(String settlement_settlementBic) {
		this.settlement_settlementBic = settlement_settlementBic;
	}

	public String getSettlement_localAgentBic() {
		return isSet(settlement_localAgentBic) ? settlement_localAgentBic : null;
	}

	public void setSettlement_localAgentBic(String settlement_localAgentBic) {
		this.settlement_localAgentBic = settlement_localAgentBic;
	}

	public String getSettlement_localAgentName() {
		return isSet(settlement_localAgentName) ? settlement_localAgentName : null;
	}

	public void setSettlement_localAgentName(String settlement_localAgentName) {
		this.settlement_localAgentName = settlement_localAgentName;
	}

	public String getSettlement_localAgentAcct() {
		return isSet(settlement_localAgentAcct) ? settlement_localAgentAcct : null;
	}

	public void setSettlement_localAgentAcct(String settlement_localAgentAcct) {
		this.settlement_localAgentAcct = settlement_localAgentAcct;
	}

	public String getSettlement_dtcParticipantNumber() {
		return isSet(settlement_dtcParticipantNumber) ? settlement_dtcParticipantNumber : null;
	}

	public void setSettlement_dtcParticipantNumber(String settlement_dtcParticipantNumber) {
		this.settlement_dtcParticipantNumber = settlement_dtcParticipantNumber;
	}

	public String getSettlement_custodianName() {
		return isSet(settlement_custodianName) ? settlement_custodianName : null;
	}

	public void setSettlement_custodianName(String settlement_custodianName) {
		this.settlement_custodianName = settlement_custodianName;
	}

	public String getSettlement_custodianBic() {
		return isSet(settlement_custodianBic) ? settlement_custodianBic : null;
	}

	public void setSettlement_custodianBic(String settlement_custodianBic) {
		this.settlement_custodianBic = settlement_custodianBic;
	}

	public String getSettlement_custodianAcct() {
		return isSet(settlement_custodianAcct) ? settlement_custodianAcct : null;
	}

	public void setSettlement_custodianAcct(String settlement_custodianAcct) {
		this.settlement_custodianAcct = settlement_custodianAcct;
	}

	public String getCounterparty_settlement_internalAcctCd() {
		return counterparty_settlement_internalAcctCd;
	}

	public void setCounterparty_settlement_internalAcctCd(String counterparty_settlement_internalAcctCd) {
		this.counterparty_settlement_internalAcctCd = counterparty_settlement_internalAcctCd;
	}

	public String getCounterparty_settlement_settlementBic() {
		return counterparty_settlement_settlementBic;
	}

	public void setCounterparty_settlement_settlementBic(String counterparty_settlement_settlementBic) {
		this.counterparty_settlement_settlementBic = counterparty_settlement_settlementBic;
	}

	public String getCounterparty_settlement_localAgentBic() {
		return counterparty_settlement_localAgentBic;
	}

	public void setCounterparty_settlement_localAgentBic(String counterparty_settlement_localAgentBic) {
		this.counterparty_settlement_localAgentBic = counterparty_settlement_localAgentBic;
	}

	public String getCounterparty_settlement_localAgentName() {
		return counterparty_settlement_localAgentName;
	}

	public void setCounterparty_settlement_localAgentName(String counterparty_settlement_localAgentName) {
		this.counterparty_settlement_localAgentName = counterparty_settlement_localAgentName;
	}

	public String getCounterparty_settlement_localAgentAcct() {
		return counterparty_settlement_localAgentAcct;
	}

	public void setCounterparty_settlement_localAgentAcct(String counterparty_settlement_localAgentAcct) {
		this.counterparty_settlement_localAgentAcct = counterparty_settlement_localAgentAcct;
	}

	public String getCounterparty_settlement_dtcParticipantNumber() {
		return counterparty_settlement_dtcParticipantNumber;
	}

	public void setCounterparty_settlement_dtcParticipantNumber(String counterparty_settlement_dtcParticipantNumber) {
		this.counterparty_settlement_dtcParticipantNumber = counterparty_settlement_dtcParticipantNumber;
	}

	public String getCounterparty_settlement_custodianName() {
		return counterparty_settlement_custodianName;
	}

	public void setCounterparty_settlement_custodianName(String counterparty_settlement_custodianName) {
		this.counterparty_settlement_custodianName = counterparty_settlement_custodianName;
	}

	public String getCounterparty_settlement_custodianBic() {
		return counterparty_settlement_custodianBic;
	}

	public void setCounterparty_settlement_custodianBic(String counterparty_settlement_custodianBic) {
		this.counterparty_settlement_custodianBic = counterparty_settlement_custodianBic;
	}

	public String getCounterparty_settlement_custodianAcct() {
		return counterparty_settlement_custodianAcct;
	}

	public void setCounterparty_settlement_custodianAcct(String counterparty_settlement_custodianAcct) {
		this.counterparty_settlement_custodianAcct = counterparty_settlement_custodianAcct;
	}

	public String getApi_uri() {
		return isSet(api_uri) ? api_uri : null;
	}

	public String getAuth_uri() {
		return isSet(auth_uri) ? auth_uri : null;
	}

	public String getAuth_client_id() {
		return isSet(auth_client_id) ? auth_client_id : null;
	}

	public String getAuth_client_secret() {
		return isSet(auth_client_secret) ? auth_client_secret : null;
	}

	private boolean isSet(String val) {
		return val != null && val.trim().length() > 0;
	}
}
