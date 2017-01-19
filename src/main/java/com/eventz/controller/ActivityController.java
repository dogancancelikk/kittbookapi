package com.eventz.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.eventz.model.Activity;
import com.eventz.response.ResponseActivity;
import com.eventz.service.ActivityService;

@CrossOrigin(origins = "http://kittbook.com")
@RestController
public class ActivityController {
	
	@Autowired
	ActivityService activityService;
	
	
	@RequestMapping(value = "activity/get", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Activity>> getAllActivity(){
		Collection<Activity> activity = activityService.getAllActivity();
		if (activity==null)
			return new ResponseEntity<Collection<Activity>>(HttpStatus.NOT_FOUND);
			
		return new ResponseEntity<Collection<Activity>>(activity,HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "activity/getuseractivity/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<ResponseActivity>> getOneStory(@PathVariable("id") Long id){
		Collection<ResponseActivity> activity = activityService.getUserActivies(id);
		if (activity==null)
			return new ResponseEntity<Collection<ResponseActivity>>(HttpStatus.NOT_FOUND);
			
		return new ResponseEntity<Collection<ResponseActivity>>(activity,HttpStatus.OK);
		
	}
	@RequestMapping(value = "activity/getuserlastactivity/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<ResponseActivity>> getLastActivities(@PathVariable("id") Long id){
		Collection<ResponseActivity> activity = activityService.getUserActivies(id);
		if (activity==null)
			return new ResponseEntity<Collection<ResponseActivity>>(HttpStatus.NOT_FOUND);
			
		return new ResponseEntity<Collection<ResponseActivity>>(activity,HttpStatus.OK);
	}

}
