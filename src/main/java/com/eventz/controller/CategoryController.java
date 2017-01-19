package com.eventz.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.eventz.model.Category;
import com.eventz.model.User;
import com.eventz.service.CategoryService;

@CrossOrigin(origins = "http://kittbook.com")
@RestController
public class CategoryController {
	@Autowired
	CategoryService categoryService;
	
	@RequestMapping(value="/category/get", method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Category>> getAllCategories(){
		
		Collection<Category> categories=categoryService.findAll();
		if(categories==null)
			return new ResponseEntity<Collection<Category>>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<Collection<Category>>(categories,HttpStatus.OK);
	}

	@RequestMapping(value="/category/get/{id}", method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Category> getOneCategory(@PathVariable("id") Long id){
		
		Category category = categoryService.findOne(id);
		if(category==null)
			return new ResponseEntity<Category>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<Category>(category,HttpStatus.OK);
	}	
	

}
