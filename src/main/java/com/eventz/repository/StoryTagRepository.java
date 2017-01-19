package com.eventz.repository;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.eventz.model.Stories;
import com.eventz.model.StoryTag;
import com.eventz.model.StoryTagId;

public interface StoryTagRepository extends JpaRepository<StoryTag, StoryTagId> {
	
	public final static String STORY_TAGS = "SELECT distinct t.tag "
			+ "FROM StoryTag t "
			+ "WHERE t.storyID = :story ";
	public final static String FIND_BY_TAG = "SELECT new Stories(s,CONCAT(u.name,' ',u.surname) as USERNAME)"
			+ "FROM Story s, StoryTag t, User u "
			+ "WHERE s.status = 1 "
			+ "AND u.status = 1 "
			+ "AND s.ownerID = u.id "
			+ "AND s.id = t.storyID "
			+ "AND t.tag = :tg";
	public final static String SEARH_STORY_TAGS = "SELECT t "
			+ "FROM StoryTag t "
			+ "WHERE t.tag like :tag";
	
	@Query(STORY_TAGS)
	public Collection<String> getStoriesTag(@Param("story") Long id);
	@Query(FIND_BY_TAG)
	public Collection<Stories> getStoriesByTag(@Param("tg") String tag);
	@Query(SEARH_STORY_TAGS)
	public ArrayList<StoryTag> searchStoryTag(@Param("tag") String tag);
	
	
}
