package com.eventz.service;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventz.model.ExceptionLog;
import com.eventz.repository.ExceptionLogRepository;

@Service
public class ExceptionLogServiceImpl implements ExceptionLogService {

	@Autowired
	ExceptionLogRepository exceptionLogRepository;
	
	@Override
	public void logException(String controllerName, String methodName, String message, String request) {
		ExceptionLog log = new ExceptionLog(controllerName, methodName, message, Calendar.getInstance().getTime(), request);
		exceptionLogRepository.save(log);
	}

}
