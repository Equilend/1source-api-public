package com.os.console.api.tasks;

import org.springframework.web.reactive.function.client.WebClient;

import com.os.client.model.RerateCancelErrorResponse;
import com.os.console.util.RESTUtil;

public class CancelRerateTask implements Runnable {

	private WebClient webClient;
	private String loanId;
	private String rerateId;
	private RerateCancelErrorResponse cancelReason;

	public CancelRerateTask(WebClient webClient, String loanId, String rerateId, RerateCancelErrorResponse cancelReason) {
		this.webClient = webClient;
		this.loanId = loanId;
		this.rerateId = rerateId;
		this.cancelReason = cancelReason;
	}

	@Override
	public void run() {
		RESTUtil.postRequest(webClient, "/loans/" + loanId + "/rerates/" + rerateId + "/cancel", cancelReason);
	}
}
