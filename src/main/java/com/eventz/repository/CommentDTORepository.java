package com.eventz.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.eventz.model.CommentDTO;

public interface CommentDTORepository extends JpaRepository<CommentDTO, Long> {
	
	public static final String STORY_COMMENTS = "SELECT new CommentDTO(c,u.userName,u.image) "
			+ "FROM Comments c,User u "
			+ "WHERE c.typeID = 1 "
			+ "AND u.status=1 "
			+ "AND u.id = c.userID "
			+ "AND c.parentID = :story "
			+ "AND c.status = 1 ";
	
	public static final String CHAPTER_COMMENTS = "SELECT new CommentDTO(c,u.userName,u.image) "
			+ "FROM Comments c,User u "
			+ "WHERE c.typeID = 2 "
			+ "AND u.status=1 "
			+ "AND u.id = c.userID "
			+ "AND c.parentID = :chapter "
			+ "AND c.status = 1 ";
	
	@Query(STORY_COMMENTS)
	public Collection<CommentDTO> getStoryComments(@Param("story") Long id);
	@Query(CHAPTER_COMMENTS)
	public Collection<CommentDTO> getChapterComments(@Param("chapter") Long id);
}
