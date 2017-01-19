	package com.eventz.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.eventz.model.Event;
import com.eventz.service.EventService;

@CrossOrigin(origins = "http://kittbook.com")
@RestController
public class EventController {
	@Autowired
	EventService eventService;
	
	@RequestMapping(value = "event/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Event> createEvent(@RequestBody Event event) throws ParseException {
		Event createdEvent = eventService.create(event);
		if(createdEvent==null)
			return new ResponseEntity<Event>(HttpStatus.NOT_FOUND);

		return new ResponseEntity<Event>(createdEvent, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "event/update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Event> updateEvent(@RequestBody Event event) throws ParseException {
		Event updatedEvent = eventService.update(event);
		if (updatedEvent == null)
			return new ResponseEntity<Event>(HttpStatus.NOT_FOUND);

		return new ResponseEntity<Event>(updatedEvent, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/event/get/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Event> getEvent(@PathVariable("id") Long id) {
		Event event = eventService.findOne(id);
		if(event==null)
			return new ResponseEntity<Event>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<Event>(event, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/event/get", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Event>> getAllEvents() {
		Collection<Event> events = eventService.find();
		if(events==null)
			return new ResponseEntity<Collection<Event>>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<Collection<Event>>(events, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/event/getbytype/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Event>> getEventsByType(@PathVariable("id") Integer id) {
		Collection<Event> events = eventService.findByType(id);
		if(events==null)
			return new ResponseEntity<Collection<Event>>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<Collection<Event>>(events, HttpStatus.OK);
	}
	
	@RequestMapping(value = "event/inactivate", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Event> inactivateEvent(@RequestBody Event event) throws ParseException {
		Event e = eventService.inActivate(event);

		return new ResponseEntity<Event>(e, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/event/getapplycount/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Integer> getEventApplyCount(@PathVariable("id") Long id) {
		return new ResponseEntity<Integer>(eventService.getApplyCount(id),HttpStatus.OK);
	}
	
	@RequestMapping(value = "/event/getactives", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Event>> getActiveEvents() {
		Collection<Event> events = eventService.getActives();
		if(events==null)
			return new ResponseEntity<Collection<Event>>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<Collection<Event>>(events, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/event/getinactives", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Event>> getInActiveEvents() {
		Collection<Event> events = eventService.getInActives();
		if(events==null)
			return new ResponseEntity<Collection<Event>>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<Collection<Event>>(events, HttpStatus.OK);
	}
	@RequestMapping(value = "/event/getuserappliedevents/{user}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Event>> getUserAppliedEvents(@PathVariable("user") Long id){
		Collection<Event> events = eventService.getUserAppliedEvents(id);
		if(events==null)
			return new ResponseEntity<Collection<Event>>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<Collection<Event>>(events, HttpStatus.OK);
	}
	@RequestMapping(value = "/event/getNotStartedEvents", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Event>> getNotStartedEvents(){
		Collection<Event> notStartedEvents = eventService.getNotStartedEvents();
		if(notStartedEvents == null)
			return new ResponseEntity<Collection<Event>>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<Collection<Event>>(notStartedEvents, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/event/getStartedEvents", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Event>> getStartedEvents(){
		Collection<Event> startedEvents = eventService.getStartedEvents();
		if(startedEvents == null){
			return new ResponseEntity<Collection<Event>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Collection<Event>>(startedEvents, HttpStatus.OK);
	}
	
	/*@RequestMapping(value = "/event/getfinished", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Event>> getFinished() {
		CollectiveStoryArrangementTask c = new CollectiveStoryArrangementTask();
		c.findFinishedEvents();
		Collection<Event> events = eventService.getInActives();
		if(events==null)
			return new ResponseEntity<Collection<Event>>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<Collection<Event>>(events, HttpStatus.OK);
	}*/

}
