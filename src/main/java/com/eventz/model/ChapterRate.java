package com.eventz.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@IdClass(ChapterRateID.class)
public class ChapterRate {
	@Id
	private Long chapterID;
	@Id
	private Long userID;
	private int rate;
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
	public int getRate() {
		return rate;
	}
	public void setRate(int rate) {
		this.rate = rate;
	}
	
	
}
