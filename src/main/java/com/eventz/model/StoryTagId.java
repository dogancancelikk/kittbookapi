package com.eventz.model;

import java.io.Serializable;

public class StoryTagId implements Serializable {

	private static final long serialVersionUID = 47331759164634142L;
	private Long storyID;
	private String tag;
	
	public StoryTagId(Long storyID, String tag) {
		super();
		this.storyID = storyID;
		this.tag = tag;
	}

	public StoryTagId() {

	}

	public boolean equals(Object o) {
		return ((o instanceof StoryTagId) && storyID.equals(((StoryTagId) o).getStoryID())
				&& tag == ((StoryTagId) o).getTag());
	}

	public int hashCode() {
		return storyID.intValue() + Integer.parseInt(tag);
	}

	public Long getStoryID() {
		return storyID;
	}

	public void setStoryID(Long storyID) {
		this.storyID = storyID;
	}

	public String getTag() {
		return tag;
	}

	public void setChapterID(String tag) {
		this.tag = tag;
	}

}
