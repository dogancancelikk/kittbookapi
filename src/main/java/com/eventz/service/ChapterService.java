package com.eventz.service;

import java.util.Collection;

import com.eventz.model.Chapter;
import com.eventz.model.ChapterDTO;
import com.eventz.model.ChapterRate;
import com.eventz.model.ChapterRead;
import com.eventz.model.ChapterReadId;

public interface ChapterService {
	Collection<Chapter> findAll();

	ChapterDTO findOne(Long id);

	Chapter create(ChapterDTO chapter);

	Chapter update(ChapterDTO chapter);

	void delete(Long id);

	Collection<Chapter> getChapters(Long id);

	Chapter publishChapter(Long id);
	
	Chapter unPublishChapter(Long id);
	
	Chapter rateChapter(ChapterRate rate);
	
	Integer isRatedByUser(Long chapterID,Long userID);
	
	ChapterRead readChapter(ChapterReadId id);
	
	Collection<Chapter> getAllChapters(Long id);
	
	ChapterDTO getChapterWithChapterNumber(Integer chapterNumber, Long storyID);
	

}
