package com.eventz.controller;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.eventz.model.Stories;
import com.eventz.service.StoryService;

@CrossOrigin(origins = "http://kittbook.com")
@RestController
public class CollectiveBookController {

	@Autowired
	StoryService storyService;
	
	@RequestMapping(value ="/collectivebook/start/{id}", method = RequestMethod.GET,  produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Boolean> startStory(@PathVariable("id") Long id) throws ParseException {
		boolean active = storyService.startCollective(id);
		return new ResponseEntity<Boolean>(active, HttpStatus.OK);
	}
	
	@RequestMapping(value ="/collectivebook/finish/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Boolean> finishStory(@PathVariable("id") Long id) throws ParseException {
		boolean active = storyService.finishCollective(id);
		return new ResponseEntity<Boolean>(active, HttpStatus.OK);
	}
	
	@RequestMapping(value ="/collectivebook/getNotStarted", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Collection<Stories>> getNotStarted() throws ParseException {
		Collection<Stories> stories = storyService.getNotStartedCollective();
		if(stories==null)
			return new ResponseEntity<Collection<Stories>>(new ArrayList<Stories>(), HttpStatus.OK);
		return new ResponseEntity<Collection<Stories>>(stories, HttpStatus.OK);
	}
	
	@RequestMapping(value ="/collectivebook/getStarted", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Collection<Stories>> getStarted() throws ParseException {
		Collection<Stories> stories = storyService.getStartedCollective();
		if(stories==null)
			return new ResponseEntity<Collection<Stories>>(new ArrayList<Stories>(), HttpStatus.OK);
		return new ResponseEntity<Collection<Stories>>(stories, HttpStatus.OK);
	}
	
	@RequestMapping(value ="/collectivebook/getFinished", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Collection<Stories>> getFinished() throws ParseException {
		Collection<Stories> stories = storyService.getFinishedCollective();
		if(stories==null)
			return new ResponseEntity<Collection<Stories>>(HttpStatus.OK);
		return new ResponseEntity<Collection<Stories>>(stories, HttpStatus.OK);
	}
	
	
	
	
}
