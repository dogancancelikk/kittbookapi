package com.eventz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventz.model.User;
import com.eventz.repository.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService {
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Override
	public User findByUserName(String username) {
		User user= accountRepository.findByUserName(username);
		if (user == null || user.getStatus() == 0) {
			return null;
		}
		return user;
	}

}
