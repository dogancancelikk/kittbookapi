package com.eventz.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.eventz.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
	public final String USER_POSTS = "SELECT p "
			+ "FROM Post p "
			+ "WHERE p.userID = :user "
			+ "AND p.status = 1 order by p.postDate DESC ";
	
	@Query(USER_POSTS)
	public Collection<Post> getUserPost(@Param("user") Long id);
}
