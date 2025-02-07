package com.os.console.api.tasks;

import org.springframework.web.reactive.function.client.WebClient;

import com.os.client.model.InternalReferenceUpdate;
import com.os.console.util.RESTUtil;

public class UpdateLoanInternalReferenceTask implements Runnable {

	private WebClient webClient;
	private String loanId;
	private String internalRefId;

	public UpdateLoanInternalReferenceTask(WebClient webClient, String loanId, String internalRefId) {
		this.webClient = webClient;
		this.loanId = loanId;
		this.internalRefId = internalRefId;
	}

	@Override
	public void run() {

		InternalReferenceUpdate internalReferenceUpdate = new InternalReferenceUpdate();
		internalReferenceUpdate.setInternalReference(internalRefId);

		RESTUtil.patchRequest(webClient, "/loans/" + loanId, internalReferenceUpdate);

	}
}
