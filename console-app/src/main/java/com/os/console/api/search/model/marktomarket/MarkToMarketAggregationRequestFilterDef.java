package com.os.console.api.search.model.marktomarket;

import com.os.console.api.search.model.Term;

public class MarkToMarketAggregationRequestFilterDef {
	Term filter;
	MarkToMarketAggregationRequestFilterAggregation aggs;

	public MarkToMarketAggregationRequestFilterAggregation getAggs() {
		return aggs;
	}

	public void setAggs(MarkToMarketAggregationRequestFilterAggregation aggs) {
		this.aggs = aggs;
	}

	public Term getFilter() {
		return filter;
	}

	public void setFilter(Term filter) {
		this.filter = filter;
	}

}
