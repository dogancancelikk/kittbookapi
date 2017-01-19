package com.eventz.model;

import java.io.Serializable;

public class StoryAuthorId implements Serializable {

	private static final long serialVersionUID = 1364819527523664791L;
	private Long storyID;
	private Long userID;
	
	public StoryAuthorId(){};
	
	public StoryAuthorId(StoryAuthor a)
	{
		this.storyID = a.getStoryID();
		this.userID = a.getUserID();
	}
	
	public StoryAuthorId(Long storyID,Long userID)
	{
		this.storyID = storyID;
		this.userID = userID;
	}
	
	public boolean equals (Object o){
		return ((o instanceof StoryAuthorId) && storyID.equals(((StoryAuthorId) o).getStoryID())
				&& userID == ((StoryAuthorId) o).getUserID());
	}
	
	public int hashCode(){
		return storyID.intValue()+userID.intValue();
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
