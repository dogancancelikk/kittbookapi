package com.eventz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eventz.model.StoryDetail;
import com.eventz.model.StoryDetailId;

@Repository
public interface StoryDetailRepository extends JpaRepository<StoryDetail,StoryDetailId> {
	
}