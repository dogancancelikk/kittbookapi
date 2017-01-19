package com.eventz.response;

import java.util.Date;

public class ResponseActivity {
	
	private Long id;
	private Long activityUserID;
	private String activityUserName;
	private String text;
	private int activityType;
	private Date createdate;
	private String activityDetailLink;
	private String username; //followed user
	
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
	public String getActivityUserName() {
		return activityUserName;
	}
	public void setActivityUserName(String activityUserName) {
		this.activityUserName = activityUserName;
	}
	public int getActivityType() {
		return activityType;
	}
	public void setActivityType(int activityType) {
		this.activityType = activityType;
	}
	public Date getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getActivityDetailLink() {
		return activityDetailLink;
	}
	public void setActivityDetailLink(String activityDetailLink) {
		this.activityDetailLink = activityDetailLink;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

}
