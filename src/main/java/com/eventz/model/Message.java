package com.eventz.model;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Message {

	@Id
	@GeneratedValue
	private Long id;
	private Long sender;
	private Long receiver;
	private Calendar messageTime;
	private String text;
	private Integer isDeleteSender;
	private Integer isDeleteReceiver;
	private Integer readByReceiver;

	public Message() {
	}

	public Message(MessageDTO m) {
		this.id=m.getId();
		this.sender=m.getSender();
		this.receiver=m.getReceiver();
		this.messageTime=Calendar.getInstance();
		this.text=m.getText();
		this.isDeleteReceiver=m.getIsDeleteReceiver()==null?0:m.getIsDeleteReceiver();
		this.isDeleteSender=m.getIsDeleteSender()==null?0:m.getIsDeleteReceiver();
		this.readByReceiver = 0;
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

	public Integer getRead() {
		return readByReceiver;
	}

	public void setRead(Integer read) {
		this.readByReceiver = read;
	}
	
	

}
