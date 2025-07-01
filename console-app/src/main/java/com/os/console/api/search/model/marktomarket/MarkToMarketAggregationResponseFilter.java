package com.os.console.api.search.model.marktomarket;

import com.os.console.api.search.SearchResponse;

public class MarkToMarketAggregationResponseFilter extends SearchResponse {
	MarkToMarketAggregationResponseSubFilter filtered_sum;

	public MarkToMarketAggregationResponseSubFilter getFiltered_sum() {
		return filtered_sum;
	}

	public void setFiltered_sum(MarkToMarketAggregationResponseSubFilter filtered_sum) {
		this.filtered_sum = filtered_sum;
	}

}
