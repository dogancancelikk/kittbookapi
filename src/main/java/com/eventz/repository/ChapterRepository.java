package com.eventz.repository;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.eventz.model.Chapter;

@Repository
public interface ChapterRepository extends JpaRepository<Chapter,Long> {
	public final static String FIND_ALL_CHAPTERS = "SELECT c " + 
            "FROM StoryDetail d, Chapter c " +
            "WHERE d.storyID = :story " +
            "AND c.status = 1 " +
            "AND c.id = d.chapterID ";
	public final static String FIND_CHAPTERS = "SELECT c " + 
            "FROM StoryDetail d, Chapter c " +
            "WHERE d.storyID = :story " +
            "AND c.status = 1 " +
            "AND c.id = d.chapterID "+
            "AND c.chapterstatus = 'P'";
	public final static String FIND_ONE = "SELECT c "
			+ "FROM Chapter c "
			+ "WHERE c.status = 1 "
			+ "AND c.id = :chapter";
	public final static String FIND_ALL = "SELECT c "
			+ "FROM Chapter c "
			+ "WHERE c.status = 1 ";
	public final static String DELETE_STORY_CHAPTERS = "UPDATE Chapter c "
			+ "SET c.status = 0 "
			+ "WHERE storyID = :story ";
	public final static String SEARCH_CHAPTER = "SELECT c "
			+ "FROM Chapter c "
			+ "WHERE c.status = 1 "
			+ "AND c.name like :chapter";
		
	@Query(FIND_CHAPTERS)
	public Collection<Chapter> findChapters(@Param("story") Long id);
	@Query(FIND_ALL)
	public Collection<Chapter> findAllChapters();
	@Query(FIND_ONE)
	public Chapter findOneChapter(@Param("chapter") Long id);
	@Query(FIND_ALL_CHAPTERS)
	public Collection<Chapter> findAllStoryChapters(@Param("story") Long id);
	@Modifying
	@Query(DELETE_STORY_CHAPTERS)
	public void deleteStoryChapters(@Param("story") Long story);
	@Query(SEARCH_CHAPTER)
	public ArrayList<Chapter> searchChapter(@Param("chapter") String chapter);
	
	public Chapter findByChapterNumberAndStoryIDAndStatus (Integer chapterNumber, Long storyID, Integer status);
	
}
