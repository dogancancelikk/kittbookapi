package com.eventz.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.eventz.model.Comments;

public interface CommentRepository extends JpaRepository<Comments, Long> {

	public static final String STORY_COMMENTS = "SELECT c "
			+ "FROM Comments c "
			+ "WHERE c.typeID = 1 "
			+ "AND c.parentID = :story "
			+ "AND c.status = 1 ";
	
	public static final String CHAPTER_COMMENTS = "SELECT c "
			+ "FROM Comments c "
			+ "WHERE c.typeID = 2 "
			+ "AND c.parentID = :chapter "
			+ "AND c.status = 1 ";
	
	@Query(STORY_COMMENTS)
	public Collection<Comments> getStoryComments(@Param("story") Long id);
	@Query(CHAPTER_COMMENTS)
	public Collection<Comments> getChapterComments(@Param("chapter") Long id);

}
