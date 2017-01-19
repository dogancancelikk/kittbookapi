package com.eventz.model;

import java.io.Serializable;

public class StoryRateId implements Serializable {

	private static final long serialVersionUID = 6660156741036273826L;
	private Long storyID;
	private Long userID;
	
	public StoryRateId(){
		
	}
	
	public StoryRateId(Long storyID,Long userID){
		this.storyID=storyID;
		this.userID=userID;
	}
	
	public boolean equals(Object o) {
		return ((o instanceof StoryRateId) && storyID.equals(((StoryRateId) o).getStoryID())
				&& userID == ((StoryRateId) o).getUserID());
	}

	public int hashCode() {
		return storyID.intValue() + userID.intValue();
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
	
	
}
