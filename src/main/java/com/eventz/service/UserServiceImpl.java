package com.eventz.service;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Calendar;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.eventz.model.Library;
import com.eventz.model.User;
import com.eventz.repository.LibraryRepository;
import com.eventz.repository.UserRepository;
import com.eventz.util.PasswordGenerator;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	@Autowired
	LibraryRepository libRepository;
	@Autowired
	EmailService emailService;
	public static final String DEFAULT_IMAGE = "http://kittbook.com/images/default-user.png";
	
	 private SecureRandom random = new SecureRandom();

	  public String nextSessionId() {
	    return new BigInteger(130, random).toString(32);
	  }

	@Override
	public Collection<User> findAll() {

		Collection<User> user = userRepository.findAllUser();
		return user;
	}

	@Override
	public User findOne(Long id) {
		User user = userRepository.findOneUser(id);
		return user;
	}

	@Override
	public User create(User user) {

		Library userLibrary = new Library();
		user.setStatus(1);
		if(user.getImage()==null)
			user.setImage(DEFAULT_IMAGE);
		String token = nextSessionId();
		if(!StringUtils.hasText(user.getFacebookID()) && !StringUtils.hasText(user.getGoogleID())) {
			user.setUserStatus(0);
		} else {
			user.setUserStatus(1);
		}
		user.setToken(token);
		if(!StringUtils.hasText(user.getPassword())) {
			user.setPassword(PasswordGenerator.generate(16));
		}
		int isAdmin=0;
		user.setIsadmin(isAdmin);
		user.setCreateDate(Calendar.getInstance().getTime());
		User newUser = userRepository.save(user);
		userLibrary.setOwnerID(newUser.getId());
		userLibrary.setName(newUser.getUserName() + " okuma listesi");
		userLibrary.setStatus(1);
		Library lib = libRepository.save(userLibrary);
		newUser.setLibraryID(lib.getId());
		User updateUser = this.update(newUser);
		if(user.getUserStatus() == 0) {
			emailService.sendActivationMail(updateUser, token);
		}
		return updateUser;
	}

	@Override
	public User update(User user) {
		
		if (userRepository.findOne(user.getId()) == null)
			return null;
		return userRepository.save(user);
	}

	@Override
	public void delete(Long id) {

		User user = userRepository.findOne(id);
		user.setStatus(0);
		userRepository.save(user);

	}

	@Override
	public User findByUser(String userName) {
		User user = userRepository.findByUserName(userName);
		if (user == null)
			return null;
		return user;
	}

	@Override
	public User findByMail(String email) {
		User user = userRepository.findByMail(email);
		if (user == null)
			return null;
		return user;
	}
	
	@Override
	public User findByFacebookId(String facebookId) {
		User user = userRepository.findByFacebookId(facebookId);
		return user != null ? user : null;
	}
	
	@Override
	public User findByGoogleId(String googleId) {
		User user = userRepository.findByGoogleId(googleId);
		return user != null ? user : null;
	}

	@Override
	public User verify(User user) {
		if(userRepository.findOne(user.getId())==null)
			return null;
		user.setUserStatus(1);
		userRepository.save(user);
		return null;
	}

}
