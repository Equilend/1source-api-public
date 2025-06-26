package com.os.console.api.search.model;

public class MarkToMarketAggregationRequest extends AggregationRequest {
	MarkToMarketAggregationRequestFilter aggs;

	public MarkToMarketAggregationRequestFilter getAggs() {
		return aggs;
	}

	public void setAggs(MarkToMarketAggregationRequestFilter aggs) {
		this.aggs = aggs;
	}

}
