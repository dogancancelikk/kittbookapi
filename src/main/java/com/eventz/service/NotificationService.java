package com.eventz.service;

import java.util.Collection;

import com.eventz.model.Notification;
import com.eventz.model.NotificationDTO;
import com.eventz.response.ResponseUserNotifications;

public interface NotificationService {

	Collection<ResponseUserNotifications> getUserNotifications(String username);

	Notification createNotification(NotificationDTO notification);

	Notification readNotification(Long id);

	void deleteNotification(Long id);

}
