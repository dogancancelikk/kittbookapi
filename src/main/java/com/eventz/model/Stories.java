package com.eventz.model;

import java.util.Calendar;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.beans.BeanUtils;


@Entity
public class Stories {
	
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private Integer status;
	private Long ownerID;
	private String image;
	private String username;
	private String nameSurname;
	private Integer iseditable;
	private Integer isreadable;
	private Integer isfinished;
	private Integer isPublished;
	private String description;
	private Integer readcount;
	private Double ownerrate;
	private Double storyrate;
	private Integer commentCount;
	private Calendar lastupdate;
	private Calendar createdate;
	private Integer isActive;
	private Integer isCollective;
	private Long categoryID;
	private String userImage;
	@Column
    @ElementCollection(targetClass=String.class)
	private Collection<String> tags;
	@Column
    @ElementCollection(targetClass=Long.class)
	private Collection<Long> authors;
	@Column
    @ElementCollection(targetClass=Category.class)
	private Collection<Category> categories;
	
	public Stories(Long id, String name, Integer status, Long ownerID, String image, String username,
			int iseditable, int isreadable, int isfinished,String description,Integer readcount,Double ownerrate,
			Double storyrate,Calendar lastupdate,Calendar createdate,Integer commentCount,Collection<String> tags,Collection<Long> authors,
			Integer isActive,Integer isCollective,Long categoryID) {
		this.id = id;
		this.name = name;
		this.status = status;
		this.ownerID = ownerID;
		this.image = image;
		this.username = username;
		this.iseditable = iseditable;
		this.isreadable = isreadable;
		this.isfinished = isfinished;
		this.description=description;
		this.readcount=readcount;
		this.ownerrate=ownerrate;
		this.storyrate=storyrate;
		this.lastupdate=lastupdate;
		this.createdate=createdate;
		this.commentCount=commentCount;
		this.tags=tags;
		this.authors=authors;
		this.isActive =isActive;
		this.isCollective=isCollective;
		this.categoryID = categoryID;
	}
	
	public Stories(Story story, String username, Collection<Category> categories,Collection<String> tags) {
		this.nameSurname = username;
		this.categories = categories;
		BeanUtils.copyProperties(story, this);
		this.tags=tags;
	}
	
	public Stories(Story story,Collection<String> tags, String username) {
		this.username = username;
		BeanUtils.copyProperties(story, this);
		this.tags=tags;
	}
	
	public Stories(Story story,Collection<String> tags) {
		BeanUtils.copyProperties(story, this);
		this.tags=tags;
	}
	
	public Stories(Stories story,Collection<String> tags) {
		BeanUtils.copyProperties(story, this);
		this.tags=tags;
	}
	
	public Stories(Story story, String username) {
		BeanUtils.copyProperties(story, this);
		this.nameSurname = username;
	}
	public Stories(Story story, String username,String userimage) {
		BeanUtils.copyProperties(story, this);
		this.nameSurname = username;
		this.userImage = userimage;
	}
	
	public Stories(Story story,User user, Collection<Category> categories,Collection<String> tags)
	{
		BeanUtils.copyProperties(story, this);
		this.nameSurname = user.getName() + " " + user.getSurname();
		this.userImage = user.getImage();	
		this.username = user.getUserName();
		this.categories = categories;
		this.tags = tags;
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

	public Stories(){
		
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Collection<Category> getCategories() {
		return categories;
	}

	public void setCategories(Collection<Category> categories) {
		this.categories = categories;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getReadcount() {
		return readcount;
	}

	public void setReadcount(Integer readcount) {
		this.readcount = readcount;
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

	public Long getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(Long categoryID) {
		this.categoryID = categoryID;
	}

	public Collection<Long> getAuthors() {
		return authors;
	}

	public void setAuthors(Collection<Long> authors) {
		this.authors = authors;
	}

	public Integer getIsActive() {
		return isActive;
	}

	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}

	public Integer getIsCollective() {
		return isCollective;
	}

	public void setIsCollective(Integer isCollective) {
		this.isCollective = isCollective;
	}

	public Integer getIsPublished() {
		return isPublished;
	}

	public void setIsPublished(Integer isPublished) {
		this.isPublished = isPublished;
	}

	public String getUserImage() {
		return userImage;
	}

	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}

	public String getNameSurname() {
		return nameSurname;
	}

	public void setNameSurname(String nameSurname) {
		this.nameSurname = nameSurname;
	} 
	
	
}
