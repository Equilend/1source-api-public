package com.os.console.api.tasks;

import org.springframework.web.reactive.function.client.WebClient;

import com.os.console.util.RESTUtil;

public class CancelPendingLoanTask implements Runnable {

	private WebClient webClient;
	private String loanId;

	public CancelPendingLoanTask(WebClient webClient, String loanId) {
		this.webClient = webClient;
		this.loanId = loanId;
	}

	@Override
	public void run() {
		RESTUtil.postRequest(webClient, "/loans/" + loanId + "/cancelpending");
	}
}
