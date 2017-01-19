package com.eventz.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventz.model.Message;
import com.eventz.model.MessageDTO;
import com.eventz.model.User;
import com.eventz.model.UserDTO;
import com.eventz.repository.MessageRepository;

@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	MessageRepository messageRepository;

	@Override
	public Collection<Message> getMessages(MessageDTO m) {
		if (m.getUser() == m.getOther())
			return null;
		return messageRepository.getMessages(m.getUser(), m.getOther());
	}

	@Override
	public Collection<Message> sendMessage(MessageDTO m) {
		if (m.getSender() == m.getReceiver())
			return null;
		Message message = new Message(m);
		messageRepository.save(message);
		return messageRepository.getMessages(m.getSender(), m.getReceiver());
	}

	@Override
	public Collection<Message> deleteMessage(MessageDTO m) {
		Message message = messageRepository.findOne(m.getId());
		Long other;
		if (m.getUser() == message.getSender()) {
			message.setIsDeleteSender(1);
			other = message.getReceiver();
		} else if (m.getUser() == message.getReceiver()) {
			message.setIsDeleteReceiver(1);
			other = message.getSender();
		} else
			return null;
		messageRepository.save(message);
		return messageRepository.getMessages(m.getUser(), other);
	}

	@Override
	public Collection<UserDTO> getLastMessages(Long userId) {
		Collection<User> users = messageRepository.getUsers(userId);
		List<UserDTO> conversations = new ArrayList<UserDTO>();
		for (User user : users) {
			Collection<Message> lastMessages = messageRepository.getLastMessage(userId, user.getId());
			Message message = lastMessages.iterator().next();
			Integer hasRead = message.getSender() != userId && message.getRead() == 0 ? 0 : 1;
			user.setPassword("");
			user.setRoles(null);
			conversations.add(new UserDTO(user, message.getMessageTime(), message.getText(), hasRead, message.getSender()));
		}

		Collections.sort(conversations, new Comparator<UserDTO>() {

			@Override
			public int compare(UserDTO o1, UserDTO o2) {
				return o2.getMessageTime().compareTo(o1.getMessageTime());
			}

		});
		return conversations;
	}

	@Override
	@Transactional
	public void deleteAllMessages(MessageDTO m) {

		Collection<Long> sender = messageRepository.getMessagesIdSender(m.getUser(), m.getOther());
		Collection<Long> receiver = messageRepository.getMessagesIdReceiver(m.getUser(), m.getOther());
		if (sender.size() > 0)
			messageRepository.deleteAllMessagesSender(sender);
		if (receiver.size() > 0)
			messageRepository.deleteAllMessagesReceiver(receiver);
	}

	@Override
	public Message readByReceiver(Long messageId) {
		Message message = messageRepository.findOne(messageId);
		message.setRead(1);
		return messageRepository.save(message);
	}

	@Override
	@Transactional
	public void readAllMessagesOfSender(Message m) {
		messageRepository.readAllMessagesOfSender(m.getSender(), m.getReceiver());
	}

}
