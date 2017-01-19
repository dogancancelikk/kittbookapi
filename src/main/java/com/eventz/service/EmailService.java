package com.eventz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.eventz.model.User;

@Service
public class EmailService {

		private JavaMailSender javaMailSender;
		
		@Autowired
		public EmailService(JavaMailSender javaMailSender){
			this.javaMailSender=javaMailSender;
		}
		
		public void sendNotification(User user) throws MailException{
			SimpleMailMessage mail = new SimpleMailMessage();
			mail.setTo(user.getEmail());
			mail.setFrom("info@kittbook.com");
			mail.setSubject("Şifre Bildirim Servisi");
			mail.setText("Şifreniz: " + user.getPassword());
			
			javaMailSender.send(mail);
		}
		
		public void sendActivationMail(User user, String token) throws MailException{
			SimpleMailMessage mail = new SimpleMailMessage();
			mail.setTo(user.getEmail());
			mail.setFrom("info@kittbook.com");
			mail.setSubject("Aktivasyon Maili");
			mail.setText("Activasyon Linkiniz: " + "www.kittbook.com/#/activation/"+user.getId()+"/"+token);
			javaMailSender.send(mail);
		}
		
		public void sendInvitationMail(User user, String mailAdress) throws MailException{
			SimpleMailMessage mail = new SimpleMailMessage();
			mail.setTo(mailAdress);
			mail.setFrom("info@kittbook.com");
			mail.setSubject("Kittbook davet maili");
			mail.setText(user.getName() + " " + user.getSurname() + "adlı kullanıcıdan kittbook.com a davet aldınız. Hadi yine iyisiniz sizi seven dostlarınız var");
			javaMailSender.send(mail);
		}
}
