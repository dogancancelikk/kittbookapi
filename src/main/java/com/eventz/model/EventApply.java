package com.eventz.model;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class EventApply {

	@Id
	@GeneratedValue
	private Long id;
	private Long activityID;
	private String text;
	private String title;
	private String about;
	private Long userID;
	private int status;
	private Calendar applyDate;
	private String image;
	private Integer approve;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getActivityID() {
		return activityID;
	}
	public void setActivityID(Long activityID) {
		this.activityID = activityID;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	public Long getUserID() {
		return userID;
	}
	public void setUserID(Long userID) {
		this.userID = userID;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Calendar getApplyDate() {
		return applyDate;
	}
	public void setApplyDate(Calendar applyDate) {
		this.applyDate = applyDate;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Integer getApprove() {
		return approve;
	}
	public void setApprove(Integer approve) {
		this.approve = approve;
	}
}
