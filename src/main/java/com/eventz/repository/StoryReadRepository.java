package com.eventz.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eventz.model.StoryRead;
import com.eventz.model.StoryReadId;

public interface StoryReadRepository extends JpaRepository<StoryRead, StoryReadId> {
	

}
