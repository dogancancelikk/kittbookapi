package com.eventz.model;

import java.io.Serializable;

public class LibraryIndexId implements Serializable {

	private static final long serialVersionUID = 2133092072081860702L;
	private Long libraryID;
	private Long storyID;
	
	public LibraryIndexId(){
		
	}
	
	public LibraryIndexId(long libraryID,long storyID){
		this.libraryID=libraryID;
		this.storyID=storyID;
	}
	
	public boolean equals (Object o){
		return ((o instanceof LibraryIndexId) && storyID.equals(((LibraryIndexId) o).getStoryID())
				&& libraryID == ((LibraryIndexId) o).getLibraryID());
	}
	
	public int hashCode(){
		return libraryID.intValue()+storyID.intValue();
	}

	public long getLibraryID() {
		return libraryID;
	}

	public void setLibraryID(long libraryID) {
		this.libraryID = libraryID;
	}

	public long getStoryID() {
		return storyID;
	}

	public void setStoryID(long storyID) {
		this.storyID = storyID;
	}
	
	

}
