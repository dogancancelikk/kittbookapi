package com.eventz.model;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class MessageDTO {

	@Id
	@GeneratedValue
	private Long id;
	private Long sender;
	private Long receiver;
	private Long user;
	private Long other;
	private Calendar messageTime;
	private String text;
	private Integer isDeleteSender;
	private Integer isDeleteReceiver;
	private Integer read;

	public MessageDTO() {
	}
	public MessageDTO(Message m){
		this.id = m.getId();
		this.sender = m.getSender();
		this.receiver = m.getReceiver();
		this.messageTime = m.getMessageTime();
		this.text=m.getText();
		this.isDeleteSender=m.getIsDeleteSender();
		this.isDeleteReceiver=m.getIsDeleteReceiver();
		this.read = m.getRead();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSender() {
		return sender;
	}

	public void setSender(Long sender) {
		this.sender = sender;
	}

	public Long getReceiver() {
		return receiver;
	}

	public void setReceiver(Long receiver) {
		this.receiver = receiver;
	}

	public Calendar getMessageTime() {
		return messageTime;
	}

	public void setMessageTime(Calendar messageTime) {
		this.messageTime = messageTime;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Integer getIsDeleteSender() {
		return isDeleteSender;
	}

	public void setIsDeleteSender(Integer isDeleteSender) {
		this.isDeleteSender = isDeleteSender;
	}

	public Integer getIsDeleteReceiver() {
		return isDeleteReceiver;
	}

	public void setIsDeleteReceiver(Integer isDeleteReceiver) {
		this.isDeleteReceiver = isDeleteReceiver;
	}

	public Long getUser() {
		return user;
	}

	public void setUser(Long user) {
		this.user = user;
	}

	public Long getOther() {
		return other;
	}

	public void setOther(Long other) {
		this.other = other;
	}
	public Integer getRead() {
		return read;
	}
	public void setRead(Integer read) {
		this.read = read;
	}
	
	

}
