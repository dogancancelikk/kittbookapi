package com.eventz.model;

import java.io.Serializable;

public class StoryReadId implements Serializable {

	
	private static final long serialVersionUID = -3092574803172423684L;
	private Long userID;
	private Long storyID;
	
	public StoryReadId(){}
	public StoryReadId(Long userID,Long storyID)
	{
		this.userID=userID;
		this.storyID=storyID;
	}
	
	public boolean equals(Object o){
		return ((o instanceof StoryReadId) && storyID.equals(((StoryRateId) o).getStoryID())
				&& userID == ((StoryReadId) o).getUserID());
	}
	
	public int hashCode() {
		return storyID.intValue() + userID.intValue();
	}
	
	public Long getUserID() {
		return userID;
	}
	public void setUserID(Long userID) {
		this.userID = userID;
	}
	public Long getStoryID() {
		return storyID;
	}
	public void setStoryID(Long storyID) {
		this.storyID = storyID;
	}
	
	
	
	

}
