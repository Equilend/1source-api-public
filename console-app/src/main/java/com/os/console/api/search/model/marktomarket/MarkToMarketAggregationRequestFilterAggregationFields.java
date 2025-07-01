package com.os.console.api.search.model.marktomarket;

import com.os.console.api.search.model.AggregationSum;

public class MarkToMarketAggregationRequestFilterAggregationFields {
	AggregationSum total_mark_today;
	AggregationSum total_collateral_today;

	public AggregationSum getTotal_mark_today() {
		return total_mark_today;
	}

	public void setTotal_mark_today(AggregationSum total_mark_today) {
		this.total_mark_today = total_mark_today;
	}

	public AggregationSum getTotal_collateral_today() {
		return total_collateral_today;
	}

	public void setTotal_collateral_today(AggregationSum total_collateral_today) {
		this.total_collateral_today = total_collateral_today;
	}

}
