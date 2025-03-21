package com.os.console.api.tasks;

import org.springframework.web.reactive.function.client.WebClient;

import com.os.client.model.RerateDeclineErrorResponse;
import com.os.console.util.RESTUtil;

public class DeclineRerateTask implements Runnable {

	private WebClient webClient;
	private String loanId;
	private String rerateId;
	private RerateDeclineErrorResponse declineError;

	public DeclineRerateTask(WebClient webClient, String loanId, String rerateId, RerateDeclineErrorResponse declineError) {
		this.webClient = webClient;
		this.loanId = loanId;
		this.rerateId = rerateId;
		this.declineError = declineError;
	}

	@Override
	public void run() {
		RESTUtil.postRequest(webClient, "/loans/" + loanId + "/rerates/" + rerateId + "/decline", declineError);
	}
}
