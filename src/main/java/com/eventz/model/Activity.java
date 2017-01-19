package com.eventz.model;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Activity {

	@Id
	@GeneratedValue
	private Long id;
	private Long activityUserID;
	private Long storyID;
	private Long chapterID;
	private Long userID;
	private Long postID;
	private int activityType;
	private Calendar lastupdate;
	private Date createdate;
	private String username; //followed username
	//story -> activityType=1
	//collectivebook -> activityType=2
	//followedUserID -> activityType=3
	//post -> activityType=4
	//chapter -> activityType=5
		
	public Long getPostID() {
		return postID;
	}
	public void setPostID(Long postID) {
		this.postID = postID;
	}
	public Date getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getActivityUserID() {
		return activityUserID;
	}
	public void setActivityUserID(Long activityUserID) {
		this.activityUserID = activityUserID;
	}
	public Long getStoryID() {
		return storyID;
	}
	public void setStoryID(Long storyID) {
		this.storyID = storyID;
	}
	public Long getChapterID() {
		return chapterID;
	}
	public void setChapterID(Long chapterID) {
		this.chapterID = chapterID;
	}
	public Long getUserID() {
		return userID;
	}
	public void setUserID(Long userID) {
		this.userID = userID;
	}
	public int getActivityType() {
		return activityType;
	}
	public void setActivityType(int activityType) {
		this.activityType = activityType;
	}
	public Calendar getLastupdate() {
		return lastupdate;
	}
	public void setLastupdate(Calendar lastupdate) {
		this.lastupdate = lastupdate;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	
}
