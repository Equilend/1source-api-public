package com.os.console.api.tasks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.reactive.function.client.WebClient;

import com.os.client.model.CurrencyCd;
import com.os.client.model.Party;
import com.os.console.api.search.SearchResponse;
import com.os.console.util.ConsoleOutputUtil;
import com.os.console.util.PayloadUtil;
import com.os.console.util.RESTUtil;

public class ReportMarkToMarketTask implements Runnable {

	private static final Logger logger = LoggerFactory.getLogger(ReportMarkToMarketTask.class);

	private WebClient webClient;
	public ReportMarkToMarketTask(WebClient webClient, Party counterparty) {
		this.webClient = webClient;
	}

	@Override
	public void run() {

		String uri = "/search/_search";

		SearchResponse searchResponse = (SearchResponse) RESTUtil.getRequest(webClient, uri, SearchResponse.class, PayloadUtil.createMarkToMarketAggregationRequest(CurrencyCd.USD));

		System.out.println(searchResponse);
	}

	public void printHeader() {
		System.out.println();
		System.out.print(ConsoleOutputUtil.padSpaces("Test", 40));
		System.out.println();
		System.out.print(ConsoleOutputUtil.padDivider(40));
		System.out.println();
	}

}
