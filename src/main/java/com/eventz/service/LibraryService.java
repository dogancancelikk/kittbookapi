package com.eventz.service;

import java.util.Collection;

import com.eventz.model.Library;
import com.eventz.model.LibraryIndex;
import com.eventz.model.Story;
public interface LibraryService {
Collection<Library> findAll();
	
Library findOne(Long id);
	
Library create(Library story);
	
Library update(Library story);
	
void delete(Long id);

Collection<Library> getUserLibrary(Long id);

Collection<Story> getUserLibraryStories(Long id);

LibraryIndex addStoryToLibrary(LibraryIndex ind);

boolean isStoryInLibrary(Long libraryID,Long storyID);

void deleteStory(Long libraryID,Long storyID);

}
