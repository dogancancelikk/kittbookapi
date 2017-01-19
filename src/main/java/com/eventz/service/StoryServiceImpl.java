package com.eventz.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.eventz.model.Activity;
import com.eventz.model.Category;
import com.eventz.model.Stories;
import com.eventz.model.Story;
import com.eventz.model.StoryAuthor;
import com.eventz.model.StoryAuthorId;
import com.eventz.model.StoryRate;
import com.eventz.model.StoryRateId;
import com.eventz.model.StoryRead;
import com.eventz.model.StoryReadId;
import com.eventz.model.StoryTag;
import com.eventz.model.StoryTagId;
import com.eventz.model.User;
import com.eventz.repository.CategoryRepository;
import com.eventz.repository.ChapterRepository;
import com.eventz.repository.StoryAuthorRepository;
import com.eventz.repository.StoryRateRepository;
import com.eventz.repository.StoryReadRepository;
import com.eventz.repository.StoryRepository;
import com.eventz.repository.StoryTagRepository;
import com.eventz.repository.UserRepository;
import com.eventz.response.ResponseOrderedStories;

@Service
public class StoryServiceImpl implements StoryService {

	@Autowired
	StoryRepository storyRepository;
	@Autowired
	StoryRateRepository rateRepository;
	@Autowired
	CategoryRepository categoryRepository;
	@Autowired
	StoryTagRepository tagRepository;
	@Autowired
	StoryReadRepository readRepository;
	@Autowired
	StoryAuthorRepository authorRepo;
	@Autowired
	ChapterRepository chapterRepo;
	@Autowired
	UserRepository userRepo;
	@Autowired
	ActivityService activityService;
	public static final String DEFAULT_IMAGE = "http://kittbook.com/images/kittbook-default.png";

	@Override
	public Collection<Story> findAll() {
		Collection<Story> getAllStory = storyRepository.findAllStory();
		return getAllStory;
	}

	@Override
	public Stories findOne(Long id) {
		Stories getOneStory = storyRepository.findOneStory(id);
		if (getOneStory == null)
			return null;
		Collection<String> tags = tagRepository.getStoriesTag(getOneStory.getId());
		Stories s = new Stories(getOneStory, tags);
		return s;
	}

	@Override
	public Story create(Stories s) {

		Story story = new Story(s);
		Long storyid = story.getId();
		if (storyRepository.findOne(storyid) != null)
			return null;
		Calendar now = Calendar.getInstance();
		if (story.getImage() == null)
			story.setImage(DEFAULT_IMAGE);
		story.setCreatedate(now);
		story.setLastupdate(now);
		story.setStoryrate(new Double(0));
		Story createOneStory = storyRepository.save(story);
		Collection<String> tags = s.getTags();
		if (tags != null)
			for (String st : tags) {
				StoryTagId id = new StoryTagId(createOneStory.getId(), st.trim());
				StoryTag newTag = tagRepository.findOne(id);
				if (newTag == null) {
					StoryTag createdTag = new StoryTag(id);
					tagRepository.save(createdTag);
				}
			}
		Collection<Long> authors = s.getAuthors();
		if (authors != null) {
			for (Long author : authors) {
				StoryAuthorId id = new StoryAuthorId(createOneStory.getId(), author);
				StoryAuthor newAuthor = authorRepo.findOne(id);
				if (newAuthor == null) {
					StoryAuthor createdAuthor = new StoryAuthor(id);
					authorRepo.save(createdAuthor);
				}
			}
		}
		
		if (createOneStory.getIsPublished() == 1) {
			Activity activity = new Activity();
			if (s.getIsCollective() == 0) {
				int storyActivityType = 1;
				activity.setActivityUserID(createOneStory.getOwnerID());
				activity.setStoryID(createOneStory.getId());
				activity.setActivityType(storyActivityType);
			} else {
				int collectivebookActivityType = 2;
				activity.setActivityUserID(createOneStory.getOwnerID());
				activity.setStoryID(createOneStory.getId());
				activity.setActivityType(collectivebookActivityType);
			}
			activityService.createActivity(activity);
		}

		return createOneStory;
	}

