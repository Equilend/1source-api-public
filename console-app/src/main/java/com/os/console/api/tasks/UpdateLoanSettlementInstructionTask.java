package com.os.console.api.tasks;

import org.springframework.web.reactive.function.client.WebClient;

import com.os.client.model.SettlementInstructionUpdate;
import com.os.console.util.RESTUtil;

public class UpdateLoanSettlementInstructionTask implements Runnable {

	private WebClient webClient;
	private String loanId;
	private SettlementInstructionUpdate settlementInstructionUpdate;

	public UpdateLoanSettlementInstructionTask(WebClient webClient, String loanId,
			SettlementInstructionUpdate settlementInstructionUpdate) {
		this.webClient = webClient;
		this.loanId = loanId;
		this.settlementInstructionUpdate = settlementInstructionUpdate;
	}

	@Override
	public void run() {

		RESTUtil.patchRequest(webClient, "/loans/" + loanId, settlementInstructionUpdate);

	}
}
