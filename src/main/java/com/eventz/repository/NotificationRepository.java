package com.eventz.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.eventz.model.Notification;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
	
	public final static String FIND_USER_NOTIFICATIONS = "SELECT n, u FROM Notification n, User u WHERE n.sendBy = u.userName AND n.username = :username ORDER BY n.createDate DESC";

	Collection<Notification> findByUsernameAndHasReadOrderByCreateDateDesc(String username,Integer read);
	
	Collection<Notification> findByUsernameOrderByCreateDateDesc(String username);
	
	@Query(FIND_USER_NOTIFICATIONS)
	public List<Object[]> findUserNotifiactionsWithDetail(@Param("username") String username);
}
