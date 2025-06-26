package com.os.console.api.search.model;

public class AggregationData {
	private int doc_count_error_upper_bound;
	private int sum_other_doc_count;

	public int getDoc_count_error_upper_bound() {
		return doc_count_error_upper_bound;
	}

	public void setDoc_count_error_upper_bound(int doc_count_error_upper_bound) {
		this.doc_count_error_upper_bound = doc_count_error_upper_bound;
	}

	public int getSum_other_doc_count() {
		return sum_other_doc_count;
	}

	public void setSum_other_doc_count(int sum_other_doc_count) {
		this.sum_other_doc_count = sum_other_doc_count;
	}
}
