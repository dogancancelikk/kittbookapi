package com.eventz.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.eventz.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
	
	public final static String GET_STORY_CATEGORIES = "SELECT c " + 
            "FROM StoryCategory sc, Category c " +
            "WHERE sc.categoryID = c.id " +
            "AND sc.storyID = :story ";
	
	
	@Query(GET_STORY_CATEGORIES)
	public Collection<Category> getStoryCategories(@Param("story") Long id);
}
