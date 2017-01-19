package com.eventz.model;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.beans.BeanUtils;

@Entity
public class Event {

	@Id
	@GeneratedValue
	private Long id;
	private Integer status;
	private String name;
	private String about;
	private String image;
	private Calendar createDate;
	private Calendar startDate;
	private Calendar endDate;
	private Long collectiveBookID;
	private Integer type;
	private Long authorID;
	private Integer isActive;
	//type=1 -> kolektif yazi gonderme etkinligi
	//type=2 -> yazar kadrosu etkinligi
	//type=3 -> yazi gonderme etkinligi
	
	public Event(){}
	public Event(Event e)
	{
		BeanUtils.copyProperties(e, this);
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
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Calendar getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Calendar createDate) {
		this.createDate = createDate;
	}
	public Calendar getStartDate() {
		return startDate;
	}
	public void setStartDate(Calendar startDate) {
		this.startDate = startDate;
	}
	public Calendar getEndDate() {
		return endDate;
	}
	public void setEndDate(Calendar endDate) {
		this.endDate = endDate;
	}
	public Long getCollectiveBookID() {
		return collectiveBookID;
	}
	public void setCollectiveBookID(Long collectiveBookID) {
		this.collectiveBookID = collectiveBookID;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Long getAuthorID() {
		return authorID;
	}
	public void setAuthorID(Long authorID) {
		this.authorID = authorID;
	}
	public Integer getIsActive() {
		return isActive;
	}
	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}
	
	
}
