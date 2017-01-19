package com.eventz.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.GenerationType;

@Entity
public class Complain {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;	
	private String title;
	private String message;
	private Long storyid;
	private Long userid;
	private Long commentid;
	private Long postid;
	private Long creatorid;	
	
	
	public Long getPostid() {
		return postid;
	}
	public void setPostid(Long postid) {
		this.postid = postid;
	}
	public Long getCreatorid() {
		return creatorid;
	}
	public void setCreatorid(Long creatorid) {
		this.creatorid = creatorid;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Long getStoryid() {
		return storyid;
	}
	public void setStoryid(Long storyid) {
		this.storyid = storyid;
	}
	public Long getUserid() {
		return userid;
	}
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	public Long getCommentid() {
		return commentid;
	}
	public void setCommentid(Long commentid) {
		this.commentid = commentid;
	}
	
	
 
}
