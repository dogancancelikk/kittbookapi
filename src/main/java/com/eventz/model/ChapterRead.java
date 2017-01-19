package com.eventz.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@IdClass(ChapterReadId.class)
public class ChapterRead {
	@Id
	private Long chapterID;
	@Id
	private Long userID;
	
	public ChapterRead(){}
	
	public ChapterRead(ChapterReadId id)
	{
		this.chapterID=id.getChapterID();
		this.userID=id.getUserID();
	}
	
	public Long getChapterID() {
		return chapterID;
	}
	public void setChapterID(Long chapterID) {
		this.chapterID = chapterID;
	}
	public Long getUserID() {
		return userID;
	}
	public void setUserID(Long userID) {
		this.userID = userID;
	}
	
	

}
