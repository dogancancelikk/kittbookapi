package com.eventz.model;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@IdClass(StoryReadId.class)
public class StoryRead {
	@Id
	private Long userID;
	@Id
	private Long storyID;
	private Calendar readTime;

	public StoryRead() {
	}

	public StoryRead(StoryReadId id) {
		this.userID = id.getUserID();
		this.storyID = id.getStoryID();
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

	public Calendar getReadTime() {
		return readTime;
	}

	public void setReadTime(Calendar readTime) {
		this.readTime = readTime;
	}

}
