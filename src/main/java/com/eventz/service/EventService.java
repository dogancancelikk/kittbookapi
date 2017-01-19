package com.eventz.service;

import java.util.Collection;

import com.eventz.model.Event;

public interface EventService {

	Event create(Event e);
	
	Event update(Event e);
	
	Event inActivate(Event e);
	
	Event findOne(Long id);
	
	Collection<Event> find();
	
	Collection<Event> findByType(Integer id);
	
	Integer getApplyCount(Long id);
	
	Collection<Event> getActives();
	
	Collection<Event> getInActives();
	
	Collection<Event> getUserAppliedEvents(Long id);
	
	Collection<Event> getNotStartedEvents();
	
	Collection<Event> getStartedEvents();
}
