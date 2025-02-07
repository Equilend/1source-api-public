package com.os.console.api.tasks;

import org.springframework.web.reactive.function.client.WebClient;

import com.os.client.model.Recall;
import com.os.client.model.RecallAcknowledgement;
import com.os.console.util.RESTUtil;

public class AcknowledgeRecallTask implements Runnable {

	private WebClient webClient;
	private Recall recall;
	private RecallAcknowledgement recallAcknowledgement;

	public AcknowledgeRecallTask(WebClient webClient, Recall recall, RecallAcknowledgement recallAcknowledgement) {
		this.webClient = webClient;
		this.recall = recall;
		this.recallAcknowledgement = recallAcknowledgement;
	}

	@Override
	public void run() {
		RESTUtil.postRequest(webClient, "/loans/" + recall.getLoanId() + "/recalls/" + recall.getRecallId() + "/acknowledge", recallAcknowledgement);
	}
}
