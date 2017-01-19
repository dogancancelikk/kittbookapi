package com.eventz.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@IdClass(LibraryIndexId.class)
public class LibraryIndex {
	
	@Id
	private Long libraryID;
	@Id
	private Long storyID;
	
	public Long getLibraryID() {
		return libraryID;
	}
	public void setLibraryID(Long libraryID) {
		this.libraryID = libraryID;
	}
	public Long getStoryID() {
		return storyID;
	}
	public void setStoryID(Long storyID) {
		this.storyID = storyID;
	}
}
