package com.eventz.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ActivityDTO {

	@Id
	@GeneratedValue
	private Long id;
	private String activityUserName;
	private String storyName;
	private String chapterName;
	private String userName;
	private int activityType;
	
	public ActivityDTO(){
		
	}
	
	public ActivityDTO(Long id,String activityUserName,String storyName,String chapterName,String userName,int activityType){
		super();
		this.id=id;
		this.activityUserName=activityUserName;
		this.storyName=storyName;
		this.chapterName=chapterName;
		this.userName = userName;
		this.activityType=activityType;
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getActivityUserName() {
		return activityUserName;
	}
	public void setActivityUserName(String activityUserName) {
		this.activityUserName = activityUserName;
	}
	public String getStoryName() {
		return storyName;
	}
	public void setStoryName(String storyName) {
		this.storyName = storyName;
	}
	public String getChapterName() {
		return chapterName;
	}
	public void setChapterName(String chapterName) {
		this.chapterName = chapterName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getActivityType() {
		return activityType;
	}
	public void setActivityType(int activityType) {
		this.activityType = activityType;
	}
	
	
}
