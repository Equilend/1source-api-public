package com.os.console.api.tasks;

import org.springframework.web.reactive.function.client.WebClient;

import com.os.client.model.LoanDeclineErrorResponse;
import com.os.console.util.RESTUtil;

public class DeclineLoanTask implements Runnable {

	private WebClient webClient;
	private String loanId;
	private LoanDeclineErrorResponse declineError;

	public DeclineLoanTask(WebClient webClient, String loanId, LoanDeclineErrorResponse declineError) {
		this.webClient = webClient;
		this.loanId = loanId;
		this.declineError = declineError;
	}

	@Override
	public void run() {
		RESTUtil.postRequest(webClient, "/loans/" + loanId + "/decline", declineError);
	}
}
