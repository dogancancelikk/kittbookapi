package com.eventz.service;

public interface ExceptionLogService {

	public void logException(String controllerName, String methodName, String message, String request);

}
