package com.eventz.model;

import java.io.Serializable;

public class StoryCategoryId implements Serializable {

	private static final long serialVersionUID = 1618717164652543857L;
	private Long storyID;
	private Long categoryID;

	public StoryCategoryId(Long storyID, Long chapterID) {
		super();
		this.storyID = storyID;
		this.categoryID = chapterID;
	}

	public StoryCategoryId() {

	}

	public boolean equals(Object o) {
		return ((o instanceof StoryCategoryId) && storyID.equals(((StoryCategoryId) o).getStoryID())
				&& categoryID == ((StoryCategoryId) o).getChapterID());
	}

	public int hashCode() {
		return storyID.intValue() + categoryID.intValue();
	}

	public Long getStoryID() {
		return storyID;
	}

	public void setStoryID(Long storyID) {
		this.storyID = storyID;
	}

	public Long getChapterID() {
		return categoryID;
	}

	public void setChapterID(Long chapterID) {
		this.categoryID = chapterID;
	}

}
