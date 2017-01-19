package com.eventz.service;

import java.util.Collection;

import com.eventz.model.Message;
import com.eventz.model.MessageDTO;
import com.eventz.model.UserDTO;

public interface MessageService {
	
	Collection<Message> getMessages(MessageDTO m);
	
	Collection<Message> sendMessage (MessageDTO m);
	
	Collection<Message> deleteMessage (MessageDTO m);
	
	Collection<UserDTO> getLastMessages(Long id);
	
	void deleteAllMessages(MessageDTO m);
	
	Message readByReceiver (Long messageId);

	void readAllMessagesOfSender(Message m);
}
