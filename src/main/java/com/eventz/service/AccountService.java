package com.eventz.service;

import com.eventz.model.User;

public interface AccountService {

	User findByUserName(String username);
}
