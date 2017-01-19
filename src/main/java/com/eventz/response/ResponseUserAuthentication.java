package com.eventz.response;

import com.eventz.model.User;

public class ResponseUserAuthentication {
	private User user;
	private String errorMessage;

	public ResponseUserAuthentication() {
	}

	public ResponseUserAuthentication(User user, String errorMessage) {
		this.user = user;
		this.errorMessage = errorMessage;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
