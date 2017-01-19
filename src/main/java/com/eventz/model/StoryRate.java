package com.eventz.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@IdClass(StoryRateId.class)
public class StoryRate {
	@Id
	private Long storyID;
	@Id
	private Long userID;
	private Integer rate;
	
	public StoryRate(){
		
	}
	
	
	public Long getStoryID() {
		return storyID;
	}
	public void setStoryID(Long storyID) {
		this.storyID = storyID;
	}
	public Long getUserID() {
		return userID;
	}
	public void setUserID(Long userID) {
		this.userID = userID;
	}
	public Integer getRate() {
		return rate;
	}
	public void setRate(Integer rate) {
		this.rate = rate;
	}
	
	
	
}
