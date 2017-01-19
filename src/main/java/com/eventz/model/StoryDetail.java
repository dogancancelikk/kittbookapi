package com.eventz.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@IdClass(StoryDetailId.class)
public class StoryDetail {
	
	@Id
	private Long storyID;
	@Id
	private Long chapterID;
	private Long chapterNumber;
	
	public StoryDetail(){
		
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


	public long getChapterNumber() {
		return chapterNumber;
	}
	public void setChapterNumber(long chapterNumber) {
		this.chapterNumber = chapterNumber;
	}
	
	

}
