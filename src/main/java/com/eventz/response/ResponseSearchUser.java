package com.eventz.response;

import java.util.ArrayList;

import com.eventz.dto.SearchUserDto;

public class ResponseSearchUser {
	private ArrayList<SearchUserDto> users;

	public ResponseSearchUser(ArrayList<SearchUserDto> users) {
		this.users = users;
	}

	public ResponseSearchUser() {
	}

	public ArrayList<SearchUserDto> getLabel() {
		return users;
	}

	public void setLabel(ArrayList<SearchUserDto> users) {
		this.users = users;
	}
}
