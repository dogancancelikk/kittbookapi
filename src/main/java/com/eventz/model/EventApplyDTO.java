package com.eventz.model;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.beans.BeanUtils;

@Entity
public class EventApplyDTO {

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
	private String eventName;
	private String eventImage;
	private Integer evenType;
	
	public EventApplyDTO(){}
	
	public EventApplyDTO(Event e,EventApply a)
	{
		BeanUtils.copyProperties(a, this);
		this.eventName=e.getName();
		this.eventImage =e.getImage();
		this.evenType=e.getType();
	}

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

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getEventImage() {
		return eventImage;
	}

	public void setEventImage(String eventImage) {
		this.eventImage = eventImage;
	}

	public Integer getEvenType() {
		return evenType;
	}

	public void setEvenType(Integer evenType) {
		this.evenType = evenType;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	
}
