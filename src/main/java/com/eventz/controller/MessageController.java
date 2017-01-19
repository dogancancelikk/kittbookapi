package com.eventz.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.eventz.model.Message;
import com.eventz.model.MessageDTO;
import com.eventz.model.UserDTO;
import com.eventz.service.MessageService;

@CrossOrigin(origins = "http://kittbook.com")
@RestController
public class MessageController {

	@Autowired
	MessageService messageService;

	@RequestMapping(value = "/message/get", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Collection<Message>> getMessages(@RequestBody MessageDTO dto) throws ParseException {
		Collection<Message> messages = messageService.getMessages(dto);
		if (messages == null)
			return new ResponseEntity<Collection<Message>>(HttpStatus.OK);

		return new ResponseEntity<Collection<Message>>(messages, HttpStatus.CREATED);
	}

	@MessageMapping("/chat")
	@SendTo("/topic/messages")
	@RequestMapping(value = "/message/send", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Collection<Message>> sendMessage(@RequestBody MessageDTO dto)
			throws ParseException, InterruptedException {
		// Thread.sleep(100);
		Collection<Message> messages = messageService.sendMessage(dto);
		if (messages == null)
			return new ResponseEntity<Collection<Message>>(messages, HttpStatus.OK);

		return new ResponseEntity<Collection<Message>>(messages, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/message/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Collection<Message>> deleteMessage(@RequestBody MessageDTO dto) throws ParseException {
		Collection<Message> messages = messageService.deleteMessage(dto);
		if (messages == null)
			return new ResponseEntity<Collection<Message>>(messages, HttpStatus.OK);

		return new ResponseEntity<Collection<Message>>(messages, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/message/getlastmessages/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Collection<UserDTO>> getlastmessages(@PathVariable("id") Long id) throws ParseException {
		return new ResponseEntity<Collection<UserDTO>>(messageService.getLastMessages(id), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/message/deleteall", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Collection<Message>> deleteAllMessages(@RequestBody MessageDTO dto) throws ParseException {
		messageService.deleteAllMessages(dto);

		return new ResponseEntity<Collection<Message>>(HttpStatus.OK);
	}

	@RequestMapping(value = "/message/read", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Message> readMessage(@RequestBody Message m) throws ParseException {
		Message message = messageService.readByReceiver(m.getId());
		return new ResponseEntity<Message>(message, HttpStatus.OK);
	}

	@RequestMapping(value = "/message/readall", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public void readAllMessages(@RequestBody Message m) throws ParseException {
		messageService.readAllMessagesOfSender(m);
	}
	
}
