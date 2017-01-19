package com.eventz.service;

import java.util.Collection;

import com.eventz.model.Stories;
import com.eventz.model.Story;
import com.eventz.model.StoryAuthor;
import com.eventz.model.StoryRate;
import com.eventz.model.StoryRead;
import com.eventz.model.StoryReadId;
import com.eventz.model.User;
import com.eventz.response.ResponseOrderedStories;

public interface StoryService {

	Collection<Story> findAll();

	Stories findOne(Long id);

	Story create(Stories story);

	Story update(Stories story);

	void delete(Long id);

	Collection<Story> findUserStories(Long id);

	Collection<Stories> findStoriesByCategory(Long id);

	Collection<StoryRate> findUserRates(Long id);

	Collection<StoryRate> findStoryRate(Long id);

	Collection<Stories> findAllWithDetails();
	
	Collection<Stories> findWithDetails();

	Story rateStory(StoryRate rate);

	Story publishStory(Long id);
	
	Story unPublishStory(Long id);

	Integer isRatedByUser(Long storyID, Long userID);
	
	//Integer incrementCounter(StoryReadId id);
	
	boolean startCollective(Long id);
	
	boolean finishCollective(Long id);
	
	Story rateOwnStory(StoryRate rate);
	
	Collection<Stories> getStoriesByTag(String tag);
	
	Collection<String> getStoryTags(Long id);
	
	StoryRead readStory(StoryReadId id);
	
	Collection<Stories> getNotStartedCollective();
	
	Collection<Stories> getStartedCollective();
	
	Collection<Stories> getFinishedCollective();
	
	StoryAuthor addWriter(StoryAuthor s);
	
	Collection<User> getStoryWriters(Long id);

	ResponseOrderedStories getOrderedStories(int id);
	
	ResponseOrderedStories getMobileOrdered(int id, int page);

}
