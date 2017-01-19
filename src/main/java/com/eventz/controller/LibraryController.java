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

import com.eventz.model.Library;
import com.eventz.model.LibraryIndex;
import com.eventz.model.Story;
import com.eventz.service.LibraryService;

@CrossOrigin(origins = "http://kittbook.com")
@RestController
public class LibraryController {

	@Autowired
	LibraryService libraryService;

	@RequestMapping(value = "/library/get/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Library> getOneLibrary(@PathVariable("id") Long id) {
		Library library = libraryService.findOne(id);
		if (library == null) {
			return new ResponseEntity<Library>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Library>(library, HttpStatus.OK);

	}

	@RequestMapping(value = "/library/getbyuser/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Library>> getUserLibrary(@PathVariable("id") Long id) {
		Collection<Library> library = libraryService.getUserLibrary(id);
		if (library == null) {
			return new ResponseEntity<Collection<Library>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Collection<Library>>(library, HttpStatus.OK);

	}

	@RequestMapping(value = "/library/getuserlibrarystories/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Story>> getUserLibraryStories(@PathVariable("id") Long id) {
		Collection<Story> stories = libraryService.getUserLibraryStories(id);
		if (stories == null) {
			return new ResponseEntity<Collection<Story>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Collection<Story>>(stories, HttpStatus.OK);

	}

	@RequestMapping(value = "/library/addstory", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<LibraryIndex> addStory(@RequestBody LibraryIndex lib) throws ParseException {
		LibraryIndex ind = libraryService.addStoryToLibrary(lib);
		if (ind == null)
			return new ResponseEntity<LibraryIndex>(HttpStatus.NOT_FOUND);

		return new ResponseEntity<LibraryIndex>(ind, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/library/hasstory/{id},{id2}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public boolean isStoryInLibrary(@PathVariable("id") Long libraryID,@PathVariable("id2") Long storyID){
		boolean result = libraryService.isStoryInLibrary(libraryID, storyID);
		return result;
		
	}
	
	@RequestMapping(value = "library/deletestory/{libid},{stid}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LibraryIndex> deleteUser(@PathVariable("libid") Long libraryID,@PathVariable("stid") Long storyID) {

		libraryService.deleteStory(libraryID,storyID);
		return new ResponseEntity<LibraryIndex>(HttpStatus.NO_CONTENT);

	}
}
