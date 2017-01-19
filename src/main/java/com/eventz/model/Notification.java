package com.eventz.model;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Notification {

	@Id
	@GeneratedValue
	private Long id;
	private Calendar createDate;
	private String text;
	private String username;
	private Integer notificationType;
	private Integer hasRead;
	private String sendBy;
	//type=1  ->  x kullancisi seni takip etti
	//type=2  -> x etkinligine basvurunuz kabul edildi

	public Notification(Long id, Calendar createDate, String text, String username, Integer notificationType, Integer hasRead, String sendBy) {
		this.id = id;
		this.createDate = createDate;
		this.text = text;
		this.username = username;
		this.notificationType = notificationType;
		this.hasRead = hasRead;
		this.sendBy = sendBy;
	}

	public Notification() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Calendar getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Calendar createDate) {
		this.createDate = createDate;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getNotificationType() {
		return notificationType;
	}

	public void setNotificationType(Integer notificationType) {
		this.notificationType = notificationType;
	}

	public Integer getHasRead() {
		return hasRead;
	}

	public void setHasRead(Integer isRead) {
		this.hasRead = isRead;
	}

	public String getSendBy() {
		return sendBy;
	}

	public void setSendBy(String sendBy) {
		this.sendBy = sendBy;
	}

}
