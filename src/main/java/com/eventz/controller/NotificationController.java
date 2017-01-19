package com.eventz.controller;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.eventz.model.Notification;
import com.eventz.model.NotificationDTO;
import com.eventz.response.ResponseUserNotifications;
import com.eventz.service.NotificationService;

@CrossOrigin(origins = "http://kittbook.com")
@RestController
public class NotificationController {

	@Autowired
	NotificationService notificationService;

	private List<SseEmitter> emitters = new CopyOnWriteArrayList<>();
	private NotificationController controller;

	private NotificationController() {
	}

	public NotificationController getInstance() {
		if (controller == null)
			controller = new NotificationController();
		return controller;
	}

	@RequestMapping("/notifications")
	public SseEmitter notifications() {
		SseEmitter sseEmitter = new SseEmitter();

		emitters.add(sseEmitter);

		sseEmitter.onCompletion(() -> emitters.remove(sseEmitter));

		return sseEmitter;
	}

	@RequestMapping(value = "/new-notification", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void postNotification(@RequestBody NotificationDTO notification) {

		notificationService.createNotification(notification);

		for (SseEmitter emitter : emitters) {
			try {
				emitter.send(SseEmitter.event().name(notification.getUsername()).data(notification.getText()));
			} catch (Exception e) {
				emitters.remove(emitter);
			}
		}
	}

	@RequestMapping(value = "/notification/read", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Notification> readNotification(@RequestBody Notification request) {
		Notification notification = notificationService.readNotification(request.getId());
		if (notification == null)
			return new ResponseEntity<Notification>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<Notification>(notification, HttpStatus.OK);
	}

	@RequestMapping(value = "/notification/delete/{id}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> deleteNotification(@PathVariable("id") Long id) {
		notificationService.deleteNotification(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "/notification/get/{username}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<ResponseUserNotifications>> getUserNotifications(@PathVariable("username") String username) {
		return new ResponseEntity<Collection<ResponseUserNotifications>>(notificationService.getUserNotifications(username), HttpStatus.OK);
	}
}
