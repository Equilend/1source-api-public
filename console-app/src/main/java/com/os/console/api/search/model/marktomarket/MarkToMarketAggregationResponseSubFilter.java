package com.os.console.api.search.model.marktomarket;

import com.os.console.api.search.model.Aggregation;

public class MarkToMarketAggregationResponseSubFilter extends Aggregation {
	MarkToMarketAggregationResponseData total_mark_today;

	public MarkToMarketAggregationResponseData getTotal_mark_today() {
		return total_mark_today;
	}

	public void setTotal_mark_today(MarkToMarketAggregationResponseData total_mark_today) {
		this.total_mark_today = total_mark_today;
	}

}
