package com.eventz.repository;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.eventz.model.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
	public final static String FIND_ALL = "SELECT u "
			+ "FROM User u "
			+ "WHERE u.status = 1";
	public final static String FIND_ONE = "SELECT u "
			+ "FROM User u "
			+ "WHERE u.status = 1 "
			+ "AND u.id = :user";
	public final static String FIND_BY_USERNAME = "SELECT u "
			+ "FROM User u "
			+ "WHERE u.status = 1 "
			+ "AND u.userName = :user";
	public final static String FIND_BY_FACEBOOKID = "SELECT u "
			+ "FROM User u "
			+ "WHERE u.status = 1 "
			+ "AND u.facebookID = :user";
	public final static String FIND_BY_GOOGLEID = "SELECT u "
			+ "FROM User u "
			+ "WHERE u.status = 1 "
			+ "AND u.googleID = :user";
	public final static String FIND_BY_EMAIL = "SELECT u "
			+ "FROM User u "
			+ "WHERE u.status = 1 "
			+ "AND u.email = :email";
	public final static String SEARCH_USER = "SELECT u "
			+ "FROM User u "
			+ "WHERE u.status = 1 "
			+ "AND "
			+ "("
			+ "u.userName like :user "
			+ "OR u.name like :name "
			+ "OR u.surname like :surname"
			+ ")";
	public final static String SEARCH_USER_BY_USERNAME = "SELECT u "
			+ "FROM User u "
			+ "WHERE u.status = 1 "
			+ "AND u.userName like :user ";
	
	@Query(FIND_ONE)
	public User findOneUser(@Param("user") Long id);
	@Query(FIND_BY_USERNAME)
	public User findByUserName(@Param("user") String userName);
	@Query(FIND_BY_FACEBOOKID)
	public User findByFacebookId(@Param("user") String facebookId);
	@Query(FIND_BY_GOOGLEID)
	public User findByGoogleId(@Param("user") String googleId);
	@Query(FIND_ALL)
	public Collection<User> findAllUser();
	@Query(FIND_BY_EMAIL)
	public User findByMail(@Param("email") String email);
	@Query(SEARCH_USER)
	public ArrayList<User> searchUser(@Param("user") String user,@Param("name") String name,@Param("surname") String surname);
	@Query(SEARCH_USER_BY_USERNAME)
	public ArrayList<User> searchUserByUsername(@Param("user") String user);
	
}