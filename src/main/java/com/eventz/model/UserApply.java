package com.eventz.model;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class UserApply {

	@Id
	@GeneratedValue
	private Long id;
	private String userName;
	private String image;
	private String name;
	private String surname;
	private String mail;
	private Integer approve;
	private Long applicationID;
	private Calendar applicationDate;
	
	public UserApply(User u,EventApply a)
	{
		this.id = u.getId();
		this.userName = u.getUserName();
		this.image = u.getImage();
		this.name = u.getName();
		this.surname = u.getSurname();
		this.mail = u.getEmail();
		this.approve = a.getApprove();
		this.applicationID = a.getId();
		this.applicationDate = a.getApplyDate();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Integer getApprove() {
		return approve;
	}

	public void setApprove(Integer approve) {
		this.approve = approve;
	}

	public Long getApplicationID() {
		return applicationID;
	}

	public void setApplicationID(Long applicationID) {
		this.applicationID = applicationID;
	}

	public Calendar getApplicationDate() {
		return applicationDate;
	}

	public void setApplicationDate(Calendar applicationDate) {
		this.applicationDate = applicationDate;
	}
	
	
}