	@Override
	public Story update(Stories story) {

		Long id = story.getId();

		if (id == null || storyRepository.findOne(id) == null)
			return null;

		Collection<String> tags = story.getTags();

		if (tags != null)
			for (String st : tags) {
				StoryTagId tagId = new StoryTagId(story.getId(), st.trim());
				StoryTag newTag = tagRepository.findOne(tagId);
				if (newTag == null) {
					StoryTag createdTag = new StoryTag(tagId);
					tagRepository.save(createdTag);
				}
			}

		Collection<Long> authors = story.getAuthors();

		if (authors != null) {
			for (Long author : authors) {
				StoryAuthorId storyId = new StoryAuthorId(story.getId(), author);
				StoryAuthor newAuthor = authorRepo.findOne(storyId);
				if (newAuthor == null) {
					StoryAuthor createdAuthor = new StoryAuthor(storyId);
					authorRepo.save(createdAuthor);
				}
			}
		}

		Story updatedStory = new Story(story);
		Calendar now = Calendar.getInstance();
		updatedStory.setLastupdate(now);

		return storyRepository.save(updatedStory);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		Story story = storyRepository.findOne(id);
		if (story == null)
			return;
		story.setStatus(0);
		storyRepository.save(story);
		chapterRepo.deleteStoryChapters(story.getId());
	}

	@Override
	public Collection<Story> findUserStories(Long id) {
		Collection<Story> userStories = storyRepository.findUserStories(id);
		return userStories;
	}

	@Override
	public Collection<Stories> findStoriesByCategory(Long id) {
		List<Object[]> map = storyRepository.findStoriesByCategory(id);
		Collection<Stories> stories = new ArrayList<Stories>();
		for (Object[] obj : map) {
			Story story = (Story) obj[0];
			User user = (User) obj[1];
			Collection<String> tags = tagRepository.getStoriesTag(story.getId());
			story.setOwnerrate(story.getOwnerrate());

			stories.add(new Stories(story, tags, user.getUserName()));
		}
		return stories;
	}

	@Override
	public Collection<Stories> findAllWithDetails() {
		/*
		 * List<Object[]> map = storyRepository.findAllWithDetails();
		 * Collection<Stories> stories = new ArrayList<Stories>(); for (Object[]
		 * obj : map) { Story story = (Story) obj[0]; Collection<Category>
		 * categories = categoryRepository.getStoryCategories(story.getId());
		 * Collection<String> tags = tagRepository.getStoriesTag(story.getId());
		 * story.setOwnerrate(story.getOwnerrate()); stories.add(new
		 * Stories(story, (String) obj[1], categories,tags)); } return stories;
		 */
		return getAllWithDetails();
	}

	public Collection<Stories> getAllWithDetails() {
		List<Object[]> map = storyRepository
				.getAllWithDetails(new PageRequest(0, 1000, Sort.Direction.DESC, "createdate"));
		Collection<Stories> stories = new ArrayList<Stories>();
		for (Object[] obj : map) {
			Story story = (Story) obj[0];
			Collection<Category> categories = categoryRepository.getStoryCategories(story.getId());
			Collection<String> tags = tagRepository.getStoriesTag(story.getId());
			story.setOwnerrate(story.getOwnerrate());
			stories.add(new Stories(story, (User) obj[1], categories, tags));
		}
		return stories;
	}

	@Override
	public Collection<Stories> findWithDetails() {
		List<Object[]> map = storyRepository.findWithDetails();
		Collection<Stories> stories = new ArrayList<Stories>();
		for (Object[] obj : map) {
			Story story = (Story) obj[0];
			Collection<Category> categories = categoryRepository.getStoryCategories(story.getId());
			Collection<String> tags = tagRepository.getStoriesTag(story.getId());
			story.setOwnerrate(story.getOwnerrate());
			stories.add(new Stories(story, (String) obj[1], categories, tags));
		}
		return stories;
	}

