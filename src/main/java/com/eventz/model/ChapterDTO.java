package com.eventz.model;

import java.util.Calendar;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ChapterDTO {
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
	private String storyName;
	private Double rate;
	private Calendar createDate;
	private Calendar updateDate;
	private Integer readCount;
	private Integer commentCount;


	@Column
    @ElementCollection(targetClass=String.class)
	private Collection<String> tags;
	
	public ChapterDTO() {}
	
	public ChapterDTO(Chapter c,Story s)
	{
		this.id = c.getId();
		this.status=c.getStatus();
		this.name = c.getName();
		this.chapterstatus=c.getChapterstatus();
		this.storyID=c.getStoryID();
		this.userID=c.getUserID();
		this.text=c.getText();
		this.image=c.getImage();
		this.chapterNumber=c.getChapterNumber();
		this.rate=c.getRate();
		this.createDate=c.getCreateDate();
		this.updateDate=c.getUpdateDate();
		this.readCount=c.getReadCount();
		this.commentCount=c.getCommentCount();
		this.storyName=s.getName();
	}
	
	public ChapterDTO(Long id,int status,String name,String chapterstatus,
			Long storyID,Long userID,String text,String image,Integer chapterNumber
			,String storyName) {
		super();
		this.id=id;
		this.status=status;
		this.name=name;
		this.chapterstatus=chapterstatus;
		this.storyID=storyID;
		this.userID=userID;
		this.text=text;
		this.image=image;
		this.chapterNumber=chapterNumber;
		this.storyName=storyName;
	}
	
	public ChapterDTO(Chapter c,Collection<String> tags,String storyName)
	{
		this.id = c.getId();
		this.status=c.getStatus();
		this.name = c.getName();
		this.chapterstatus=c.getChapterstatus();
		this.storyID=c.getStoryID();
		this.userID=c.getUserID();
		this.text=c.getText();
		this.image=c.getImage();
		this.chapterNumber=c.getChapterNumber();
		this.rate=c.getRate();
		this.createDate=c.getCreateDate();
		this.updateDate=c.getUpdateDate();
		this.readCount=c.getReadCount();
		this.commentCount=c.getCommentCount();
		this.tags = tags;
		this.storyName = storyName;
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

	public String getStoryName() {
		return storyName;
	}

	public void setStoryName(String storyName) {
		this.storyName = storyName;
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

	public Collection<String> getTags() {
		return tags;
	}

	public void setTags(Collection<String> tags) {
		this.tags = tags;
	}
	
	
}
