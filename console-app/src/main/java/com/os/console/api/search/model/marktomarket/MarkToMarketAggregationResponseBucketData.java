package com.os.console.api.search.model.marktomarket;

import com.os.console.api.search.model.AggregationResponseBucketValue;

public class MarkToMarketAggregationResponseBucketData {
	private String key;
	private int doc_count;
	AggregationResponseBucketValue total_collateral_today;
	AggregationResponseBucketValue total_mark_today;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public int getDoc_count() {
		return doc_count;
	}

	public void setDoc_count(int doc_count) {
		this.doc_count = doc_count;
	}

	public AggregationResponseBucketValue getTotal_collateral_today() {
		return total_collateral_today;
	}

	public void setTotal_collateral_today(AggregationResponseBucketValue total_collateral_today) {
		this.total_collateral_today = total_collateral_today;
	}

	public AggregationResponseBucketValue getTotal_mark_today() {
		return total_mark_today;
	}

	public void setTotal_mark_today(AggregationResponseBucketValue total_mark_today) {
		this.total_mark_today = total_mark_today;
	}

}
