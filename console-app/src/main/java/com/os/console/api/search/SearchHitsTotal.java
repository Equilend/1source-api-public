package com.os.console.api.search;

public class SearchHitsTotal {
	private float value;
	private String relation;

	public float getValue() {
		return value;
	}

	public String getRelation() {
		return relation;
	}

	public void setValue(float value) {
		this.value = value;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

}
