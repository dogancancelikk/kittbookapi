package com.eventz.model;

public class NotificationDTO {

	private String username;
	private String text;
	private Integer notificationType;
	private Integer hasRead;
	private String sendBy;

	public NotificationDTO() {
	}

	public NotificationDTO(String username, String text, Integer notificationType, Integer hasRead, String sendBy) {
		this.username = username;
		this.text = text;
		this.notificationType = notificationType;
		this.hasRead = hasRead;
		this.sendBy = sendBy;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
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
