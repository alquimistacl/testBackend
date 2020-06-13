
package com.ionix.backend.dto;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "name", "detail" })
public class Item {

	@JsonProperty("name")
	private String name;
	@JsonProperty("detail")
	private Detail detail;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("name")
	public String getName() {
		return name;
	}

	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}

	@JsonProperty("detail")
	public Detail getDetail() {
		return detail;
	}

	@JsonProperty("detail")
	public void setDetail(Detail detail) {
		this.detail = detail;
	}

}
