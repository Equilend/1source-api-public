package com.os.console.api.tasks;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.reactive.function.client.WebClient;

import com.os.client.model.CurrencyCd;
import com.os.console.api.search.model.MarkToMarketAggregationResponse;
import com.os.console.api.search.model.MarkToMarketAggregationResponseBucketData;
import com.os.console.api.search.model.MarkToMarketAggregationResponseData;
import com.os.console.api.search.model.MarkToMarketAggregationResponseFilter;
import com.os.console.api.search.model.MarkToMarketAggregationResponseSubFilter;
import com.os.console.util.ConsoleOutputUtil;
import com.os.console.util.PayloadUtil;
import com.os.console.util.RESTUtil;

public class ReportMarkToMarketTask implements Runnable {

	private static final Logger logger = LoggerFactory.getLogger(ReportMarkToMarketTask.class);

	private WebClient webClient;
	private CurrencyCd currencyCd;
	
	public ReportMarkToMarketTask(WebClient webClient, CurrencyCd currencyCd) {
		this.webClient = webClient;
		this.currencyCd = currencyCd;
	}

	@Override
	public void run() {

		String uri = "/search/_search";

		MarkToMarketAggregationResponse searchResponse = (MarkToMarketAggregationResponse) RESTUtil.getRequest(webClient, uri, MarkToMarketAggregationResponse.class, PayloadUtil.createMarkToMarketAggregationRequest(currencyCd));

		//ConsoleOutputUtil.printObject(searchResponse);
		
		ArrayList<MarkToMarketAggregationResponseBucketData> buckets = null;
		
		if (searchResponse != null) {
			MarkToMarketAggregationResponseFilter aggregations = searchResponse.getAggregations();
			if (aggregations != null) {
				MarkToMarketAggregationResponseSubFilter filteredAggregation = aggregations.getFiltered_sum();
				if (filteredAggregation != null) {
					MarkToMarketAggregationResponseData data = filteredAggregation.getTotal_mark_today();
					if (data != null) {
						buckets = data.getBuckets();
					}
				}
			}
		}
		
		if (buckets == null || buckets.size() == 0) {
			logger.warn("Invalid report object or nothing to report");
			System.out.println("nothing found");
			printHeader();
		} else {
			
			System.out.println("complete");
			printHeader();
			int rows = 1;
			for (MarkToMarketAggregationResponseBucketData bucket : buckets) {
				if (rows % 15 == 0) {
					printHeader();
				}

				String borrower = bucket.getKey().substring(0, bucket.getKey().indexOf("::"));
				
				String collateralValueAsString = null;
				if (bucket.getTotal_collateral_today() != null) {
					collateralValueAsString = bucket.getTotal_collateral_today().getValue_as_string();
				}
				
				String markValueAsString = null;
				if (bucket.getTotal_mark_today() != null) {
					markValueAsString = bucket.getTotal_mark_today().getValue_as_string();
				}
				
				System.out.print(ConsoleOutputUtil.padSpaces(borrower, 20));
				System.out.print(ConsoleOutputUtil.padSpaces(collateralValueAsString, 15, true));
				System.out.print(ConsoleOutputUtil.padSpaces(markValueAsString, 15, true));

				System.out.println();

				rows++;
			}
		}
		System.out.println();
	}

	public void printHeader() {
		System.out.println();
		System.out.print(ConsoleOutputUtil.padSpaces("Borrower", 20));
		System.out.print(ConsoleOutputUtil.padSpaces("Exposure", 15));
		System.out.print(ConsoleOutputUtil.padSpaces("Net Mark", 15));
		System.out.println();
		System.out.print(ConsoleOutputUtil.padDivider(20));
		System.out.print(ConsoleOutputUtil.padDivider(15));
		System.out.print(ConsoleOutputUtil.padDivider(15));
		System.out.println();
	}

}
