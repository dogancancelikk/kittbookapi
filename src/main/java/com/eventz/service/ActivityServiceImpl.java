package com.eventz.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.eventz.model.Activity;
import com.eventz.model.ActivityDTO;
import com.eventz.model.User;
import com.eventz.repository.ActivityDtoRepository;
import com.eventz.repository.ActivityRepository;
import com.eventz.response.ResponseActivity;

@Service
public class ActivityServiceImpl implements ActivityService {

	@Autowired
	ActivityRepository activityRepository;
	@Autowired
	ActivityDtoRepository dtoRepository;
	@Autowired
	UserService userService;
	
	@Override
	public Collection<ResponseActivity> getUserActivies(Long id) {
		List<Object[]> map = dtoRepository.getUserActivities(id);
		Collection<ResponseActivity> activities = new ArrayList<ResponseActivity>();
		for (Object[] obj : map) {
			Activity activity = (Activity) obj[0];
			User user = (User) obj[1];
			ResponseActivity dto = new ResponseActivity();
			dto.setActivityType(activity.getActivityType());
			dto.setActivityUserID(activity.getActivityUserID());
			dto.setActivityUserName(user.getUserName());
			dto.setId(activity.getId());
			dto.setCreatedate(activity.getCreatedate());
			dto.setUsername(activity.getUsername());
			String text = "";
			String activityDetailLink = "";
			if (activity.getActivityType() == 1) {
				text = user.getUserName() + " yeni bir kitap oluşturdu.";
				activityDetailLink = "#/story/" + activity.getStoryID();
			} else if (activity.getActivityType() == 2) {
				text = user.getUserName() + " yeni bir kolektif kitap oluşturdu.";
				activityDetailLink = "#/story/" + activity.getStoryID();
			} else if (activity.getActivityType() == 3) {
				text = user.getUserName() + ", " + activity.getUsername() + " adlı hesabı takip etmeye başladı.";
				activityDetailLink = "#/profile/" + activity.getUsername();
			} else if (activity.getActivityType() == 4) {
				text = user.getUserName() + " yeni bir durum paylaştı.";
				activityDetailLink = "#/profile/" + activity.getActivityUserID();
			} else if (activity.getActivityType() == 5) {
				text = user.getUserName() + " yeni bir bölüm ekledi.";
				activityDetailLink = "#/chapter/" + activity.getChapterID();
			}
			dto.setText(text);
			dto.setActivityDetailLink(activityDetailLink);
			activities.add(dto);
		}
		return activities;
	}

	@Override
	public Activity createActivity(Activity a) {
		Calendar now = Calendar.getInstance();
		a.setLastupdate(now);
		a.setCreatedate(now.getTime());
		if(a.getUserID() != null && !StringUtils.hasText(a.getUsername())) {
			User followedUser = userService.findOne(a.getUserID());
			a.setUsername(followedUser.getUserName());
		}
		return activityRepository.save(a);
	}

	@Override
	public Collection<ActivityDTO> getUserLastsActivity(Long id) {
		return dtoRepository.findUserActivity(id);
	}
	
	@Override
	public Collection<Activity> getAllActivity() {
		return activityRepository.findAll();
	}

}
