package com.eventz.task;

import java.util.ArrayList;
import java.util.Calendar;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.eventz.model.Event;
import com.eventz.model.Story;
import com.eventz.repository.EventRepository;
import com.eventz.repository.StoryRepository;


@Component
public class CollectiveStoryArrangementTask {

	EventRepository eventRepo;
	StoryRepository storyRepo;

	// @Scheduled(fixedRate = 5000)
	@Scheduled(cron = "0 0 12 * * ?")
	public void findFinishedEvents() {
		ArrayList<Event> events = eventRepo.getFinishedEvents(Calendar.getInstance());
		for(Event e : events)
		{
			e.setIsActive(0);
			if(e.getType()==2)
			{
				Event newEvent = new Event(e);
				newEvent.setId(0L);
				newEvent.setType(1);
				Story s = new Story();
				s.setOwnerID(newEvent.getAuthorID());
				s.setCreatedate(Calendar.getInstance());
				s.setLastupdate(Calendar.getInstance());
				s.setName(newEvent.getName());
				if(newEvent.getImage() != null)
					s.setImage(newEvent.getImage());
				Story newStory = storyRepo.save(s);
				newEvent.setCollectiveBookID(newStory.getId());
				eventRepo.save(newEvent);
			}
		}
	}
}
