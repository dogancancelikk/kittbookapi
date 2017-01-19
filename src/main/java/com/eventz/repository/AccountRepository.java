package com.eventz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eventz.model.User;



@Repository
public interface AccountRepository extends JpaRepository<User, Long> {
	
	
	User findByUserName (String username);
	
}
