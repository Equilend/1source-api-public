package com.os.console.api.tasks;

import org.springframework.web.reactive.function.client.WebClient;

import com.os.console.util.RESTUtil;

public class ApproveRerateTask implements Runnable {

	private WebClient webClient;
	private String loanId;
	private String rerateId;

	public ApproveRerateTask(WebClient webClient, String loanId, String rerateId) {
		this.webClient = webClient;
		this.loanId = loanId;
		this.rerateId = rerateId;
	}

	@Override
	public void run() {

		RESTUtil.postRequest(webClient, "/loans/" + loanId + "/rerates/" + rerateId + "/approve");
	}
}
