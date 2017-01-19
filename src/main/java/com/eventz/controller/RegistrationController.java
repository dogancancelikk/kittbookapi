package com.eventz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.eventz.model.RequestInvitationDTO;
import com.eventz.model.User;
import com.eventz.service.EmailService;
import com.eventz.service.UserService;

@RestController
public class RegistrationController {
	
		@Autowired
		private EmailService emailService;
		
		@Autowired 
		private UserService userService;
		
		@RequestMapping("/signup")
		public String signup(){
			return "Please sign up for our service";
		}
		
		@RequestMapping(value= "/forgotpassword/{username}", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
		public String signupsuccess(@PathVariable("username") String username){
			User user= userService.findByUser(username);				
			//send email
			try{
				emailService.sendNotification(user);
				
			}catch(MailException e){
				//catch error				
			}			
			return "Thank you for registering with us";
		}
		
		@RequestMapping(value = "/sendInvitation", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
		@ResponseBody
		public void sendInvitationMail(@RequestBody RequestInvitationDTO requestInvitationDTO) throws Exception {
			User user = userService.findByUser(requestInvitationDTO.getUsername());
			try{
				emailService.sendInvitationMail(user, requestInvitationDTO.getEmailAdress());
			}catch (Exception e) {
				throw new Exception(e.getMessage());
			}
		}
}
