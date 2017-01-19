package com.eventz.model;

import java.io.Serializable;

public class RelationshipId implements Serializable {


	private static final long serialVersionUID = -7501932738392621908L;
	private Long followedby;
	private Long following;

	public RelationshipId() {
	}

	public RelationshipId(Long followedby, Long following) {
		this.followedby = followedby;
		this.following = following;
	}

	public boolean equals(Object o) {
		return ((o instanceof RelationshipId) && followedby.equals(((RelationshipId) o).getFollowedby())
				&& following == ((RelationshipId) o).getFollowing());
	}

	public int hashCode() {
		return followedby.intValue() + following.intValue();
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
