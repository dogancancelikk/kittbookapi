package com.eventz.model;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Chapter {

	@Id
	@GeneratedValue
	private Long id;
	private Integer status;
	private String name;
	private String chapterstatus;
	private Long storyID;
	private Long userID;
	private String text;
	private String image;
	private Integer chapterNumber;
	private Double rate;
	private Calendar createDate;
	private Calendar updateDate;
	private Integer readCount;
	private Integer commentCount;

	public Chapter() {

	}
	
	public Chapter(ChapterDTO dto) {
		this.id = dto.getId();
		this.status = dto.getStatus()==null?1:dto.getStatus();
		this.name = dto.getName();
		this.chapterstatus=dto.getChapterstatus()==null?"U":dto.getChapterstatus();
		this.storyID=dto.getStoryID();
		this.userID = dto.getUserID();
		this.text = dto.getText();
		this.image = dto.getImage();
		this.chapterNumber = dto.getChapterNumber();
		this.rate = dto.getRate();
		this.createDate = dto.getCreateDate();
		this.updateDate = dto.getUpdateDate();
		this.readCount=dto.getReadCount()==null ? 0 : dto.getReadCount();
		this.commentCount = dto.getCommentCount()==null ? 0 : dto.getCommentCount();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getChapterstatus() {
		return chapterstatus;
	}

	public void setChapterstatus(String chapterstatus) {
		this.chapterstatus = chapterstatus;
	}

	public Long getStoryID() {
		return storyID;
	}

	public void setStoryID(Long storyID) {
		this.storyID = storyID;
	}

	public Long getUserID() {
		return userID;
	}

	public void setUserID(Long userID) {
		this.userID = userID;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Integer getChapterNumber() {
		return chapterNumber;
	}

	public void setChapterNumber(Integer chapterNumber) {
		this.chapterNumber = chapterNumber;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public Calendar getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Calendar createDate) {
		this.createDate = createDate;
	}

	public Calendar getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Calendar updateDate) {
		this.updateDate = updateDate;
	}

	public Integer getReadCount() {
		return readCount;
	}

	public void setReadCount(Integer readCount) {
		this.readCount = readCount;
	}

	public Integer getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}
	
	
}
