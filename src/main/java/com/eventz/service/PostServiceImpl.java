package com.eventz.service;

import java.util.Calendar;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventz.model.Activity;
import com.eventz.model.Post;
import com.eventz.repository.PostRepository;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	PostRepository postRepository;
	@Autowired
	ActivityService activityService;
	
	@Override
	public Collection<Post> findAll() {
		// TODO Auto-generated method stub
		return postRepository.findAll();
	}

	@Override
	public Post findOne(Long id) {
		// TODO Auto-generated method stub
		return postRepository.findOne(id);
	}

	@Override
	public Post create(Post post) {
		post.setPostDate(Calendar.getInstance().getTime());
		post.setVisibility(1);
		post.setStatus(1);		
		Post savedRepository = postRepository.save(post);
		Activity activity =new Activity();
		int postActivityType = 4;
		activity.setPostID(savedRepository.getId());
		activity.setActivityUserID(savedRepository.getUserID());
		activity.setActivityType(postActivityType);
		activityService.createActivity(activity);
		return savedRepository;
		
	}

	@Override
	public Post update(Post post) {
		Long postId = post.getId();
		if(postId==null && postRepository.findOne(postId)==null)
			return null;
		post.setLastUpdate(Calendar.getInstance().getTime());
		
		return postRepository.save(post);
	}

	@Override
	public void delete(Long id) {
		Post post = postRepository.findOne(id);
		if(post!=null)
		{
			post.setStatus(0);
			postRepository.save(post);
		}
	}

	@Override
	public Collection<Post> findUserPosts(Long id) {
		return postRepository.getUserPost(id);
	}

	@Override
	public Post setVisible(Long id) {
		Post post = postRepository.findOne(id);
		if(post==null)
			return null;
		post.setVisibility(1);
		return postRepository.save(post);
	}

	@Override
	public Post setInVisible(Long id) {
		Post post = postRepository.findOne(id);
		if(post==null)
			return null;
		post.setVisibility(0);
		return postRepository.save(post);
	}

}
