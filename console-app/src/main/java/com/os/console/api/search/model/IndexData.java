package com.os.console.api.search.model;

public class IndexData {
	private String _index;
	private String _id;
	private float _score;

	public String get_index() {
		return _index;
	}

	public void set_index(String _index) {
		this._index = _index;
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public float get_score() {
		return _score;
	}

	public void set_score(float _score) {
		this._score = _score;
	}
}
