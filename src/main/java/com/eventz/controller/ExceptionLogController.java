package com.eventz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.eventz.model.ExceptionLog;
import com.eventz.service.ExceptionLogService;

@CrossOrigin(origins = "http://kittbook.com")
@RestController
public class ExceptionLogController {
	
	@Autowired
	ExceptionLogService exceptionLogService;
	
	@RequestMapping(value = "exception/log", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void logException(@RequestBody ExceptionLog exception) {
		exceptionLogService.logException(exception.getControllerName(), exception.getMethodName(), exception.getMessage(), exception.getRequest());
	}

}
