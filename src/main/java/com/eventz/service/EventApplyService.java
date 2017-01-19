package com.eventz.service;

import java.util.ArrayList;
import java.util.Collection;

import com.eventz.model.EventApply;
import com.eventz.model.EventApplyDTO;
import com.eventz.model.UserApply;

public interface EventApplyService {

	EventApply create(EventApply e);
	
	EventApply update(EventApply eventApply);
	
	EventApply findOne(Long id);
	
	Collection<EventApply> findAll();
	
	Collection<EventApply> findByType(Integer id);
	
	Collection<EventApplyDTO> findByEvent(Long id);
	
	EventApply approve(Long id);
	
	EventApply reject(Long id);
	
	ArrayList<UserApply> getAppliedUser(Long id);
	
	EventApply isApplied(EventApplyDTO e);
}
