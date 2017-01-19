package com.eventz.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.eventz.model.Stories;
import com.eventz.model.Story;
import com.eventz.model.User;

@Repository
public interface StoryRepository extends JpaRepository<Story,Long> {
	public final static String FIND_USER_STORIES_QUERY = "SELECT s " + 
            "FROM Story s " +
            "WHERE s.ownerID = :owner "
            + "AND s.status = 1 ";
	public final static String FIND_STORIES_BY_CATEGORY = "SELECT s,u " + 
            "FROM Story s, User u " +
            "WHERE s.categoryID = :category " + "AND s.ownerID = u.id "
            + "AND s.status = 1 " + "AND u.status = 1 " + "AND s.isPublished = 1 " + "AND s.isCollective = 0 order by s.createdate";
	public final static String FIND_ALL_WITH_DETAILS = "SELECT s,CONCAT(u.name,' ',u.surname) as username " + 
            "FROM Story s, User u " +
            "WHERE s.ownerID = u.id "
            + "AND s.status = 1 "
            + "AND u.status = 1 "
            + "AND s.isPublished = 1";
	public final static String GET_ALL_WITH_DETAILS = "SELECT s,u " + 
            "FROM Story s, User u " +
            "WHERE s.ownerID = u.id "
            + "AND s.status = 1 "
            + "AND u.status = 1 "
            + "AND s.isPublished = 1 "
            + "AND s.isCollective = 0";
	public final static String FIND_WITH_DETAILS = "SELECT s,CONCAT(u.name,' ',u.surname) as username " + 
            "FROM Story s, User u " +
            "WHERE s.ownerID = u.id "
            + "AND s.status = 1 "
            + "AND u.status = 1 ";
	public final static String FIND_ALL = "SELECT s "
			+ "FROM Story s "
			+ "WHERE s.status = 1";
	public final static String FIND_ONE = "SELECT new Stories(s,CONCAT(u.name,' ',u.surname) as USERNAME) "
			+ "FROM Story s,User u "
			+ "WHERE s.status = 1 "
			+ "AND u.status = 1 "
			+ "AND s.ownerID = u.id "
			+ "AND s.id = :story";
	public final static String FIND_COLLECTIVE = "SELECT s,u "
			+ "FROM Story s,User u "
			+ "WHERE s.status = 1 "
			+ "AND s.ownerID = u.id "
			+ "AND u.status = 1 "
			+ "AND s.isCollective = 1 "
			+ "AND s.isActive = :active order by s.createdate";
	public final static String FIND_STARTED_COLLECTIVE = "SELECT s "
			+ "FROM Story s "
			+ "WHERE s.status = 1 "
			+ "AND s.isActive = 1 ";
	public final static String FIND_FINISHED_COLLETIVE = "SELECT s "
			+ "FROM Story s "
			+ "WHERE s.status = 1 "
			+ "AND s.isActive = 2 ";
	public final static String GET_COLLECTIVE_AUTHORS ="SELECT a.userID "
			+ "FROM Story s,StoryAuthor a "
			+ "WHERE a.status = 1 "
			+ "AND s.status = 1 "
			+ "AND a.storyID = s.id "
			+ "AND s.isCollective = 1 "
			+ "AND s.id = :story";
	public final static String SEARCH_STORY = "SELECT s "
			+ "FROM Story s "
			+ "WHERE s.status = 1 "
			+ "AND s.name like :str ";

	
	@Query(FIND_USER_STORIES_QUERY)
	public Collection<Story> findUserStories(@Param("owner") Long id);
	@Query(FIND_STORIES_BY_CATEGORY)
	public List<Object[]> findStoriesByCategory(@Param("category") Long id);
	@Query(FIND_ALL_WITH_DETAILS)
	public List<Object[]> findAllWithDetails();
	@Query(GET_ALL_WITH_DETAILS)
	public List<Object[]> getAllWithDetails(Pageable pageable);
	@Query(FIND_WITH_DETAILS)
	public List<Object[]> findWithDetails();
	@Query(FIND_ALL)
	public Collection<Story> findAllStory();
	@Query(FIND_ONE)
	public Stories findOneStory(@Param("story") Long id);
	@Query(FIND_COLLECTIVE)
	public List<Object[]> findCollective(@Param("active") int id);
	@Query(FIND_STARTED_COLLECTIVE)
	public Collection<Story> findStartedCollective();
	@Query(FIND_FINISHED_COLLETIVE)
	public Collection<Story> findFinishedCollective();
	@Query(GET_COLLECTIVE_AUTHORS)
	public Collection<User> getStoryAuthors(@Param("story") Long id);
	@Query(SEARCH_STORY)
	public ArrayList<Story> searchStory(@Param("str") String str);
	@Query(GET_ALL_WITH_DETAILS)
	public List<Object[]> getStoriesWithPaging(Pageable pageable);

}
