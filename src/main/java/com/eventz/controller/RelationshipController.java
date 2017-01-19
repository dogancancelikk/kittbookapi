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

import com.eventz.model.Relationship;
import com.eventz.model.RelationshipId;
import com.eventz.model.Story;
import com.eventz.model.User;
import com.eventz.service.RelationshipService;

@CrossOrigin(origins = "http://kittbook.com")
@RestController
public class RelationshipController {

	@Autowired
	RelationshipService relationshipService;

	@RequestMapping(value = "/relationship/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Relationship> createRelationship(@RequestBody Relationship relationship) throws ParseException {
		Relationship response = relationshipService.create(relationship);
		return response != null ? new ResponseEntity<Relationship>(response, HttpStatus.CREATED) : new ResponseEntity<Relationship>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@RequestMapping(value = "/relationship/delete", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Story> deleteRelationship(@RequestBody RelationshipId relationshipId) {
		relationshipService.delete(relationshipId);
		return new ResponseEntity<Story>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(value = "/relationship/getfollowers/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<User>> getFollowers(@PathVariable("userId") Long userId) {
		Collection<User> users = relationshipService.getFollowers(userId);
		return new ResponseEntity<Collection<User>>(users, HttpStatus.OK) ;
	}

	@RequestMapping(value = "/relationship/getfollowedusers/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<User>> getFollowedUsers(@PathVariable("userId") Long userId) {
		Collection<User> users = relationshipService.getFollowedUsers(userId);
		return new ResponseEntity<Collection<User>>(users, HttpStatus.OK);
	}

}
