package com.eventz.service;

import java.util.Calendar;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventz.model.Activity;
import com.eventz.model.Chapter;
import com.eventz.model.ChapterDTO;
import com.eventz.model.ChapterRate;
import com.eventz.model.ChapterRateID;
import com.eventz.model.ChapterRead;
import com.eventz.model.ChapterReadId;
import com.eventz.model.ChapterTag;
import com.eventz.model.ChapterTagId;
import com.eventz.model.Stories;
import com.eventz.model.Story;
import com.eventz.model.StoryDetail;
import com.eventz.repository.ChapterDtoRepository;
import com.eventz.repository.ChapterRateRepository;
import com.eventz.repository.ChapterReadRepository;
import com.eventz.repository.ChapterRepository;
import com.eventz.repository.ChapterTagRepository;
import com.eventz.repository.StoryDetailRepository;
import com.eventz.repository.StoryRepository;

@Service
public class ChapterServiceImpl implements ChapterService {

	@Autowired
	ChapterRepository chapterRepository;
	@Autowired
	StoryDetailRepository storyDetailRepository;
	@Autowired
	ChapterRateRepository rateRepository;
	@Autowired
	ChapterReadRepository readRepository;
	@Autowired
	ChapterTagRepository tagRepo;
	@Autowired
	StoryRepository storyRepo;
	@Autowired
	ActivityService activityService;
	@Autowired
	StoryService storyService;
	@Autowired
	ChapterDtoRepository chapterDtoRepository;

	@Override
	public Collection<Chapter> findAll() {
		Collection<Chapter> getAllChapter = chapterRepository.findAllChapters();
		if (getAllChapter == null)
			return null;
		return getAllChapter;
	}

	@Override
	public ChapterDTO findOne(Long id) {
		Chapter chapter = chapterRepository.findOneChapter(id);
		Stories story = storyService.findOne(chapter.getStoryID());
		if (chapter == null)
			return null;
		Collection<String> tags = tagRepo.getChaptersTag(chapter.getId());
		ChapterDTO dto = new ChapterDTO(chapter, tags, story.getName());
		return dto;
	}

	@Override
	public ChapterDTO getChapterWithChapterNumber(Integer chapterNumber, Long storyID) {
		Integer status = 1;
		ChapterDTO chapter = chapterDtoRepository.findChapterWithNumber(storyID, chapterNumber);
		if (chapter == null)
			return null;
		return chapter;
	}

	@Override
	public Chapter create(ChapterDTO dto) {

		Chapter chapter = new Chapter(dto);
		Long chapterID = dto.getId();
		if (chapterRepository.findOne(chapterID) != null)
			return null;
		if (chapter.getImage() == null) {
			Stories story = storyService.findOne(chapter.getStoryID());
			chapter.setImage(story.getImage());
		}
		Calendar now = Calendar.getInstance();
		chapter.setUpdateDate(now);
		chapter.setCreateDate(now);
		if (chapter.getStatus() == null || chapter.getStatus().equals(""))
			chapter.setChapterstatus("U");
		Collection<Chapter> chapters = chapterRepository.findChapters(dto.getStoryID());
		int chapterNumber;
		if (chapters == null)
			chapterNumber = 1;
		else
			chapterNumber = chapters.size() + 1;
		chapter.setChapterNumber(chapterNumber);
		Chapter newChapter = chapterRepository.save(chapter);
		StoryDetail det = new StoryDetail();
		det.setStoryID(newChapter.getStoryID());
		det.setChapterID(newChapter.getId());
		det.setChapterNumber(newChapter.getChapterNumber());
		storyDetailRepository.save(det);
		Collection<String> tags = dto.getTags();
		if (tags != null) {
			for (String str : tags) {
				ChapterTagId id = new ChapterTagId(newChapter.getId(), str.trim());
				if (tagRepo.findOne(id) == null) {
					ChapterTag tag = new ChapterTag(id);
					tagRepo.save(tag);
				}
			}
		}
		if ("P".equals(newChapter.getChapterstatus())) {
			Activity activity = new Activity();
			int chapterActivityType = 5;
			activity.setActivityUserID(newChapter.getUserID());
			activity.setActivityType(chapterActivityType);
			activity.setChapterID(newChapter.getId());
			activityService.createActivity(activity);
		}

		return newChapter;
	}

	@Override
	public Chapter update(ChapterDTO dto) {
		Chapter chapter = new Chapter(dto);
		if (chapterRepository.findOne(dto.getId()) == null)
			return null;
		Calendar now = Calendar.getInstance();
		chapter.setUpdateDate(now);
		Chapter updateOneChapter = chapterRepository.save(chapter);
		Collection<String> tags = dto.getTags();
		if (tags != null) {
			for (String str : tags) {
				ChapterTagId id = new ChapterTagId(dto.getId(), str.trim());
				if (tagRepo.findOne(id) == null) {
					ChapterTag tag = new ChapterTag(id);
					tagRepo.save(tag);
				}
			}
		}
		return updateOneChapter;
	}

	@Override
	public void delete(Long id) {
		Chapter chapter = chapterRepository.findOne(id);
		if (chapter != null) {
			chapter.setStatus(0);
			chapterRepository.save(chapter);
		}
	}

	@Override
	public Collection<Chapter> getChapters(Long id) {
		return chapterRepository.findChapters(id);
	}

	@Override
	public Collection<Chapter> getAllChapters(Long id) {
		return chapterRepository.findAllStoryChapters(id);
	}

	@Override
	public Chapter publishChapter(Long id) {
		Chapter chapter = chapterRepository.findOne(id);
		if (chapter == null)
			return null;
		String newStatus = "P";
		chapter.setChapterstatus(newStatus);
		chapterRepository.save(chapter);
		return chapter;
	}

	@Override
	public Chapter unPublishChapter(Long id) {
		Chapter chapter = chapterRepository.findOne(id);
		if (chapter == null)
			return null;
		String newStatus = "U";
		chapter.setChapterstatus(newStatus);
		chapterRepository.save(chapter);
		return chapter;
	}

	@Override
	public Chapter rateChapter(ChapterRate rate) {
		ChapterRateID id = new ChapterRateID(rate);
		if (rateRepository.findOne(id) != null)
			return null;
		rateRepository.save(rate);
		Double newRate = rateRepository.calculateNewRate(rate.getChapterID());
		Chapter chapter = chapterRepository.findOne(rate.getChapterID());
		chapter.setRate(newRate);
		return chapterRepository.save(chapter);
	}

	@Override
	public Integer isRatedByUser(Long chapterID, Long userID) {
		ChapterRateID id = new ChapterRateID(chapterID, userID);
		ChapterRate rate = rateRepository.findOne(id);
		if (rate == null)
			return null;
		else
			return rate.getRate();
	}

	@Override
	public ChapterRead readChapter(ChapterReadId id) {
		if (readRepository.findOne(id) != null)
			return null;
		Chapter chapter = chapterRepository.findOne(id.getChapterID());
		Story story = new Story();
		if (chapter != null)
			story = storyRepo.findOne(chapter.getStoryID());
		if (story == null || story.getOwnerID() == id.getUserID())
			return null;
		ChapterRead c = new ChapterRead(id);
		readRepository.save(c);
		int readCount = chapter.getReadCount();
		readCount++;
		chapter.setReadCount(readCount);
		chapterRepository.save(chapter);
		return c;
	}

}
