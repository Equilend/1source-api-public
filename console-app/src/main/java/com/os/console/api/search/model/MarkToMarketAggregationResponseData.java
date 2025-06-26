package com.os.console.api.search.model;

import java.util.ArrayList;

public class MarkToMarketAggregationResponseData extends AggregationData {

	ArrayList<MarkToMarketAggregationResponseBucketData> buckets = new ArrayList<MarkToMarketAggregationResponseBucketData>();

	public ArrayList<MarkToMarketAggregationResponseBucketData> getBuckets() {
		return buckets;
	}

	public void setBuckets(ArrayList<MarkToMarketAggregationResponseBucketData> buckets) {
		this.buckets = buckets;
	}

}
