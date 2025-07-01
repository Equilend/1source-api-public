package com.os.console.api.search.model;

public class Query {

	BooleanQuery query;

	public BooleanQuery getQuery() {
		return query;
	}

	public void setQuery(BooleanQuery query) {
		this.query = query;
	}

	public boolean isTrack_total_hits() {
		return track_total_hits;
	}

	public void setTrack_total_hits(boolean track_total_hits) {
		this.track_total_hits = track_total_hits;
	}

	private boolean track_total_hits;
}
