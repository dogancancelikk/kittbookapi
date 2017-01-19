package com.eventz.model;

import java.io.Serializable;

public class StoryDetailId implements Serializable{

	
	private static final long serialVersionUID = -1152251598314416227L;
	private Long storyID;
	private Long chapterID;
	
	public StoryDetailId(){
		
	}
	
	public StoryDetailId(Long storyID,Long chapterID){
		this.storyID=storyID;
		this.chapterID=chapterID;
	}
	
	public boolean equals (Object o){
		return ((o instanceof StoryDetailId) && storyID.equals(((StoryDetailId) o).getStoryID())
				&& chapterID == ((StoryDetailId) o).getChapterID());
	}
	
	public int hashCode(){
		return chapterID.intValue()+storyID.intValue();
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
	
	
}
