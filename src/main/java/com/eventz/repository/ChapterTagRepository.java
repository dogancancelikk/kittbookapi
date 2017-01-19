package com.eventz.repository;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.eventz.model.ChapterDTO;
import com.eventz.model.ChapterTag;
import com.eventz.model.ChapterTagId;

@Repository
public interface ChapterTagRepository extends JpaRepository<ChapterTag, ChapterTagId> {
	
	public final static String CHAPTER_TAGS = "SELECT t.tag "
			+ "FROM ChapterTag t "
			+ "WHERE t.chapterID = :chapter ";
	public final static String FIND_BY_TAG = "SELECT c "
			+ "FROM Chapter c, ChapterTag t "
			+ "WHERE c.status = 1 "
			+ "AND c.id = t.chapterID "
			+ "AND t.tag = :tg";
	public final static String SEARCH_CHAPTER_TAG = "SELECT t "
			+ "FROM ChapterTag t "
			+ "WHERE t.tag like :tag";
	
	@Query(CHAPTER_TAGS)
	public Collection<String> getChaptersTag(@Param("chapter") Long id);
	@Query(FIND_BY_TAG)
	public Collection<ChapterDTO> getChaptersByTag(@Param("tg") String tag);
	@Query(SEARCH_CHAPTER_TAG)
	public ArrayList<ChapterTag> searchChapterTag(@Param("tag") String tag);
	

}
