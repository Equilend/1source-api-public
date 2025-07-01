package com.os.console.api.search.model.marktomarket;

import com.os.console.api.search.SearchResponse;

public class MarkToMarketAggregationResponse extends SearchResponse {
	MarkToMarketAggregationResponseFilter aggregations;

	public MarkToMarketAggregationResponseFilter getAggregations() {
		return aggregations;
	}

	public void setAggregations(MarkToMarketAggregationResponseFilter aggregations) {
		this.aggregations = aggregations;
	}

}
