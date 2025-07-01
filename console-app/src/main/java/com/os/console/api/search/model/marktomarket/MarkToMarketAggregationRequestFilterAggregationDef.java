package com.os.console.api.search.model.marktomarket;

import com.os.console.api.search.model.AggregationDefTerms;

public class MarkToMarketAggregationRequestFilterAggregationDef {
	AggregationDefTerms terms;
	MarkToMarketAggregationRequestFilterAggregationFields aggs;

	public AggregationDefTerms getTerms() {
		return terms;
	}

	public void setTerms(AggregationDefTerms terms) {
		this.terms = terms;
	}

	public MarkToMarketAggregationRequestFilterAggregationFields getAggs() {
		return aggs;
	}

	public void setAggs(MarkToMarketAggregationRequestFilterAggregationFields aggs) {
		this.aggs = aggs;
	}

}
