package com.os.console.api.search.model;

public class AggregationResponseBucketValue {
	private float value;
	private String value_as_string;

	public float getValue() {
		return value;
	}

	public void setValue(float value) {
		this.value = value;
	}

	public String getValue_as_string() {
		return value_as_string;
	}

	public void setValue_as_string(String value_as_string) {
		this.value_as_string = value_as_string;
	}

}
