package com.os.console.api.tasks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.reactive.function.client.WebClient;

import com.os.console.api.search.SearchResponse;
import com.os.console.api.search.model.Query;
import com.os.console.util.ConsoleOutputUtil;
import com.os.console.util.RESTUtil;

public class OpenSearchQueryTask implements Runnable {

	private static final Logger logger = LoggerFactory.getLogger(OpenSearchQueryTask.class);

	private WebClient webClient;
	private Query query;

	public OpenSearchQueryTask(WebClient webClient, Query query) {
		this.webClient = webClient;
		this.query = query;
	}

	@Override
	public void run() {

		String uri = "/search/_search";

		SearchResponse searchResponse = (SearchResponse) RESTUtil.getRequest(webClient, uri, SearchResponse.class,
				query);

		System.out.println();
	}

	public void printHeader() {
		System.out.println();
		System.out.print(ConsoleOutputUtil.padSpaces("Loan Id", 40));
		System.out.print(ConsoleOutputUtil.padSpaces("Status", 12));
		System.out.print(ConsoleOutputUtil.padSpaces("Borrower", 15));
		System.out.print(ConsoleOutputUtil.padSpaces("Settlement", 14));
		System.out.print(ConsoleOutputUtil.padSpaces("Lender", 15));
		System.out.print(ConsoleOutputUtil.padSpaces("Settlement", 14));
		System.out.print(ConsoleOutputUtil.padSpaces("Trade Date", 15));
		System.out.print(ConsoleOutputUtil.padSpaces("Ticker", 10));
		System.out.print(ConsoleOutputUtil.padSpaces("Orig Quantity", 15));
		System.out.print(ConsoleOutputUtil.padSpaces("Open Quantity", 15));
		System.out.println();
		System.out.print(ConsoleOutputUtil.padDivider(40));
		System.out.print(ConsoleOutputUtil.padDivider(12));
		System.out.print(ConsoleOutputUtil.padDivider(15));
		System.out.print(ConsoleOutputUtil.padDivider(14));
		System.out.print(ConsoleOutputUtil.padDivider(15));
		System.out.print(ConsoleOutputUtil.padDivider(14));
		System.out.print(ConsoleOutputUtil.padDivider(15));
		System.out.print(ConsoleOutputUtil.padDivider(10));
		System.out.print(ConsoleOutputUtil.padDivider(15));
		System.out.print(ConsoleOutputUtil.padDivider(15));
		System.out.println();
	}

}
