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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.eventz.model.EventApply;
import com.eventz.model.EventApplyDTO;
import com.eventz.model.UserApply;
import com.eventz.service.EventApplyService;

@CrossOrigin(origins = "http://kittbook.com")
@RestController
public class EventApplyController {
	@Autowired
	EventApplyService applyService;
	
	@RequestMapping(value = "/eventapply/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<EventApply> createApply(@RequestBody EventApply event) throws ParseException {
		EventApply createdApply = applyService.create(event);
		if(createdApply==null)
			return new ResponseEntity<EventApply>(HttpStatus.NOT_FOUND);

		return new ResponseEntity<EventApply>(createdApply, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/eventapply/update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<EventApply> updateUser(@RequestBody EventApply eventApply) {
		EventApply updatedApply = applyService.update(eventApply);

		return new ResponseEntity<EventApply>(updatedApply, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/eventapply/get/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EventApply> getOneApply(@PathVariable("id") Long id) {
		EventApply apply = applyService.findOne(id);
		if(apply==null)
			return new ResponseEntity<EventApply>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<EventApply>(apply, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/eventapply/appliedUser/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ArrayList<UserApply>> getAppliedUsers (@PathVariable("id") Long id) {
		ArrayList<UserApply> apply = applyService.getAppliedUser(id);
		if(apply==null)
			return new ResponseEntity<ArrayList<UserApply>>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<ArrayList<UserApply>>(apply, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/eventapply/get", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<EventApply>> getAllApplies() {
		Collection<EventApply> applies = applyService.findAll();
		if(applies==null)
			return new ResponseEntity<Collection<EventApply>>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<Collection<EventApply>>(applies, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/eventapply/getbytype/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<EventApply>> getAppliesByType(@PathVariable("id") Integer id) {
		Collection<EventApply> applies = applyService.findByType(id);
		if(applies==null)
			return new ResponseEntity<Collection<EventApply>>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<Collection<EventApply>>(applies, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/eventapply/getbyevent/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<EventApplyDTO>> getAppliesByEvent(@PathVariable("id") Long id) {
		Collection<EventApplyDTO> applies = applyService.findByEvent(id);
		if(applies==null)
			return new ResponseEntity<Collection<EventApplyDTO>>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<Collection<EventApplyDTO>>(applies, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/eventapply/approve/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EventApply> approveApply(@PathVariable("id") Long id) {
		EventApply apply = applyService.approve(id);
		return new ResponseEntity<EventApply>(apply, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/eventapply/reject/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EventApply> rejectApply(@PathVariable("id") Long id) {
		EventApply apply = applyService.reject(id);
		return new ResponseEntity<EventApply>(apply, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/eventapply/isapplied", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<EventApply> isApplied(@RequestBody EventApplyDTO event) throws ParseException {
		EventApply apply = applyService.isApplied(event);
		return new ResponseEntity<EventApply>(apply, HttpStatus.OK);
	}
}
