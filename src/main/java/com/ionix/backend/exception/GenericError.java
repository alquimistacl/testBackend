package com.ionix.backend.exception;

import io.swagger.annotations.ApiModelProperty;

/**
 * Object to record a generic error
 * 
 * @author Luis San Martin
 *
 */
public class GenericError {

	@ApiModelProperty(notes = "User error message")
	private String userMessage;

	@ApiModelProperty(notes = "Internal system error message")
	private String internalMessage;

	public String getUserMessage() {
		return userMessage;
	}

	public void setUserMessage(String userMessage) {
		this.userMessage = userMessage;
	}

	public String getInternalMessage() {
		return internalMessage;
	}

	public void setInternalMessage(String internalMessage) {
		this.internalMessage = internalMessage;
	}
}