package com.eventz.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@IdClass(ChapterTagId.class)
public class ChapterTag {

	@Id
	private Long chapterID;
	@Id
	private String tag;
	
	public ChapterTag(){}
	
	public ChapterTag(ChapterTagId id)
	{
		this.chapterID = id.getChapterID();
		this.tag = id.getTag();
	}
	
	public Long getChapterID() {
		return chapterID;
	}
	public void setChapterID(Long chapterID) {
		this.chapterID = chapterID;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	
	
}
