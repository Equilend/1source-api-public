package com.os.console.api.search;

public class SearchShards {
	private float total;
	private float successful;
	private float skipped;
	private float failed;

	public float getTotal() {
		return total;
	}

	public float getSuccessful() {
		return successful;
	}

	public float getSkipped() {
		return skipped;
	}

	public float getFailed() {
		return failed;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public void setSuccessful(float successful) {
		this.successful = successful;
	}

	public void setSkipped(float skipped) {
		this.skipped = skipped;
	}

	public void setFailed(float failed) {
		this.failed = failed;
	}

}
