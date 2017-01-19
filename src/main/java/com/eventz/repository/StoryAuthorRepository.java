package com.eventz.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.eventz.model.StoryAuthor;
import com.eventz.model.StoryAuthorId;
import com.eventz.model.User;

@Repository
public interface StoryAuthorRepository extends JpaRepository<StoryAuthor, StoryAuthorId> {
	
	public static final String GET_WRITERS = "SELECT u "
			+ "FROM User u, StoryAuthor s "
			+ "WHERE s.status = 1 "
			+ "AND u.status = 1 "
			+ "AND s.storyID = :story "
			+ "AND s.userID = u.id ";
	
	@Query(GET_WRITERS)
	public Collection<User> getStoryWriters (@Param("story") Long id);

}
