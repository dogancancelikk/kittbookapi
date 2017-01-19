package com.eventz.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventz.model.Library;
import com.eventz.model.LibraryIndex;
import com.eventz.model.LibraryIndexId;
import com.eventz.model.Story;
import com.eventz.repository.LibraryIndexRepository;
import com.eventz.repository.LibraryRepository;

@Service
public class LibraryServiceImpl implements LibraryService {

	@Autowired
	LibraryRepository libraryRepository;
	@Autowired
	LibraryIndexRepository indexRepository;

	@Override
	public Collection<Library> findAll() {
		return null;
	}

	@Override
	public Library findOne(Long id) {
		Library lib = libraryRepository.findOneLibrary(id);
		if (lib == null)
			return null;
		return lib;
	}

	@Override
	public Library create(Library story) {
		return null;
	}

	@Override
	public Library update(Library story) {
		return null;
	}

	@Override
	public void delete(Long id) {
		
	}

	@Override
	public Collection<Library> getUserLibrary(Long id) {
		Collection<Library> libs = libraryRepository.getUserLibrary(id);
		return libs;
	}

	@Override
	public Collection<Story> getUserLibraryStories(Long id) {
		return libraryRepository.getUserLibStories(id);
	}

	@Override
	public LibraryIndex addStoryToLibrary(LibraryIndex ind) {
		if (ind == null || ind.getLibraryID() == null || ind.getStoryID() == null)
			return null;
		return indexRepository.save(ind);
	}

	@Override
	public boolean isStoryInLibrary(Long libraryID, Long storyID) {
		LibraryIndexId index = new LibraryIndexId(libraryID, storyID);
		LibraryIndex ind = indexRepository.findOne(index);
		if (ind == null)
			return false;
		return true;
	}

	@Override
	public void deleteStory(Long libraryID, Long storyID) {
		
		LibraryIndexId index = new LibraryIndexId(libraryID,storyID);
		if(indexRepository.findOne(index)!=null)
			indexRepository.delete(index);
	}

}
