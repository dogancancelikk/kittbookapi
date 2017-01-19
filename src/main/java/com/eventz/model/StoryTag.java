package com.eventz.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@IdClass(StoryTagId.class)
public class StoryTag {
	@Id
	private Long storyID;
	@Id
	private String tag;
	
	public StoryTag(){}
	public StoryTag(StoryTagId id)
	{
		this.storyID=id.getStoryID();
		this.tag=id.getTag();
	}
	
	public Long getStoryId() {
		return storyID;
	}
	public void setSetStoryId(Long id) {
		this.storyID = id;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
}
