package com.os.console.api.search;

import java.util.ArrayList;

import com.os.console.api.search.model.IndexData;

public class SearchHits {
	SearchHitsTotal total;
	private String max_score = null;
	ArrayList<IndexData> hits = new ArrayList<IndexData>();

	public SearchHitsTotal getTotal() {
		return total;
	}

	public String getMax_score() {
		return max_score;
	}

	public void setTotal(SearchHitsTotal total) {
		this.total = total;
	}

	public void setMax_score(String max_score) {
		this.max_score = max_score;
	}

	public ArrayList<IndexData> getHits() {
		return hits;
	}

	public void setHits(ArrayList<IndexData> hits) {
		this.hits = hits;
	}
}
