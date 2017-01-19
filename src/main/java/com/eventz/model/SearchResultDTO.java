package com.eventz.model;

import java.util.ArrayList;

public class SearchResultDTO {

	ArrayList<User> users;
	ArrayList<Story> stories;
	ArrayList<Chapter> chapters;
	ArrayList<StoryTag> storyTags;
	ArrayList<ChapterTag> chapterTags;
	
	public SearchResultDTO(){}
	
	public SearchResultDTO(ArrayList<User> users,ArrayList<Story> stories,ArrayList<Chapter> chapters,ArrayList<StoryTag> storyTags,ArrayList<ChapterTag> chapterTags)
	{
		this.users = users;
		this.stories = stories;
		this.chapters = chapters;
		this.storyTags = storyTags;
		this.chapterTags = chapterTags;
	}
	
	public ArrayList<User> getUsers() {
		return users;
	}
	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}
	public ArrayList<Story> getStories() {
		return stories;
	}
	public void setStories(ArrayList<Story> stories) {
		this.stories = stories;
	}
	public ArrayList<Chapter> getChapters() {
		return chapters;
	}
	public void setChapters(ArrayList<Chapter> chapters) {
		this.chapters = chapters;
	}
	public ArrayList<StoryTag> getStoryTags() {
		return storyTags;
	}
	public void setStoryTags(ArrayList<StoryTag> storyTags) {
		this.storyTags = storyTags;
	}

	public ArrayList<ChapterTag> getChapterTags() {
		return chapterTags;
	}

	public void setChapterTags(ArrayList<ChapterTag> chapterTags) {
		this.chapterTags = chapterTags;
	}
	
	
	
}
