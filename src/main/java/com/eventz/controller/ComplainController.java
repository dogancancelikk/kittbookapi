package com.eventz.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.eventz.model.Complain;
import com.eventz.service.ComplainService;

@CrossOrigin(origins = "http://kittbook.com")
@RestController
public class ComplainController {
	
	@Autowired
	ComplainService complainService;
	
	@RequestMapping(value="/complain/get", method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Complain>> getAllComplains(){
		
		Collection<Complain> complains=complainService.findAll();
		if(complains==null)
			return new ResponseEntity<Collection<Complain>>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<Collection<Complain>>(complains,HttpStatus.OK);
	}
	
	@RequestMapping(value ="/complain/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Complain> createComplain(@RequestBody Complain complain) throws ParseException {
		Complain createdComplain = complainService.create(complain);
		if(createdComplain==null)
			return new ResponseEntity<Complain>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<Complain>(createdComplain, HttpStatus.CREATED);
	}

}
