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

import com.eventz.model.Stories;
import com.eventz.model.Story;
import com.eventz.model.StoryAuthor;
import com.eventz.model.StoryRate;
import com.eventz.model.StoryRead;
import com.eventz.model.StoryReadId;
import com.eventz.model.User;
import com.eventz.response.ResponseOrderedStories;
import com.eventz.service.ExceptionLogService;
import com.eventz.service.StoryService;

@CrossOrigin(origins = "http://kittbook.com")
@RestController
public class StoryController {

	@Autowired
	StoryService storyService;
	
	@Autowired
	ExceptionLogService exceptionLogService;

	@RequestMapping(value = "/story/get", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Story>> getAllStory() {
		Collection<Story> stories = storyService.findAll();
		if(stories==null)
			return new ResponseEntity<Collection<Story>>(HttpStatus.OK);
		return new ResponseEntity<Collection<Story>>(stories, HttpStatus.OK);
	}

	@RequestMapping(value = "/story/getAllWithDetails", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Stories>> getAllStories() {
		Collection<Stories> stories = storyService.findAllWithDetails();
		if(stories==null)
			return new ResponseEntity<Collection<Stories>>(HttpStatus.OK);
		return new ResponseEntity<Collection<Stories>>(stories, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/story/getWithDetails", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Stories>> getStories() {
		Collection<Stories> stories = storyService.findWithDetails();
		if(stories==null)
			return new ResponseEntity<Collection<Stories>>(HttpStatus.OK);
		return new ResponseEntity<Collection<Stories>>(stories, HttpStatus.OK);
	}

	@RequestMapping(value = "/story/get/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Stories> getOneStory(@PathVariable("id") Long id) {
		Stories story = storyService.findOne(id);
		if (story == null) {
			return new ResponseEntity<Stories>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Stories>(story, HttpStatus.OK);
	}

	@RequestMapping(value = "/story/getuserstory/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Story>> getUserStory(@PathVariable("id") Long id) {
		Collection<Story> stories = storyService.findUserStories(id);
		if(stories == null)
			return new ResponseEntity<Collection<Story>>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<Collection<Story>>(stories, HttpStatus.OK);
	}

	@RequestMapping(value = "/story/update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Story> updateStory(@RequestBody Stories story) {
		Story updatedStory = storyService.update(story);

		return new ResponseEntity<Story>(updatedStory, HttpStatus.CREATED);
	}

	@RequestMapping(value ="/story/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Story> createStory(@RequestBody Stories story) throws ParseException {
		Story createdStory = storyService.create(story);
		if(createdStory==null)
			return new ResponseEntity<Story>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<Story>(createdStory, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/story/delete/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Story> deleteStory(@PathVariable("id") Long id) throws ParseException{

		storyService.delete(id);
		return new ResponseEntity<Story>(HttpStatus.NO_CONTENT);

	}

	@RequestMapping(value = "/story/getcategoric/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Stories>> getCategoricStories(@PathVariable("id") Long id) {
		Collection<Stories> stories = storyService.findStoriesByCategory(id);
		if(stories==null)
			return new ResponseEntity<Collection<Stories>>(new ArrayList<Stories>(), HttpStatus.OK);
		return new ResponseEntity<Collection<Stories>>(stories, HttpStatus.OK);
	}

	@RequestMapping(value = "/story/rate", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Story> rateStory(@RequestBody StoryRate storyRate) {
		Story story = storyService.rateStory(storyRate);
		if(story==null)
			return new ResponseEntity<Story>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<Story>(story, HttpStatus.OK);
	}

	@RequestMapping(value = "/story/publish/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Story> publishStory(@PathVariable("id") Long id) {
		Story story1 = storyService.publishStory(id);
		if(story1==null)
			return new ResponseEntity<Story>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<Story>(story1, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/story/unpublish/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Story> unPublishStory(@PathVariable("id") Long id) {
		Story story1 = storyService.unPublishStory(id);
		if(story1==null)
			return new ResponseEntity<Story>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<Story>(story1, HttpStatus.OK);
	}


	@RequestMapping(value = "/story/isratedbyuser/{id},{id2}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Integer> isRatedByUser(@PathVariable("id") Long storyID, @PathVariable("id2") Long userID) {
		Integer rate = storyService.isRatedByUser(storyID, userID);
		return new ResponseEntity<Integer>(rate, HttpStatus.OK);
	}
	
	/*@RequestMapping(value = "/story/incrementcounter", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Integer> incrementCounter(@RequestBody StoryReadId id) {
		Integer counter = storyService.incrementCounter(id);
		if (counter == null)
			return new ResponseEntity<Integer>(HttpStatus.NOT_FOUND);

		return new ResponseEntity<Integer>(HttpStatus.OK);
	}*/
	@RequestMapping(value = "/story/rateownstory", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Story> rateOwnStory(@RequestBody StoryRate storyRate) {
		Story story = storyService.rateOwnStory(storyRate);
		if(story==null)
			return new ResponseEntity<Story>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<Story>(story, HttpStatus.OK);
	}
	@RequestMapping(value = "/story/gettags/{id}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Collection<String>> getTags(@PathVariable("id") Long id) {
		Collection<String> tags = storyService.getStoryTags(id);
		if (tags == null)
			return new ResponseEntity<Collection<String>>(HttpStatus.NOT_FOUND);

		return new ResponseEntity<Collection<String>>(tags,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/story/gettags/{tag}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Collection<Stories>> getStoriesByTag(@PathVariable("tag") String tag) {
		Collection<Stories> stories = storyService.getStoriesByTag(tag);
		if (stories == null)
			return new ResponseEntity<Collection<Stories>>(HttpStatus.NOT_FOUND);

		return new ResponseEntity<Collection<Stories>>(stories,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/story/read", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StoryRead> readStory(@RequestBody StoryReadId id) {
		StoryRead read = storyService.readStory(id);
		return new ResponseEntity<StoryRead>(read, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/collectivebook/addwriter", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StoryAuthor> addWriter(@RequestBody StoryAuthor s) {
		StoryAuthor writer = storyService.addWriter(s);
		return new ResponseEntity<StoryAuthor>(writer, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/story/getstorywriter/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<User>> getStoryWriter(@PathVariable("id") Long id) {
		Collection<User> users = storyService.getStoryWriters(id);
		return new ResponseEntity<Collection<User>>(users, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/story/getallordered", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseOrderedStories> getAllOrdered() {
		int id=0;
		ResponseOrderedStories stories = storyService.getOrderedStories(id);
		if(stories==null)
			return new ResponseEntity<ResponseOrderedStories>(HttpStatus.OK);
		return new ResponseEntity<ResponseOrderedStories>(stories, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/story/getOneOrdered/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseOrderedStories> getOneOrdered(@PathVariable("id") int id) {
		ResponseOrderedStories stories = storyService.getOrderedStories(id);
		if(stories==null)
			return new ResponseEntity<ResponseOrderedStories>(HttpStatus.OK);
		return new ResponseEntity<ResponseOrderedStories>(stories, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/story/getMobileOrdered/{id}/{page}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseOrderedStories> getMobileOrdered(@PathVariable("id") int id, @PathVariable("page") int page) {
		exceptionLogService.logException("GetMobileOrdered", "getMobileOrdered", "Is there some problem about", "Bla Bla");
		ResponseOrderedStories stories = storyService.getMobileOrdered(id,page);
		if(stories==null)
			return new ResponseEntity<ResponseOrderedStories>(HttpStatus.OK);
		return new ResponseEntity<ResponseOrderedStories>(stories, HttpStatus.OK);
	}
}
