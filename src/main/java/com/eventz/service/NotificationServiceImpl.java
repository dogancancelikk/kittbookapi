package com.eventz.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventz.model.Notification;
import com.eventz.model.NotificationDTO;
import com.eventz.model.User;
import com.eventz.repository.NotificationRepository;
import com.eventz.response.ResponseUserNotifications;

@Service
public class NotificationServiceImpl implements NotificationService {

	@Autowired
	NotificationRepository notificationRepository;

	@Override
	public Collection<ResponseUserNotifications> getUserNotifications(String username) {
		 List<Object[]> map = notificationRepository.findUserNotifiactionsWithDetail(username);
		 Collection<ResponseUserNotifications> notifications = new ArrayList<ResponseUserNotifications>();
		 for (Object[] obj : map) {
			 Notification notification = (Notification) obj[0];
			 User user = (User) obj[1];
			 ResponseUserNotifications resp = new ResponseUserNotifications();
			 BeanUtils.copyProperties(notification, resp);
			 resp.setUserImage(user.getImage());
			 notifications.add(resp);
		 }
		 return notifications;
	}

	@Override
	public Notification createNotification(NotificationDTO dto) {
		Notification notification = new Notification();
		BeanUtils.copyProperties(dto, notification);
		Calendar now = Calendar.getInstance();
		notification.setCreateDate(now);
		notification.setHasRead(0);
		return notificationRepository.save(notification);
	}

	@Override
	public Notification readNotification(Long id) {
		if (id == null || id == 0) {
			return null;
		}
		Notification notification = notificationRepository.findOne(id);
		if (notification == null || notification.getHasRead() == 1) {
			return notification;
		}
		notification.setHasRead(1);
		Notification newNotitification = new Notification();
		BeanUtils.copyProperties(notification, newNotitification);
		return notificationRepository.save(newNotitification);
	}

	@Override
	public void deleteNotification(Long id) {
		if (id != null && id != 0) {
			notificationRepository.delete(id);
		}
	}

}
