package com.eventz.controller;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.eventz.model.User;
import com.eventz.request.RequestChangePassword;
import com.eventz.request.RequestDeleteUser;
import com.eventz.request.RequestUpdateAdmin;
import com.eventz.response.ResponseUserAuthentication;
import com.eventz.service.AccountService;
import com.eventz.service.ExceptionLogService;
import com.eventz.service.UserService;

@RestController
public class UserController {

	//private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	UserService userService;
	@Autowired
	AccountService accountService;
	@Autowired
	ExceptionLogService exceptionLogService;

	@RequestMapping(value = "/user/get", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<User>> getAllUsers() {
		Collection<User> users = userService.findAll();
		if (users == null)
			return new ResponseEntity<Collection<User>>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<Collection<User>>(users, HttpStatus.OK);
	}

	@RequestMapping(value = "/user/get/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> getOneUser(@PathVariable("id") Long id) {
		User user = userService.findOne(id);
		if (user == null) {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@RequestMapping(value = "/user/authenticate", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseUserAuthentication> authenticate(@RequestBody User user) {
		ResponseUserAuthentication response = new ResponseUserAuthentication();
		try {
			User userModel = accountService.findByUserName(user.getUserName());
			if (userModel == null || userModel.getStatus() == 0) {
				exceptionLogService.logException("UserController", "authenticate", "No user found", user.getUserName() + " - " + user.getPassword());
				response.setErrorMessage("Geçersiz kullanıcı adı");
				return new ResponseEntity<ResponseUserAuthentication>(response, HttpStatus.OK);
			}
			if (!userModel.getPassword().equals(user.getPassword())) {
				exceptionLogService.logException("UserController", "authenticate", "Wrong password", user.getUserName() + " - " + user.getPassword());
				response.setErrorMessage("Hatalı kullanıcı adı veya parola!");
				return new ResponseEntity<ResponseUserAuthentication>(response, HttpStatus.OK);
			}
			if (userModel.getUserStatus() == 0 && userModel.getCreateDate() != null && getDateDiff(userModel.getCreateDate(), Calendar.getInstance().getTime(), TimeUnit.DAYS) > 15) {
				exceptionLogService.logException("UserController", "authenticate", "Verification needed", user.getUserName() + " - " + user.getPassword());
				response.setErrorMessage("Lütfen mail adresinize gelen aktivasyon linkine tılayın");
				return new ResponseEntity<ResponseUserAuthentication>(response, HttpStatus.OK);
			}
			response.setUser(userModel);
			exceptionLogService.logException("UserController", "authenticate", "success", user.getUserName() + " - " + user.getPassword());
			return new ResponseEntity<ResponseUserAuthentication>(response, HttpStatus.OK);
		} catch (Exception e) {
			exceptionLogService.logException("UserController", "authenticate", e.getMessage() + e.getStackTrace(), user.getUserName() + " - " + user.getPassword());
			response.setErrorMessage("Lütfen tekrar deneyin");
			return new ResponseEntity<ResponseUserAuthentication>(response, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "user/update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<User> updateUser(@RequestBody User user) {
		User updatedUser = userService.update(user);

		return new ResponseEntity<User>(updatedUser, HttpStatus.OK);
	}

	@RequestMapping(value = "user/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<User> createUser(@RequestBody User user) throws ParseException {
		User createdUser = userService.create(user);
		if (createdUser == null)
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<User>(createdUser, HttpStatus.CREATED);
	}

	@RequestMapping(value = "user/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> deleteUser(@RequestBody RequestDeleteUser request) {

		User user = userService.findOne(request.getUserId());
		if (user.getPassword().equals(request.getPassword())) {
			userService.delete(request.getUserId());
		} else {
			new ResponseEntity<String>("Hatalı şifre", HttpStatus.OK);
		}
		
		return new ResponseEntity<String>(HttpStatus.OK);

	}

	@RequestMapping(value = "/user/getbyusername/{username}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> getByUserName(@PathVariable("username") String userName) {
		User user = userService.findByUser(userName);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@RequestMapping(value = "/user/isexistusername/{username}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> isExistUserName(@PathVariable("username") String userName) {
		Boolean response = true;
		User user = userService.findByUser(userName);
		if (user == null) {
			response = false;
		}
		return new ResponseEntity<Boolean>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/user/findbymail/{mail}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> findByMail(@PathVariable("mail") String s) {

		s = s + ".com";
		User mail = userService.findByMail(s);
		if (mail == null)
			return new ResponseEntity<Boolean>(false, HttpStatus.OK);
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}

	@RequestMapping(value = "/user/findbyfacebookid/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> findByFacebookId(@PathVariable("id") String facebookId) {
		User user = userService.findByFacebookId(facebookId);
		if (user == null)
			return new ResponseEntity<>(null, HttpStatus.OK);
		user.setPassword(null);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/user/findbygoogleid/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> findByGoogleId(@PathVariable("id") String googleId) {
		User user = userService.findByGoogleId(googleId);
		if (user == null)
			return new ResponseEntity<>(null, HttpStatus.OK);
		user.setPassword(null);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@RequestMapping(value = "/user/isexistmail/{mail}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> getByMail(@PathVariable("mail") String mail) {
		User u = userService.findByMail(mail);
		return new ResponseEntity<User>(u, HttpStatus.OK);
	}

	@RequestMapping(value = "/user/activation/{userid}/{token}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> setStatus(@PathVariable("token") String token, @PathVariable("userid") Long userid) {
		User user = userService.findOne(userid);
		User updatedUser;
		if (user.getToken().equals(token)) {
			user.setUserStatus(1);
		}
		updatedUser = userService.update(user);

		return new ResponseEntity<User>(updatedUser, HttpStatus.OK);
	}

	@RequestMapping(value = "/user/verifyaccount", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<User> verifyUser(@RequestBody User user) {
		User updatedUser = userService.verify(user);
		if (updatedUser == null)
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);

		return new ResponseEntity<User>(updatedUser, HttpStatus.OK);
	}

	@RequestMapping(value = "/user/changepassword", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<User> changePassword(@RequestBody RequestChangePassword request) {
		User user = userService.findByUser(request.getUsername());
		User updatedUser = null;
		if (user != null & request.getNewpassword() != null & user.getPassword().equals(request.getOldpassword())) {
			user.setPassword(request.getNewpassword());
			updatedUser = userService.update(user);
		} else
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<User>(updatedUser, HttpStatus.OK);

	}
	@RequestMapping(value = "/user/updateadmin", method = RequestMethod.PUT, consumes= MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<User> updateAdmin(@RequestBody RequestUpdateAdmin adminUser){
		
		User user = userService.findByUser(adminUser.getUsername());
		User updatedUser = null;
		
		if(user != null){
			Integer isAdmin = 1;
			user.setIsadmin(isAdmin);
			updatedUser = userService.update(user);
		}else
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<User>(updatedUser, HttpStatus.OK);
	}
	
	private static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
	    long diffInMillies = date2.getTime() - date1.getTime();
	    return timeUnit.convert(diffInMillies,TimeUnit.MILLISECONDS);
	}

}
