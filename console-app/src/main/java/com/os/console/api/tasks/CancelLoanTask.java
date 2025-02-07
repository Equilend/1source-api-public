package com.os.console.api.tasks;

import org.springframework.web.reactive.function.client.WebClient;

import com.os.client.model.LoanCancelErrorResponse;
import com.os.console.util.RESTUtil;

public class CancelLoanTask implements Runnable {

	private WebClient webClient;
	private String loanId;
	private LoanCancelErrorResponse response;

	public CancelLoanTask(WebClient webClient, String loanId, LoanCancelErrorResponse response) {
		this.webClient = webClient;
		this.loanId = loanId;
		this.response = response;
	}

	@Override
	public void run() {
		RESTUtil.postRequest(webClient, "/loans/" + loanId + "/cancel", response);
	}
}
