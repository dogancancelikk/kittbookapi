package com.eventz.model;

import java.io.Serializable;

public class ChapterTagId implements Serializable {

	private static final long serialVersionUID = 8056235866449107031L;
	private Long chapterID;
	private String tag;
	
	public ChapterTagId(){}
	
	public ChapterTagId(Long chapterID,String tag){
		super();
		this.chapterID = chapterID;
		this.tag = tag;
	}
	
	public boolean equals(Object o) {
		return ((o instanceof ChapterTagId) && chapterID.equals(((ChapterTagId) o).getChapterID())
				&& tag == ((ChapterTagId) o).getTag());
	}

	public int hashCode() {
		return chapterID.intValue() + Integer.parseInt(tag);
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
