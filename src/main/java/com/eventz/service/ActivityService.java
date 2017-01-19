package com.eventz.service;

import java.util.Collection;

import com.eventz.model.Activity;
import com.eventz.model.ActivityDTO;
import com.eventz.response.ResponseActivity;

public interface ActivityService {
	
	
	Collection <ResponseActivity> getUserActivies(Long id);
	
	Activity createActivity(Activity a);
	
	Collection<ActivityDTO> getUserLastsActivity(Long id);
	
	Collection<Activity> getAllActivity();

}
