package com.eventz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eventz.model.Activity;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {
	

}
