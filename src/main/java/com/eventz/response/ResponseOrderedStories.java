package com.eventz.response;

import java.util.Collection;

import com.eventz.model.Stories;

public class ResponseOrderedStories {

	private Collection<Stories> newStories;
	private Collection<Stories> mostRatedStories;
	private Collection<Stories> mostViewedStories;

	public Collection<Stories> getNewStories() {
		return newStories;
	}

	public void setNewStories(Collection<Stories> newStories) {
		this.newStories = newStories;
	}

	public Collection<Stories> getMostRatedStories() {
		return mostRatedStories;
	}

	public void setMostRatedStories(Collection<Stories> mostRatedStories) {
		this.mostRatedStories = mostRatedStories;
	}

	public Collection<Stories> getMostViewedStories() {
		return mostViewedStories;
	}

	public void setMostViewedStories(Collection<Stories> mostViewedStories) {
		this.mostViewedStories = mostViewedStories;
	}

}
