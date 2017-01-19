package com.eventz.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.eventz.model.Library;
import com.eventz.model.Story;

@Repository
public interface LibraryRepository extends JpaRepository<Library,Long> {
public final String USER_LIBRARY = "SELECT l "
		+ "FROM Library l "
		+ "WHERE l.ownerID = :user "
		+ "AND l.status = 1";
public final String USER_LIBRARY_STORIES = "SELECT s "
		+ "FROM LibraryIndex i, Library l, Story s "
		+ "WHERE l.ownerID = :user "
		+ "AND s.id = i.storyID "
		+ "AND i.libraryID = l.id "
		+ "AND l.status = 1 "
		+ "AND s.status = 1 "
		+ "ORDER BY s.id ASC";
public final String FIND_ONE = "SELECT l "
		+ "FROM Library l "
		+ "WHERE l.status = 1 "
		+ "AND l.id = :library";


@Query(USER_LIBRARY)
public Collection<Library> getUserLibrary(@Param("user") Long id);
@Query(USER_LIBRARY_STORIES)
public Collection<Story> getUserLibStories(@Param("user") Long id);
@Query(FIND_ONE)
public Library findOneLibrary(@Param("library") Long id);
}
