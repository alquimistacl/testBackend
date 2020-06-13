package com.ionix.backend.model;

public class ExternalResponse {

	private Integer responseCode;
	private String description;
	private Long elapsedTime;
	private Result result;

	public Integer getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(Integer responseCode) {
		this.responseCode = responseCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "ExternalResponse [getResponseCode()=" + getResponseCode() + ", getDescription()=" + getDescription()
				+ ", getElapsedTime()=" + getElapsedTime() + ", getResult()=" + getResult() + "]";
	}

	public Long getElapsedTime() {
		return elapsedTime;
	}

	public void setElapsedTime(Long elapsedTime) {
		this.elapsedTime = elapsedTime;
	}

}
