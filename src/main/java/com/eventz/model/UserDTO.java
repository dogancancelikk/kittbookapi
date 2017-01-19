package com.eventz.model;

import java.util.Calendar;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

import org.springframework.beans.BeanUtils;

@Entity
public class UserDTO {

	@Id
	@GeneratedValue
	private Long id;
	
	private Integer status;
	
	@NotNull
	private String userName;
	
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	@NotNull
	private String password;
	private String name;
	private String surname;
	private Calendar birthday;
	private Integer isadmin;
	private String image;
	private String about;
	private String email;
	private Long libraryID;
	private String job;
	private String gender;
	private String school;
	private int userStatus;
	private Calendar messageTime;
	private String text;
	private Integer hasRead;
	private Long sender;
	
	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	@ManyToMany(fetch=FetchType.EAGER,
				cascade=CascadeType.ALL)
	@JoinTable(
			name="AccountRole",
			joinColumns=@JoinColumn(
					name="accountId",
					referencedColumnName="id"),
			inverseJoinColumns=@JoinColumn(
					name="roleId",
					referencedColumnName="id"))
	private Set<Role> roles;
	
	public UserDTO(){}
	
	public UserDTO(UserDTO u,String text)
	{
		BeanUtils.copyProperties(u, this);
		this.text = text;
	}
	
	public UserDTO(User u,Calendar messageTime)
	{
		BeanUtils.copyProperties(u, this);
		this.messageTime = messageTime;
	}
	
	public UserDTO(User u,Calendar messageTime,String text, Integer hasRead, Long sender)
	{
		BeanUtils.copyProperties(u, this);
		this.messageTime = messageTime;
		this.text=text;
		this.hasRead = hasRead;
		this.sender = sender;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	public Calendar getBirthday() {
		return birthday;
	}
	public void setBirthday(Calendar birthday) {
		this.birthday = birthday;
	}
	public Integer getIsadmin() {
		return isadmin;
	}
	public void setIsadmin(Integer isadmin) {
		this.isadmin = isadmin;
	}
	public Long getLibraryID() {
		return libraryID;
	}

	public void setLibraryID(Long libraryID) {
		this.libraryID = libraryID;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public int getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(int userStatus) {
		this.userStatus = userStatus;
	}

	public Calendar getMessageTime() {
		return messageTime;
	}

	public void setMessageTime(Calendar messageTime) {
		this.messageTime = messageTime;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Integer getHasRead() {
		return hasRead;
	}

	public void setHasRead(Integer hasRead) {
		this.hasRead = hasRead;
	}

	public Long getSender() {
		return sender;
	}

	public void setSender(Long sender) {
		this.sender = sender;
	}
	
	
}
