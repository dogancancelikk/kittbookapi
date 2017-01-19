package com.eventz.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@IdClass(StoryAuthorId.class)
public class StoryAuthor {

	@Id
	private Long storyID;
	@Id
	private Long userID;
	private Integer status;
	
	public StoryAuthor(){}
	
	public StoryAuthor(StoryAuthorId id)
	{
		this.storyID = id.getStoryID();
		this.userID = id.getUserID();
		this.status=1;
	}
	
	public StoryAuthor(Long sid,Long uid)
	{
		this.storyID = sid;
		this.userID = uid;
		this.status=1;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
}
