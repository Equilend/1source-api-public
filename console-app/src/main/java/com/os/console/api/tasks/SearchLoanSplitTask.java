package com.os.console.api.tasks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.reactive.function.client.WebClient;

import com.os.client.model.LoanSplit;
import com.os.console.util.RESTUtil;

public class SearchLoanSplitTask implements Runnable {

	private static final Logger logger = LoggerFactory.getLogger(SearchLoanSplitTask.class);

	private WebClient webClient;
	private String loanId;
	private String splitId;

	private LoanSplit loanSplit;
	
	public SearchLoanSplitTask(WebClient webClient, String loanId, String splitId) {
		this.webClient = webClient;
		this.loanId = loanId;
		this.splitId = splitId;
	}

	@Override
	public void run() {

		loanSplit = (LoanSplit) RESTUtil.getRequest(webClient, "/loans/" + loanId + "/split/" + splitId, LoanSplit.class);

		if (loanSplit == null) {
			logger.warn("Invalid loan split object or loan split not found");
			System.out.println("loan split not found");
		} else {
			System.out.println("complete");
			System.out.println();			
		}
	}

	public LoanSplit getLoanSplit() {
		return loanSplit;
	}
	
	
}
