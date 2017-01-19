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

import com.eventz.model.CommentDTO;
import com.eventz.model.Comments;
import com.eventz.service.CommentService;

@CrossOrigin(origins = "http://kittbook.com")
@RestController
public class CommentController {
	
	@Autowired
	CommentService commentService;
	
	@RequestMapping(value = "/story/getcomments/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<CommentDTO>> getStoryComments(@PathVariable("id") Long id) {
		Collection<CommentDTO> comments = commentService.getStoryComments(id);
		return new ResponseEntity<Collection<CommentDTO>>(comments, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/chapter/getcomments/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<CommentDTO>> getChapterComments(@PathVariable("id") Long id) {
		Collection<CommentDTO> comments = commentService.getChapterComments(id);
		return new ResponseEntity<Collection<CommentDTO>>(comments, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/story/comment", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Comments> addCommentStory(@RequestBody Comments comment) {
		Comments  com = commentService.addCommentToStory(comment);
		if (com == null)
			return new ResponseEntity<Comments>(HttpStatus.INTERNAL_SERVER_ERROR);
		return new ResponseEntity<Comments>(com, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/post/comment", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Comments> addCommentPost(@RequestBody Comments comment) {
		Comments  com = commentService.addCommentToPost(comment);
		if (com == null)
			return new ResponseEntity<Comments>(HttpStatus.INTERNAL_SERVER_ERROR);
		return new ResponseEntity<Comments>(com, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/chapter/comment", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Comments> addCommentChapter(@RequestBody Comments comment) {
		Comments  com = commentService.addCommentToChapter(comment);
		if (com == null)
			return new ResponseEntity<Comments>(HttpStatus.INTERNAL_SERVER_ERROR);
		return new ResponseEntity<Comments>(com, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/comment/delete/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Comments> deleteComment(@PathVariable("id") Long id) {
		commentService.deleteComment(id);
		return new ResponseEntity<Comments>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/comment/update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Comments> updateComment(@RequestBody Comments comment) {
		Comments c = commentService.updateComment(comment);
		if(c==null)
			return new ResponseEntity<Comments>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<Comments>(c,HttpStatus.OK);
	}
}
