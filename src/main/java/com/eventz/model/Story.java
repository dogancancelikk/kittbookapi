package com.eventz.model;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Story {

	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private Integer status;
	private Long ownerID;
	private String image;
	private Integer iseditable;
	private Integer isreadable;
	private Integer isfinished;
	private Double ownerrate;
	private Double storyrate;
	private Calendar lastupdate;
	private Calendar createdate;
	private Integer readcount;
	private String description;
	private Integer isPublished;
	private Long categoryID;
	private Integer isCollective;
	private Integer commentCount;
	private Integer isActive;

	public Story() {

	}

	public Story(Stories s) {
		this.id = s.getId();
		this.name = s.getName();
		this.status = s.getStatus() == null ? 1 : s.getStatus();
		this.ownerID = s.getOwnerID();
		this.image = s.getImage();
		this.iseditable = s.getIseditable() == null ? 0 : s.getIseditable();
		this.isreadable = s.getIsreadable() == null ? 0 : s.getIsreadable();
		this.isfinished = s.getIsfinished() == null ? 0 : s.getIsfinished();
		this.isPublished = s.getIsPublished()==null?0:s.getIsPublished();
		this.ownerrate = s.getOwnerrate();
		this.storyrate = s.getStoryrate();
		this.lastupdate = s.getLastupdate();
		this.createdate = s.getCreatedate();
		this.readcount = s.getReadcount() == null ? 0 : s.getReadcount();
		this.description = s.getDescription();
		this.isCollective = s.getIsCollective() == null ? 0 : s.getIsCollective();
		this.commentCount = s.getCommentCount() == null ? 0 : s.getCommentCount();
		this.isActive = s.getIsActive() == null ? 0 : s.getIsActive();
		this.categoryID = s.getCategoryID();
	}

	public Integer getIseditable() {
		return iseditable;
	}

	public void setIseditable(Integer iseditable) {
		this.iseditable = iseditable;
	}

	public Integer getIsreadable() {
		return isreadable;
	}

	public void setIsreadable(Integer isreadable) {
		this.isreadable = isreadable;
	}

	public Integer getIsfinished() {
		return isfinished;
	}

	public void setIsfinished(Integer isfinished) {
		this.isfinished = isfinished;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getOwnerID() {
		return ownerID;
	}

	public void setOwnerID(Long ownerID) {
		this.ownerID = ownerID;
	}

	public Double getOwnerrate() {
		return ownerrate;
	}

	public void setOwnerrate(Double ownerrate) {
		this.ownerrate = ownerrate;
	}

	public Double getStoryrate() {
		return storyrate;
	}

	public void setStoryrate(Double storyrate) {
		this.storyrate = storyrate;
	}

	public Calendar getLastupdate() {
		return lastupdate;
	}

	public void setLastupdate(Calendar lastupdate) {
		this.lastupdate = lastupdate;
	}

	public Calendar getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Calendar createdate) {
		this.createdate = createdate;
	}

	public Integer getReadcount() {
		return readcount;
	}

	public void setReadcount(Integer readcount) {
		this.readcount = readcount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getIsPublished() {
		return isPublished;
	}

	public void setIsPublished(Integer isPublished) {
		this.isPublished = isPublished;
	}

	public Long getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(Long categoryID) {
		this.categoryID = categoryID;
	}

	public Integer getIsCollective() {
		return isCollective;
	}

	public void setIsCollective(Integer isCollective) {
		this.isCollective = isCollective;
	}

	public Integer getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}

	public Integer getIsActive() {
		return isActive;
	}

	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}

}
