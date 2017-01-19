package com.eventz.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.eventz.model.Activity;
import com.eventz.model.ActivityDTO;

@Repository
public interface ActivityDtoRepository extends JpaRepository<ActivityDTO, Long> {

	public final static String USER_ACTIVITIES ="SELECT a,u "
			+ "FROM Activity a,User u "
			+ "WHERE a.activityUserID = :user "
			+ "AND a.activityUserID = u.id "
			+ "ORDER BY a.createdate desc ";
	public static final String USER_ACTIVITY =
			"SELECT "
			+ "new ActivityDTO"
			+ "(a.id as id,u.userName as activityUserName,s.name as storyName,c.name as chapterName,r.userName as userName,a.activityType as activityType) "
			+ "FROM Activity a,User u,Story s,Chapter c, User r " 
			+ "WHERE a.activityUserID = :user "
			+ "AND c.id = a.chapterID "
			+ "AND a.storyID = s.id "
			+ "AND a.activityUserID = u.id "
			+ "AND a.userID = r.id "
			+ "AND u.status = 1 "
			+ "AND s.status = 1 "
			+ "AND c.status = 1 "
			+ "AND r.status = 1";
	public final static String GET_USER_LAST_ACTIVITIES = "SELECT a " + 
            "FROM Activity a " +
            "WHERE a.activityUserID = :user " +
            "ORDER BY a.lastupdate desc ";
	
	@Query(USER_ACTIVITY)
	public Collection<ActivityDTO> findUserActivity (@Param("user") Long id);
	
	@Query(USER_ACTIVITIES)
	public List<Object[]> getUserActivities(@Param("user") Long id);
	
	@Query(GET_USER_LAST_ACTIVITIES)
	Collection<Activity> getUserActivity(@Param("user") Long id);
}