	@Override
	public Story rateStory(StoryRate newRate) {
		rateRepository.save(newRate);
		Double rate = rateRepository.calculateNewRate(newRate.getStoryID());
		Story story = storyRepository.findOne(newRate.getStoryID());
		story.setStoryrate(rate);
		return storyRepository.save(story);
	}

	@Override
	public Collection<StoryRate> findUserRates(Long id) {
		return rateRepository.getUserRate(id) == null ? null : rateRepository.getUserRate(id);
	}

	@Override
	public Collection<StoryRate> findStoryRate(Long id) {
		return rateRepository.getStoryRate(id) == null ? null : rateRepository.getStoryRate(id);
	}

	@Override
	public Story publishStory(Long id) {
		Story story = storyRepository.findOne(id);
		if (story == null)
			return null;
		story.setIsPublished(1);
		return storyRepository.save(story);

	}

	@Override
	public Integer isRatedByUser(Long storyID, Long userID) {
		StoryRateId id = new StoryRateId(storyID, userID);
		StoryRate rate = rateRepository.findOne(id);
		if (rate == null)
			return null;
		else
			return rate.getRate();
	}

	@Override
	public Story unPublishStory(Long id) {
		Story story = storyRepository.findOne(id);
		if (story == null)
			return null;
		story.setIsPublished(0);
		return storyRepository.save(story);
	}

	/*
	 * @Override public Integer incrementCounter(StoryReadId id) { StoryRead
	 * storyRead = readRepository.findOne(id); if(storyRead!=null) return null;
	 * Story story = storyRepository.findOne(id.getStoryID()); Integer count =
	 * story.getReadcount(); count++; story.setReadcount(count);
	 * storyRepository.save(story);
	 * 
	 * return count; }
	 */

	@Override
	public Story rateOwnStory(StoryRate rate) {
		Long storyID = rate.getStoryID();
		Story story = storyRepository.findOne(storyID);
		Long userID = story.getOwnerID();

		if (rate.getUserID() != userID)
			return null;

		Double newRate = rate.getRate().doubleValue();
		story.setOwnerrate(newRate);
		storyRepository.save(story);
		return story;
	}

	@Override
	public Collection<Stories> getStoriesByTag(String tag) {
		Collection<Stories> stories = tagRepository.getStoriesByTag(tag);
		if (stories == null || stories.size() == 0)
			return null;
		return stories;
	}

	@Override
	public Collection<String> getStoryTags(Long id) {
		Collection<String> tags = tagRepository.getStoriesTag(id);
		if (tags == null || tags.size() == 0)
			return null;
		return tags;
	}

	@Override
	public StoryRead readStory(StoryReadId id) {

		if (readRepository.findOne(id) != null)
			return null;
		Story story = storyRepository.findOne(id.getStoryID());
		if (story.getOwnerID() == id.getUserID() || story == null)
			return null;
		StoryRead s = new StoryRead(id);
		Calendar now = Calendar.getInstance();
		s.setReadTime(now);
		int readCount = story.getReadcount() == null ? 0 : story.getReadcount();
		readCount++;
		story.setReadcount(readCount);
		storyRepository.save(story);
		return readRepository.save(s);

	}

	@Override
	public boolean startCollective(Long id) {
		Story story = storyRepository.findOne(id);
		if (story == null)
			return false;
		story.setIsActive(1);
		storyRepository.save(story);

		return true;
	}

	@Override
	public boolean finishCollective(Long id) {
		Story story = storyRepository.findOne(id);
		if (story == null)
			return false;
		story.setIsActive(2);
		storyRepository.save(story);

		return true;
	}

	@Override
	public Collection<Stories> getNotStartedCollective() {
		return getCollective(0);
	}

	@Override
	public Collection<Stories> getStartedCollective() {
		return getCollective(1);
	}

