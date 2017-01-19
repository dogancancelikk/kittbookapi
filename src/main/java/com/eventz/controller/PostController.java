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

import com.eventz.model.Post;
import com.eventz.service.PostService;

@CrossOrigin(origins = "http://kittbook.com")
@RestController
public class PostController {

	@Autowired
	PostService postService;
	
	@RequestMapping(value="/post/get", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Post>> getAllPosts(){
		Collection<Post> posts = postService.findAll();
		if(posts==null)
			return new ResponseEntity<Collection<Post>>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<Collection<Post>>(posts,HttpStatus.OK);
	}
	@RequestMapping(value="/post/getuserposts/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Post>> getUserPosts(@PathVariable("id") Long id){
		Collection<Post> posts = postService.findUserPosts(id);
		if(posts==null)
			return new ResponseEntity<Collection<Post>>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<Collection<Post>>(posts,HttpStatus.OK);
	}
	@RequestMapping(value = "/post/create", method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Post> createPost(@RequestBody Post post) throws ParseException{
		Post createdPost = postService.create(post);
		if(createdPost==null)
			return new ResponseEntity<Post>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<Post>(createdPost,HttpStatus.CREATED);
	}
	@RequestMapping(value = "/post/delete/{id}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Post> deletePost(@PathVariable("id") Long id) {
		postService.delete(id);
		return new ResponseEntity<Post>(HttpStatus.NO_CONTENT);
	}
	@RequestMapping(value="/post/setvisble/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Post> setVisible(@PathVariable("id") Long id) throws Exception{
		Post post = postService.setVisible(id);
		if(post==null)
			throw new Exception("Post bulunamadı !") ;
		return new ResponseEntity<Post>(post,HttpStatus.OK);
	}
	@RequestMapping(value="/post/setinvisble/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Post> setInVisible(@PathVariable("id") Long id) throws Exception{
		Post post = postService.setInVisible(id);
		if(post==null)
			throw new Exception("Post bulunamadı !") ;
		return new ResponseEntity<Post>(post,HttpStatus.OK);
	}
}
