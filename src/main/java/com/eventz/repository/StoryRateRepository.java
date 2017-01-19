package com.eventz.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.eventz.model.StoryRate;
import com.eventz.model.StoryRateId;

@Repository
public interface StoryRateRepository extends JpaRepository<StoryRate,StoryRateId> {

	public final static String FIND_NEW_RATE =
			"SELECT avg(r.rate) "
			+ "FROM StoryRate r "
			+ "WHERE r.storyID = :story";
	public final static String USER_RATE =
			"SELECT s "
			+ "FROM StoryRate s "
			+ "WHERE s.userID = :user";
	public final static String STORY_RATE =
			"SELECT s "
			+ "FROM StoryRate s "
			+ "WHERE s.storyID = :story";
	
	@Query(FIND_NEW_RATE)
	public Double calculateNewRate(@Param("story") Long id);
	@Query(USER_RATE)
	public Collection<StoryRate> getUserRate(@Param("user") Long id);
	@Query(STORY_RATE)
	public Collection<StoryRate> getStoryRate(@Param("story") Long id);
}
