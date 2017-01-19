package com.eventz.model;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class CommentDTO {
	@Id
	@GeneratedValue
	private Long id;
	private String text;
	private Integer typeID;
	private Long userID;
	private Long parentID;
	private Calendar createTime;
	private Calendar updateTime;
	private Integer status;
	private String userName;
	private String userImage;
	
	
	public CommentDTO(Comments c,String userName,String userImage)
	{
		this.id=c.getId();
		this.text=c.getText();
		this.typeID=c.getTypeID();
		this.userID=c.getUserID();
		this.parentID=c.getParentID();
		this.createTime=c.getCreateTime();
		this.updateTime=c.getUpdateTime();
		this.status=c.getStatus();
		this.userName=userName;
		this.userImage=userImage;
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Integer getTypeID() {
		return typeID;
	}
	public void setTypeID(Integer typeID) {
		this.typeID = typeID;
	}
	public Long getUserID() {
		return userID;
	}
	public void setUserID(Long userID) {
		this.userID = userID;
	}
	public Long getParentID() {
		return parentID;
	}
	public void setParentID(Long parentID) {
		this.parentID = parentID;
	}
	public Calendar getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Calendar createTime) {
		this.createTime = createTime;
	}
	public Calendar getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Calendar updateTime) {
		this.updateTime = updateTime;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserImage() {
		return userImage;
	}
	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}
	
	

}
