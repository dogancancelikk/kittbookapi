package com.eventz.controller;

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

import com.eventz.model.SearchResultDTO;
import com.eventz.response.ResponseSearchUser;
import com.eventz.service.SearchService;

@CrossOrigin(origins = "http://kittbook.com")
@RestController
public class SerachController {
	
	@Autowired
	SearchService searchService;
	
	@RequestMapping(value = "/search/{text}", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<SearchResultDTO> getlastmessages(@PathVariable("text") String text) throws ParseException {
		return new ResponseEntity<SearchResultDTO>(searchService.search(text), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/searchuser/{text}", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseSearchUser> searchuser(@PathVariable("text") String text) throws ParseException {
		return new ResponseEntity<ResponseSearchUser>(searchService.searchUserByUsername(text), HttpStatus.OK);
	}

}
