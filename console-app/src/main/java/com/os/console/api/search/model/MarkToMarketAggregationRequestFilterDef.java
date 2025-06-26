package com.os.console.api.search.model;

public class MarkToMarketAggregationRequestFilterDef {
	MarkToMarketAggregationRequestFilterTerm filter;
	MarkToMarketAggregationRequestFilterAggregation aggs;

	public MarkToMarketAggregationRequestFilterAggregation getAggs() {
		return aggs;
	}

	public void setAggs(MarkToMarketAggregationRequestFilterAggregation aggs) {
		this.aggs = aggs;
	}

	public MarkToMarketAggregationRequestFilterTerm getFilter() {
		return filter;
	}

	public void setFilter(MarkToMarketAggregationRequestFilterTerm filter) {
		this.filter = filter;
	}

}
