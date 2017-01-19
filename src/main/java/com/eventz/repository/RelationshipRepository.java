package com.eventz.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.eventz.model.Relationship;
import com.eventz.model.RelationshipId;
import com.eventz.model.User;

@Repository
public interface RelationshipRepository extends JpaRepository<Relationship, RelationshipId> {

	public final static String FIND_FOLLOWERS = "SELECT u FROM Relationship r, User u WHERE r.following = :userId and r.followedby = u.id and u.status = 1";
	public final static String FIND_FOLLOWED_USERS = "SELECT u FROM Relationship r, User u WHERE r.followedby = :userId and r.following = u.id and u.status = 1";

	@Query(FIND_FOLLOWERS)
	public Collection<User> getFollowers(@Param("userId") Long userId);
	
	@Query(FIND_FOLLOWED_USERS)
	public Collection<User> getFollowedUsers(@Param("userId") Long userId);
	
}
