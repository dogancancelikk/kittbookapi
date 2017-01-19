package com.eventz.service;

import java.util.Collection;

import com.eventz.model.Relationship;
import com.eventz.model.RelationshipId;
import com.eventz.model.User;

public interface RelationshipService {

	Relationship create(Relationship relationship);

	void delete(RelationshipId relationshipId);
	
	Collection<User> getFollowers(Long userId);

	Collection<User> getFollowedUsers(Long userId);

}
