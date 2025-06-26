package com.os.console.api.search;

public class SearchResponse {
	private float took;
	private boolean timed_out;
	SearchShards _shards;
	SearchHits hits;

	public float getTook() {
		return took;
	}

	public boolean getTimed_out() {
		return timed_out;
	}

	public SearchShards get_shards() {
		return _shards;
	}

	public SearchHits getHits() {
		return hits;
	}

	public void setTook(float took) {
		this.took = took;
	}

	public void setTimed_out(boolean timed_out) {
		this.timed_out = timed_out;
	}

	public void set_shards(SearchShards _shards) {
		this._shards = _shards;
	}

	public void setHits(SearchHits hits) {
		this.hits = hits;
	}

}