	@Override
	public Collection<Stories> getFinishedCollective() {
		return getCollective(2);
	}

	private Collection<Stories> getCollective(int active) {
		List<Object[]> map = storyRepository.findCollective(active);
		Collection<Stories> stories = new ArrayList<Stories>();
		for (Object[] obj : map) {
			Story story = (Story) obj[0];
			User user = (User) obj[1];
			Collection<String> tags = tagRepository.getStoriesTag(story.getId());
			story.setOwnerrate(story.getOwnerrate());

			stories.add(new Stories(story, tags, user.getUserName()));
		}
		return stories;
	}

	@Override
	public StoryAuthor addWriter(StoryAuthor s) {
		if (storyRepository.findOne(s.getStoryID()) == null
				|| storyRepository.findOne(s.getStoryID()).getIsCollective() == 0
				|| userRepo.findOne(s.getUserID()) == null)
			return null;
		if (authorRepo.findOne(new StoryAuthorId(s)) != null
				&& authorRepo.findOne(new StoryAuthorId(s)).getStatus() == 1)
			return null;
		s.setStatus(1);

		return authorRepo.save(s);
	}

	@Override
	public Collection<User> getStoryWriters(Long id) {
		if (storyRepository.findOne(id) == null)
			return null;
		Collection<User> users = authorRepo.getStoryWriters(id);
		for (User user : users) {
			user.setPassword("");
			user.setRoles(null);
		}
		return users;
	}

	@Override
	public ResponseOrderedStories getOrderedStories(int id) {
		ResponseOrderedStories response = new ResponseOrderedStories();
		if (id == 0) {
			List<Object[]> newStoriesMap = storyRepository
					.getStoriesWithPaging(new PageRequest(0, 15, Sort.Direction.DESC, "createdate"));
			Collection<Stories> newStories = new ArrayList<Stories>();
			for (Object[] obj : newStoriesMap) {
				Story story = (Story) obj[0];
				User user = (User) obj[1];
				newStories.add(new Stories(story, user.getUserName()));
			}
			List<Object[]> mostRatedMap = storyRepository
					.getStoriesWithPaging(new PageRequest(0, 15, Sort.Direction.DESC, "storyrate"));
			Collection<Stories> mostRatedStories = new ArrayList<Stories>();
			for (Object[] obj : mostRatedMap) {
				Story story = (Story) obj[0];
				User user = (User) obj[1];
				mostRatedStories.add(new Stories(story, user.getUserName()));
			}
			List<Object[]> mostViewedMap = storyRepository
					.getStoriesWithPaging(new PageRequest(0, 15, Sort.Direction.DESC, "readcount"));
			Collection<Stories> mostViewedStories = new ArrayList<Stories>();
			for (Object[] obj : mostViewedMap) {
				Story story = (Story) obj[0];
				User user = (User) obj[1];
				mostViewedStories.add(new Stories(story, user.getUserName()));
			}
			response.setNewStories(newStories);
			response.setMostRatedStories(mostRatedStories);
			response.setMostViewedStories(mostViewedStories);
		}

		if (id == 1) {
			List<Object[]> newStoriesMap = storyRepository
					.getStoriesWithPaging(new PageRequest(0, 50, Sort.Direction.DESC, "createdate"));
			Collection<Stories> newStories = new ArrayList<Stories>();
			for (Object[] obj : newStoriesMap) {
				Story story = (Story) obj[0];
				User user = (User) obj[1];
				newStories.add(new Stories(story, user.getUserName()));
			}
			response.setNewStories(newStories);
		}

		if (id == 2) {
			List<Object[]> mostRatedMap = storyRepository
					.getStoriesWithPaging(new PageRequest(0, 50, Sort.Direction.DESC, "storyrate"));
			Collection<Stories> mostRatedStories = new ArrayList<Stories>();
			for (Object[] obj : mostRatedMap) {
				Story story = (Story) obj[0];
				User user = (User) obj[1];
				mostRatedStories.add(new Stories(story, user.getUserName()));
			}
			response.setMostRatedStories(mostRatedStories);
		}

		if (id == 3) {
			List<Object[]> mostViewedMap = storyRepository
					.getStoriesWithPaging(new PageRequest(0, 50, Sort.Direction.DESC, "readcount"));
			Collection<Stories> mostViewedStories = new ArrayList<Stories>();
			for (Object[] obj : mostViewedMap) {
				Story story = (Story) obj[0];
				User user = (User) obj[1];
				mostViewedStories.add(new Stories(story, user.getUserName()));
			}
			response.setMostViewedStories(mostViewedStories);
		}

		return response;
	}
	
