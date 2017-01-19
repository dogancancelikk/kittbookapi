package com.eventz.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;


@Entity
@IdClass(RelationshipId.class)
public class Relationship {
	
	@Id
	private Long followedby;
	@Id
	private Long following;

	public Relationship() {
	}

	public Long getFollowedby() {
		return followedby;
	}

	public void setFollowedby(Long followedby) {
		this.followedby = followedby;
	}

	public Long getFollowing() {
		return following;
	}

	public void setFollowing(Long following) {
		this.following = following;
	}
}