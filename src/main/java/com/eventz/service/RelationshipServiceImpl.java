package com.eventz.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventz.model.Activity;
import com.eventz.model.Relationship;
import com.eventz.model.RelationshipId;
import com.eventz.model.User;
import com.eventz.repository.RelationshipRepository;

@Service
public class RelationshipServiceImpl implements RelationshipService {

	@Autowired
	RelationshipRepository relationshipRepository;
	
	@Autowired
	ActivityService activityService;

	@Override
	public Relationship create(Relationship relationship) {
		if (relationship.getFollowedby().compareTo(relationship.getFollowing()) == 0) {
			return null;
		}
		Relationship savedRepository= relationshipRepository.save(relationship);
		Activity activity = new Activity();
		int followActivityType = 3;
		activity.setActivityUserID(savedRepository.getFollowedby());
		activity.setUserID(savedRepository.getFollowing());
		activity.setActivityType(followActivityType);
		activityService.createActivity(activity);
		return savedRepository;
	}

	@Override
	public void delete(RelationshipId relationshipId) {
		relationshipRepository.delete(relationshipId);
	}

	@Override
	public Collection<User> getFollowers(Long userId) {
		Collection<User> users = relationshipRepository.getFollowers(userId);
		if (users == null || users.size() == 0)
			return null;
		
		for (User user : users) {
			user.setPassword("");
			user.setRoles(null);
		}
		return users;
	}

	@Override
	public Collection<User> getFollowedUsers(Long userId) {
		Collection<User> users = relationshipRepository.getFollowedUsers(userId);
		if (users == null || users.size() == 0)
			return null;
		for (User user : users) {
			user.setPassword("");
			user.setRoles(null);
		}

		return users;
	}

}
