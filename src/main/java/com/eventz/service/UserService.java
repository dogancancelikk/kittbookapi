package com.eventz.service;

import java.util.Collection;

import com.eventz.model.User;

public interface UserService {
	Collection<User> findAll();
	
	User findOne(Long id);
	
	User create(User user);
	
	User update(User user);
	
	User verify(User user);
	
	void delete(Long id);
	
	User findByUser(String userName);
	
	User findByMail(String email);

	User findByFacebookId(String facebookId);

	User findByGoogleId(String googleId);
}
