package com.eventz.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.eventz.model.Message;
import com.eventz.model.User;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

	public static final String GET_MESSAGES = "SELECT m "
			+ "FROM Message m "
			+ "WHERE ( (m.sender = :user AND m.receiver = :other AND m.isDeleteSender = 0) "
			+ "OR (m.sender = :other AND m.receiver = :user AND m.isDeleteReceiver = 0)) "
			+ "ORDER BY m.messageTime ASC ";
	public static final String GET_MESSAGE_ID = "SELECT m.id "
			+ "FROM Message m "
			+ "WHERE ( (m.sender = :user AND m.receiver = :other AND m.isDeleteSender = 0) "
			+ "OR (m.sender = :other AND m.receiver = :user AND m.isDeleteReceiver = 0)) "
			+ "ORDER BY m.messageTime ASC ";
	public static final String GET_MESSAGE_ID_SENDER = "SELECT m.id "
			+ "FROM Message m "
			+ "WHERE m.sender = :user "
			+ "AND m.receiver = :other "
			+ "AND m.isDeleteSender = 0 "
			+ "ORDER BY m.messageTime ASC ";
	public static final String GET_MESSAGE_ID_RECEIVER = "SELECT m.id "
			+ "FROM Message m "
			+ "WHERE m.sender = :other "
			+ "AND m.receiver = :user "
			+ "AND m.isDeleteReceiver = 0 "
			+ "ORDER BY m.messageTime ASC ";
	public static final String GET_USERS = "SELECT s "
			+ "FROM User u,Message m,User s "
			+ "WHERE "
			+ "(u.id = :user AND m.isDeleteSender = 0 AND u.id = m.sender AND u.status = 1 AND s.id = m.receiver AND s.status = 1) "
			+ "OR "
			+ "(u.id = :user AND m.isDeleteReceiver = 0 AND u.id = m.receiver AND u.status = 1 and s.id = m.sender AND s.status = 1 ) "
			+ "GROUP BY s.id "	;
	public static final String GET_LAST_MESSAGE= "SELECT m "
			+ "FROM Message m "
			+ "WHERE "
			+ "((m.sender = :user AND m.receiver = :other) OR (m.sender =:other AND m.receiver=:user))"
			+ "ORDER BY m.messageTime desc";
	public static final String DELETE_ALL_MESSAGES_SENDER = "UPDATE Message m "
			+ "SET m.isDeleteSender = 1 "
			+ "WHERE m.id in :messages ";
	public static final String DELETE_ALL_MESSAGES_RECEIVER = "UPDATE Message m "
			+ "SET m.isDeleteReceiver = 1 "
			+ "WHERE m.id in :messages ";
	public static final String READ_ALL_MESSAGES_OF_SENDER = "UPDATE Message m "
			+ "SET m.readByReceiver = 1 "
			+ "WHERE m.sender = :sender "
			+ "AND  m.receiver = :receiver";
	
	
	@Query(GET_MESSAGES)
	public Collection<Message> getMessages(@Param("user") Long user, @Param("other") Long other);

	@Query(GET_MESSAGE_ID)
	public Collection<Long> getMessagesId(@Param("user") Long user, @Param("other") Long other);
	
	@Query(GET_MESSAGE_ID_RECEIVER)
	public Collection<Long> getMessagesIdReceiver(@Param("user") Long user, @Param("other") Long other);
	
	@Query(GET_MESSAGE_ID_SENDER)
	public Collection<Long> getMessagesIdSender(@Param("user") Long user, @Param("other") Long other);
	
	@Query(GET_USERS)
	public Collection<User> getUsers(@Param("user") Long user);

	@Query(GET_LAST_MESSAGE)
	public Collection<Message> getLastMessage(@Param("user") Long user, @Param("other") Long other);
	
	@Modifying
	@Query(DELETE_ALL_MESSAGES_SENDER)
	public void deleteAllMessagesSender(@Param("messages") Collection<Long> messages);
	
	@Modifying
	@Query(DELETE_ALL_MESSAGES_RECEIVER)
	public void deleteAllMessagesReceiver(@Param("messages") Collection<Long> messages);
	
	@Modifying
	@Query(READ_ALL_MESSAGES_OF_SENDER)
	public void readAllMessagesOfSender(@Param("sender") Long senderId, @Param("receiver") Long receiverId);
}
