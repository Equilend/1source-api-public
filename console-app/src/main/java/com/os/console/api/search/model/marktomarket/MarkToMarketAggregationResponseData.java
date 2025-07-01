package com.os.console.api.search.model.marktomarket;

import java.util.ArrayList;

import com.os.console.api.search.model.AggregationData;

public class MarkToMarketAggregationResponseData extends AggregationData {

	ArrayList<MarkToMarketAggregationResponseBucketData> buckets = new ArrayList<MarkToMarketAggregationResponseBucketData>();

	public ArrayList<MarkToMarketAggregationResponseBucketData> getBuckets() {
		return buckets;
	}

	public void setBuckets(ArrayList<MarkToMarketAggregationResponseBucketData> buckets) {
		this.buckets = buckets;
	}

}
