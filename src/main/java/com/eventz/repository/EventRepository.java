package com.eventz.repository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.eventz.model.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
	
	public static final String FIND_ALL = "SELECT e "
			+ "FROM Event e "
			+ "WHERE e.status = 1 "
			+ "AND e.isActive = 1 "
			+ "ORDER BY e.createDate DESC";
	public static final String FIND_ONE = "SELECT e "
			+ "FROM Event e "
			+ "WHERE e.status = 1 "
			+ "AND e.isActive = 1 "
			+ "AND e.id = :event";
	public static final String FIND_BY_TYPE = "SELECT e "
			+ "FROM Event e "
			+ "WHERE e.status = 1 "
			+ "AND e.isActive = 1 "
			+ "AND e.type = :type order by e.createDate DESC";
	public static final String FIND_ACTIVES = "SELECT e "
			+ "FROM Event e "
			+ "WHERE e.status = 1 "
			+ "AND e.startDate<:now "
			+ "AND e.endDate>:now order by e.createDate DESC ";
	public static final String FIND_INACTIVES = "SELECT e "
			+ "FROM Event e "
			+ "WHERE e.status =1 "
			+ "AND (e.startDate>:now OR e.endDate<:now) order by e.createDate DESC ";
	public static final String FIND_USER_APPLIED_EVENTS = "SELECT distinct e "
			+ "FROM EventApply a, Event e "
			+ "WHERE e.status = 1 "
			+ "AND a.status = 1 "
			+ "AND e.id = a.activityID "
			+ "AND a.userID = :user";
	public static final String FIND_FINISHED_EVENTS = "SELECT e "
			+ "FROM Event e "
			+ "WHERE e.status = 1 "
			+ "AND e.isActive = 1 "
			+ "AND e.endDate<:now";
	public static final String FIND_NOTSTARTED_EVENTS ="SELECT e "
			+"FROM Event e "
			+"WHERE e.status=1 "
			+"AND e.startDate>:now";
	public static final String FIND_STARTED_EVENTS ="SELECT e "
			+"FROM Event e "
			+"WHERE e.status=1 "
			+"AND e.startDate<:now AND e.endDate>:now";
	
	@Query(FIND_ALL)
	public Collection<Event> findAllEvents();
	@Query(FIND_ONE)
	public Event findOneEvent(@Param("event") Long id);
	@Query(FIND_BY_TYPE)
	public Collection<Event> findByType(@Param("type") Integer id);
	@Query(FIND_ACTIVES)
	public Collection<Event> findActives(@Param("now") Calendar now);
	@Query(FIND_INACTIVES)
	public Collection<Event> findInActives(@Param("now") Calendar now);
	@Query(FIND_USER_APPLIED_EVENTS)
	public Collection<Event> findUserAppliedEvents(@Param("user") Long id);
	@Query(FIND_FINISHED_EVENTS)
	ArrayList<Event> getFinishedEvents(@Param("now") Calendar now);
	@Query(FIND_NOTSTARTED_EVENTS)
	public Collection<Event> findNotStartedEvents(@Param("now") Calendar now);
	@Query(FIND_STARTED_EVENTS)
	public Collection<Event> findStartedEvents(@Param("now") Calendar now);

}
