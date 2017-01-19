package com.eventz.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventz.model.ChapterDTO;
import com.eventz.model.Event;
import com.eventz.model.EventApply;
import com.eventz.model.EventApplyDTO;
import com.eventz.model.StoryAuthor;
import com.eventz.model.UserApply;
import com.eventz.repository.EventApplyRepository;
import com.eventz.repository.EventRepository;

@Service
public class EventApplyServiceImpl implements EventApplyService {

	@Autowired
	EventApplyRepository applyRepository;
	@Autowired
	EventRepository eventRepository;
	@Autowired
	StoryService storyService;
	@Autowired
	ChapterService chapterService;
	@Override
	public EventApply create(EventApply e) {
		if(applyRepository.findOne(e.getId())!=null && applyRepository.isApplied(e.getActivityID(), e.getUserID()) != null)
			return null;
		e.setApplyDate(Calendar.getInstance());
		e.setStatus(1);
		e.setApprove(0);
		return applyRepository.save(e);
	}
	
	@Override
	public EventApply update(EventApply eventApply) {
		
		if (applyRepository.findOne(eventApply.getId()) == null)
			return null;
		return applyRepository.save(eventApply);
	}
	
	@Override
	public EventApply findOne(Long id) {
		EventApply e = applyRepository.findOneApply(id);
		if(e==null)
			return null;
		return e;
	}
	@Override
	public Collection<EventApply> findAll() {
		Collection<EventApply> applies = applyRepository.findAllApplies();
		if(applies==null)
			return null;
		return applies;
	}
	@Override
	public Collection<EventApply> findByType(Integer id) {
		Collection<EventApply> applies = applyRepository.findAppliesByType(id);
		if(applies==null)
			return null;
		return applies;
	}
	@Override
	public Collection<EventApplyDTO> findByEvent(Long id) {
		Collection<EventApplyDTO> applies = applyRepository.findAppliesByEvent(id);
		if(applies==null)
			return null;
		return applies;
	}
	@Override
	public EventApply approve(Long id) {
		EventApply apply = applyRepository.findOne(id);
		if(apply==null || apply.getStatus()==0 || apply.getApprove()==1)
			return null;
		apply.setApprove(1);
		Event e = eventRepository.findOne(apply.getActivityID());
		if(e.getType()==1)
		{
			ChapterDTO dto = new ChapterDTO();
			dto.setId(0L);
			dto.setStatus(1);
			dto.setName(apply.getTitle()==null?"":apply.getTitle());
			dto.setStoryID(e.getCollectiveBookID());
			dto.setText(apply.getText());
			dto.setImage(apply.getImage());
			dto.setUserID(apply.getUserID());
			chapterService.create(dto);
		}
		else if(e.getType()==2)
		{
			StoryAuthor s = new StoryAuthor(e.getCollectiveBookID(),apply.getUserID());
			storyService.addWriter(s);
		}
		return applyRepository.save(apply);
	}
	@Override
	public ArrayList<UserApply> getAppliedUser(Long id) {
		return applyRepository.findAppliedUsers(id);
	}
	@Override
	public EventApply reject(Long id) {
		EventApply apply = applyRepository.findOne(id);
		if(apply==null)
			return null;
		apply.setApprove(2);
		return applyRepository.save(apply);
	}
	@Override
	public EventApply isApplied(EventApplyDTO e) {
		return applyRepository.isApplied(e.getActivityID(), e.getUserID());
	}

}
