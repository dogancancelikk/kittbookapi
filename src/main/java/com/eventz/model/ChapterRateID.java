package com.eventz.model;

import java.io.Serializable;

public class ChapterRateID implements Serializable {

	private static final long serialVersionUID = 3939888175519818593L;
	private Long chapterID;
	private Long userID;
	
	public ChapterRateID(){
		
	}
	
	public ChapterRateID(Long chapterID,Long userID){
		this.chapterID=chapterID;
		this.userID=userID;
	}
	
	public ChapterRateID(ChapterRate rate)
	{
		this.chapterID=rate.getChapterID();
		this.userID=rate.getUserID();
	}
	
	public boolean equals(Object o) {
		return ((o instanceof ChapterRateID) && chapterID.equals(((ChapterRateID) o).getChapterID())
				&& userID == ((ChapterRateID) o).getUserID());
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
