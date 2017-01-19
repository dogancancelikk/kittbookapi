package com.eventz.service;

import java.util.Collection;

import com.eventz.model.Post;

public interface PostService {

	Collection<Post> findAll();
	
	Post findOne(Long id);
	
	Post create(Post post);
	
	Post update(Post post);
	
	void delete(Long id);
	
	Collection<Post> findUserPosts(Long id);
	
	Post setVisible(Long id);
	
	Post setInVisible(Long id);
}
