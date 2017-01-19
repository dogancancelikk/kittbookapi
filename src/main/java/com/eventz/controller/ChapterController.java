package com.eventz.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.eventz.model.Chapter;
import com.eventz.model.ChapterDTO;
import com.eventz.model.ChapterRate;
import com.eventz.model.ChapterRead;
import com.eventz.model.ChapterReadId;
import com.eventz.service.ChapterService;

@CrossOrigin(origins = "http://kittbook.com")
@RestController
public class ChapterController {

	@Autowired
	ChapterService chapterService;

	@RequestMapping(value = "/chapter/get", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Chapter>> getAllChapters() {
		Collection<Chapter> getChapters = chapterService.findAll();
		if(getChapters==null)
			return new ResponseEntity<Collection<Chapter>>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<Collection<Chapter>>(getChapters, HttpStatus.OK);
	}
	
	@RequestMapping(value="/chapter/getChapterWithNumber/{chapterNumber},{storyID}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ChapterDTO> getChapterWithNumber(@PathVariable("storyID") Long storyID, @PathVariable("chapterNumber") Integer chapterNumber){
		
		ChapterDTO chapter = chapterService.getChapterWithChapterNumber(chapterNumber, storyID);
		if(chapter == null)
			return null;
		return new ResponseEntity<ChapterDTO>(chapter,HttpStatus.OK);
	}

	@RequestMapping(value = "/chapter/getstorychapters/{id},{value}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Chapter>> getStoryChapter(@PathVariable("id") Long id,@PathVariable("value") Boolean value) {
		Collection<Chapter> getChapters=null;
		if(value)
			getChapters = chapterService.getAllChapters(id);
		else 
			getChapters = chapterService.getChapters(id);
		return new ResponseEntity<Collection<Chapter>>(getChapters, HttpStatus.OK);
	}

	@RequestMapping(value = "/chapter/get/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ChapterDTO> getOneChapter(@PathVariable("id") Long id) {
		ChapterDTO chapter = chapterService.findOne(id);
		if(chapter==null)
			return new ResponseEntity<ChapterDTO>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<ChapterDTO>(chapter, HttpStatus.OK);
	}

	@RequestMapping(value = "/chapter/update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Chapter> updateChapter(@RequestBody ChapterDTO chapter) {
		Chapter updatedChapter = chapterService.update(chapter);
		if (updatedChapter == null)
			return new ResponseEntity<Chapter>(HttpStatus.INTERNAL_SERVER_ERROR);
		return new ResponseEntity<Chapter>(updatedChapter, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/chapter/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Chapter> createChapter(@RequestBody ChapterDTO chapter) {
		Chapter createdChapter = chapterService.create(chapter);
		if(createdChapter==null)
			return new ResponseEntity<Chapter>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<Chapter>(createdChapter, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/chapter/delete/{id}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Chapter> deleteChapter(@PathVariable("id") Long id) {
		chapterService.delete(id);
		return new ResponseEntity<Chapter>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/chapter/publish/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Chapter> publishChapter(@PathVariable("id") Long id) {
		Chapter chapter = chapterService.publishChapter(id);
		if(chapter==null)
			return new ResponseEntity<Chapter>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<Chapter>(chapter, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/chapter/unpublish/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Chapter> unPublishChapter(@PathVariable("id") Long id) {
		Chapter chapter = chapterService.unPublishChapter(id);
		if(chapter==null)
			return new ResponseEntity<Chapter>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<Chapter>(chapter, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/chapter/rate", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Chapter> rateChapter(@RequestBody ChapterRate chapterRate) {
		Chapter chapter = chapterService.rateChapter(chapterRate);
		if(chapter==null)
			return new ResponseEntity<Chapter>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<Chapter>(chapter, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/chapter/isratedbyuser/{id},{id2}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Integer> isRatedByUser(@PathVariable("id") Long chapterID, @PathVariable("id2") Long userID) {
		Integer rate = chapterService.isRatedByUser(chapterID, userID);
		return new ResponseEntity<Integer>(rate, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/chapter/read", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ChapterRead> readChapter(@RequestBody ChapterReadId id) {
		ChapterRead chapter = chapterService.readChapter(id);
		return new ResponseEntity<ChapterRead>(chapter, HttpStatus.OK);
	}
}