	@Override
	public ResponseOrderedStories getMobileOrdered(int id, int page) {
		ResponseOrderedStories response = new ResponseOrderedStories();
		if (id == 0) {
			List<Object[]> newStoriesMap = storyRepository
					.getStoriesWithPaging(new PageRequest(page, 15, Sort.Direction.DESC, "createdate"));
			Collection<Stories> newStories = new ArrayList<Stories>();
			for (Object[] obj : newStoriesMap) {
				Story story = (Story) obj[0];
				User user = (User) obj[1];
				newStories.add(new Stories(story, user.getUserName()));
			}
			List<Object[]> mostRatedMap = storyRepository
					.getStoriesWithPaging(new PageRequest(page, 15, Sort.Direction.DESC, "storyrate"));
			Collection<Stories> mostRatedStories = new ArrayList<Stories>();
			for (Object[] obj : mostRatedMap) {
				Story story = (Story) obj[0];
				User user = (User) obj[1];
				mostRatedStories.add(new Stories(story, user.getUserName()));
			}
			List<Object[]> mostViewedMap = storyRepository
					.getStoriesWithPaging(new PageRequest(page, 15, Sort.Direction.DESC, "readcount"));
			Collection<Stories> mostViewedStories = new ArrayList<Stories>();
			for (Object[] obj : mostViewedMap) {
				Story story = (Story) obj[0];
				User user = (User) obj[1];
				mostViewedStories.add(new Stories(story, user.getUserName()));
			}
			response.setNewStories(newStories);
			response.setMostRatedStories(mostRatedStories);
			response.setMostViewedStories(mostViewedStories);
		}

		if (id == 1) {
			List<Object[]> newStoriesMap = storyRepository
					.getStoriesWithPaging(new PageRequest(page, 30, Sort.Direction.DESC, "createdate"));
			Collection<Stories> newStories = new ArrayList<Stories>();
			for (Object[] obj : newStoriesMap) {
				Story story = (Story) obj[0];
				User user = (User) obj[1];
				newStories.add(new Stories(story, user.getUserName(),user.getImage()));
			}
			response.setNewStories(newStories);
		}

		if (id == 2) {
			List<Object[]> mostRatedMap = storyRepository
					.getStoriesWithPaging(new PageRequest(page, 30, Sort.Direction.DESC, "storyrate"));
			Collection<Stories> mostRatedStories = new ArrayList<Stories>();
			for (Object[] obj : mostRatedMap) {
				Story story = (Story) obj[0];
				User user = (User) obj[1];
				mostRatedStories.add(new Stories(story, user.getUserName(),user.getImage()));
			}
			response.setMostRatedStories(mostRatedStories);
		}

		if (id == 3) {
			List<Object[]> mostViewedMap = storyRepository
					.getStoriesWithPaging(new PageRequest(page, 30, Sort.Direction.DESC, "readcount"));
			Collection<Stories> mostViewedStories = new ArrayList<Stories>();
			for (Object[] obj : mostViewedMap) {
				Story story = (Story) obj[0];
				User user = (User) obj[1];
				mostViewedStories.add(new Stories(story, user.getUserName()));
			}
			response.setMostViewedStories(mostViewedStories);
		}

		return response;
	}
}
