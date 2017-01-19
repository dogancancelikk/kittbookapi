package com.eventz.model;

import java.io.Serializable;

public class ChapterReadId implements Serializable {

	private static final long serialVersionUID = 8047406906824176912L;
	private Long chapterID;
	private Long userID;
	
	public ChapterReadId(){
		
	}
	
	public ChapterReadId(Long chapterID,Long userID){
		this.chapterID=chapterID;
		this.userID=userID;
	}
	
	public boolean equals(Object o) {
		return ((o instanceof ChapterReadId) && chapterID.equals(((ChapterReadId) o).getChapterID())
				&& userID == ((ChapterReadId) o).getUserID());
	}

	public int hashCode() {
		return chapterID.intValue() + userID.intValue();
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
