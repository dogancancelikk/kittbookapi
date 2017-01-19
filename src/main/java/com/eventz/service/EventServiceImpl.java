package com.eventz.service;

import java.util.Calendar;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventz.model.Event;
import com.eventz.repository.EventApplyRepository;
import com.eventz.repository.EventRepository;

@Service
public class EventServiceImpl implements EventService {

	@Autowired
	EventRepository eventRepository;
	@Autowired
	EventApplyRepository applyRepository;

	public static final String DEFAULT_IMAGE = "http://kittbook.com/images/kittbook-default.png";

	@Override
	public Event create(Event e) {
		if (e.getImage() == null) {
			e.setImage(DEFAULT_IMAGE);
		}

		if (eventRepository.findOne(e.getId()) != null)
			return null;
		e.setCreateDate(Calendar.getInstance());
		if (e.getStatus() == null)
			e.setStatus(1);
		return eventRepository.save(e);
	}

	@Override
	public Event update(Event e) {
		if (e.getId() == null || eventRepository.findOne(e.getId()) == null) {
			return null;
		}
		if (e.getImage() == null) {
			e.setImage(DEFAULT_IMAGE);
		}
		return eventRepository.save(e);
	}

	@Override
	public Event findOne(Long id) {
		Event e = eventRepository.findOneEvent(id);
		if (e == null)
			return null;
		return e;
	}

	@Override
	public Collection<Event> find() {
		Collection<Event> events = eventRepository.findAllEvents();
		if (events == null)
			return null;
		return events;
	}

	@Override
	public Collection<Event> findByType(Integer id) {
		Collection<Event> events = eventRepository.findByType(id);
		if (events == null)
			return null;
		return events;
	}

	@Override
	public Event inActivate(Event e) {
		Event event = eventRepository.findOne(e.getId());
		if (event == null)
			return null;
		event.setIsActive(0);
		eventRepository.save(event);
		return event;
	}

	@Override
	public Integer getApplyCount(Long id) {
		return applyRepository.findAppliesByEvent(id) == null ? 0 : applyRepository.findAppliesByEvent(id).size();
	}

	@Override
	public Collection<Event> getActives() {
		return eventRepository.findActives(Calendar.getInstance());
	}

	@Override
	public Collection<Event> getInActives() {
		return eventRepository.findInActives(Calendar.getInstance());
	}

	@Override
	public Collection<Event> getUserAppliedEvents(Long id) {
		return eventRepository.findUserAppliedEvents(id);
	}

	@Override
	public Collection<Event> getNotStartedEvents() {
		return eventRepository.findNotStartedEvents(Calendar.getInstance());
	}

	@Override
	public Collection<Event> getStartedEvents() {
		return eventRepository.findStartedEvents(Calendar.getInstance());
	}
}
