package com.eventz.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ExceptionLog {

	@Id
	@GeneratedValue
	private Long id;
	private String controllerName;
	private String methodName;
	private String message;
	private Date createDate;
	private String request;

	public ExceptionLog() {
		super();
	}

	public ExceptionLog(String controllerName, String methodName, String message, Date createDate, String request) {
		super();
		this.controllerName = controllerName;
		this.methodName = methodName;
		this.message = message;
		this.createDate = createDate;
		this.setRequest(request);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getControllerName() {
		return controllerName;
	}

	public void setControllerName(String controllerName) {
		this.controllerName = controllerName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

}
