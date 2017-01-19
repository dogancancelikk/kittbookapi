package com.eventz.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.eventz.model.ChapterDTO;

@Repository
public interface ChapterDtoRepository extends JpaRepository<ChapterDTO,Long> {
	
	public final static String CHAPTERS ="SELECT "
			+ "new ChapterDTO"
			+ "(c,s) "
			+ "FROM Chapter c, Story s "
			+ "WHERE s.id = c.storyID "
			+ "AND s.id = :story "
			+ "AND s.status = 1 "
			+ "AND c.status = 1 ";
	
	public final static String CHAPTER_WITH_NUMBER ="SELECT "
			+"new ChapterDTO"
			+"(c,s) "
			+"FROM Chapter c, Story s "
			+"WHERE s.id = c.storyID "
			+"AND s.id = :story "
			+"AND c.chapterNumber = :cNumber "
			+"AND c.status = 1 ";
	
	@Query(CHAPTERS)
	public Collection<ChapterDTO> getChapters(@Param("story") Long id);
	
	@Query(CHAPTER_WITH_NUMBER)
	public ChapterDTO findChapterWithNumber(@Param("story") Long id, @Param("cNumber") Integer chapterNumber);
}
