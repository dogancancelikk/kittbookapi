package com.eventz.repository;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.eventz.model.EventApply;
import com.eventz.model.EventApplyDTO;
import com.eventz.model.UserApply;

@Repository
public interface EventApplyRepository extends JpaRepository<EventApply, Long> {
	
	public static final String FIND_ALL = "SELECT e "
			+ "FROM EventApply e "
			+ "WHERE e.status = 1 ";
	public static final String FIND_ONE = "SELECT e "
			+ "FROM EventApply e "
			+ "WHERE e.status = 1 "
			+ "AND e.id = :apply ";
	public static final String FIND_BY_TYPE = "SELECT a "
			+ "FROM EventApply a, Event e "
			+ "WHERE e.status = 1 "
			+ "AND a.status = 1 "
			+ "AND e.id = a.activityID "
			+ "AND e.type = :type ";
	public static final String FIND_BY_EVENT = "SELECT new EventApplyDTO(e,a) "
			+ "FROM EventApply a, Event e "
			+ "WHERE e.status = 1 "
			+ "AND a.status = 1 "
			+ "AND e.id = a.activityID "
			+ "AND e.id = :event ";
	public static final String GET_APPLIED_USERS = "SELECT new UserApply(u,a) "
			+ "FROM User u, EventApply a "
			+ "WHERE u.status = 1 "
			+ "AND a.status = 1 "
			+ "AND a.userID = u.id "
			+ "AND a.activityID = :act ";
	public static final String IS_APPLIED = "SELECT a "
			+ "FROM EventApply a "
			+ "WHERE a.status = 1 "
			+ "AND a.activityID = :event "
			+ "AND a.userID = :user";
	
	@Query(FIND_ALL)
	public Collection<EventApply> findAllApplies();
	@Query(FIND_ONE)
	public EventApply findOneApply(@Param("apply") Long id);
	@Query(FIND_BY_TYPE)
	public Collection<EventApply> findAppliesByType(@Param("type") Integer id);
	@Query(FIND_BY_EVENT)
	public Collection<EventApplyDTO> findAppliesByEvent(@Param("event") Long id);
	@Query(GET_APPLIED_USERS)
	public ArrayList<UserApply> findAppliedUsers(@Param("act") Long id);
	@Query(IS_APPLIED)
	public EventApply isApplied (@Param("event") Long event,@Param("user") Long user);

}
