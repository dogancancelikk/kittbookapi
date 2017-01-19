package com.eventz.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.eventz.model.Stories;

public interface StoriesRepository extends JpaRepository<Stories,Long>{
	public final static String FIND_ALL_STORIES = "SELECT new Stories(s,CONCAT(u.name,' ',u.surname) as USERNAME) " + 
            "FROM Story s, User u " +
            "WHERE s.ownerID = u.id "
            + "AND s.status = 1 "
            + "AND u.status = 1";
	
	@Query(FIND_ALL_STORIES)
	public Collection<Stories> findAllStories();
}
